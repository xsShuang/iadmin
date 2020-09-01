package xyz.iotcode.iadmin.common.mapping.resolver;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import xyz.iotcode.iadmin.common.mapping.annotation.Mapping;
import xyz.iotcode.iadmin.common.mapping.annotation.Mappings;
import xyz.iotcode.iadmin.common.util.SpringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author xieshuang
 * @date 2020-07-08 18:47
 */
@Slf4j
public class MappingResolver {

    public static <V> V mapping(Class<V> vClass, Object d) {
        V v = create(vClass);
        BeanUtils.copyProperties(d, Objects.requireNonNull(v));
        Map<String, Field> vFieldMap = getAllFieldMap(vClass);
        Map<String, Field> dFieldMap = getAllFieldMap(d.getClass());
        for (Map.Entry<String, Field> stringFieldEntry : dFieldMap.entrySet()) {
            try {
                Field field = stringFieldEntry.getValue();
                Object value = field.get(d);
                Mappings mappings = field.getAnnotation(Mappings.class);
                if (mappings != null){
                    Mapping[] mappingArray = mappings.value();
                    for (Mapping mapping : mappingArray) {
                        String[] params = mapping.params();
                        Class<?>[] classArray = getClassArray(value, params, dFieldMap);
                        Object[] valueArray = getValueArray(value, params, dFieldMap, d);
                        Method method = mapping.provider().getMethod(mapping.method(), classArray);
                        Object invoke = method.invoke(SpringUtils.getBean(mapping.provider()), valueArray);
                        setValue(mapping, v, invoke, vFieldMap, field);
                    }
                }else {
                    Mapping annotation = field.getAnnotation(Mapping.class);
                    if (annotation != null) {
                        String[] params = annotation.params();
                        Class<?>[] classArray = getClassArray(value, params, dFieldMap);
                        Object[] valueArray = getValueArray(value, params, dFieldMap, d);
                        Method method = annotation.provider().getMethod(annotation.method(), classArray);
                        Object invoke = method.invoke(SpringUtils.getBean(annotation.provider()), valueArray);
                        setValue(annotation, v, invoke, vFieldMap, field);
                    }
                }
            }catch (Exception e1){
                log.error("映射解析处理字段 {} 时出错：", stringFieldEntry.getKey(), e1);
            }
        }
        return v;
    }

    private static <V> void setValue(Mapping annotation, V v, Object invoke, Map<String, Field> vFieldMap, Field field) throws IllegalAccessException {
        if (Mapping.MappingType.FIELD.equals(annotation.mappingType())){
            Map<String, Field> allFieldMap = getAllFieldMap(invoke.getClass());
            for (String s : annotation.resultTrans()) {
                String[] split = s.split(">");
                Field field1 = allFieldMap.get(split[0]);
                Field field2 = vFieldMap.get(split[1]);
                if (field1 != null && field2 != null){
                    field2.set(v, field1.get(invoke));
                }
            }
        }else if (Mapping.MappingType.BEAN.equals(annotation.mappingType())){
            if ("$$".equals(annotation.resultTrans()[0])){
                Field vField = vFieldMap.get(field.getName());
                if (vField != null){
                    vField.set(v, invoke);
                }
            }else {
                String s = StrUtil.removePrefix(annotation.resultTrans()[0], "$");
                Field vField = vFieldMap.get(s);
                if (vField != null){
                    vField.set(v, invoke);
                }
            }
        }else {
            if ("$$".equals(annotation.resultTrans()[0])){
                Field vField = vFieldMap.get("mappingVO");
                if (vField != null){
                    Map<String, Object> map = (Map<String, Object>)vField.get(v);
                    if (map == null){
                        map = new HashMap<>();
                    }
                    map.put(field.getName(), invoke);
                    vField.set(v, map);
                }
            }else {
                String s = StrUtil.removePrefix(annotation.resultTrans()[0], "$");
                Field vField = vFieldMap.get("mappingVO");
                if (vField != null){
                    Map<String, Object> map = (Map<String, Object>)vField.get(v);
                    if (map == null){
                        map = new HashMap<>();
                    }
                    map.put(s, invoke);
                    vField.set(v, map);
                }
            }
        }
    }

    private static Class<?>[] getClassArray(Object value, String[] params, Map<String, Field> dFieldMap){
        Class<?>[] classArray = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            classArray[i] = getClass(params[i], value, dFieldMap);
        }
        return classArray;
    }

    private static Object[] getValueArray(Object value, String[] params, Map<String, Field> dFieldMap, Object d) throws IllegalAccessException {
        Object[] valueArray = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            valueArray[i] = getObject(params[i], value, dFieldMap, d);
        }
        return valueArray;
    }

    private static Class<?> getClass(String param, Object value, Map<String, Field> dFieldMap){
        if ("$$".equals(param)){
            return value.getClass();
        }else if (param.startsWith("$")){
            Field field = dFieldMap.get(StrUtil.removePrefix(param, "$"));
            return field.getClass();
        }else {
            String[] split = param.split("#");
            return getClassByStr(split[0]);
        }
    }

    private static Object getObject(String param, Object value, Map<String, Field> dFieldMap, Object d) throws IllegalAccessException {
        if ("$$".equals(param)){
            return value;
        }else if (param.startsWith("$")){
            Field field = dFieldMap.get(StrUtil.removePrefix(param, "$"));
            return field.get(d);
        }else {
            String[] split = param.split("#");
            return getObjectByStr(split[0], split[1]);
        }
    }

    private static <V> V create(Class<V> vClass){
        try {
            return vClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Field> getAllFieldMap(Class<?> cls){
        Field[] vFields = getAllFields(cls);
        Map<String, Field> fieldMap = new HashMap<>(vFields.length);
        for (Field field : vFields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    private static Field[] getAllFields(final Class<?> cls) {
        final List<Field> allFieldsList = getAllFieldsList(cls);
        return allFieldsList.toArray(new Field[0]);
    }

    private static List<Field> getAllFieldsList(final Class<?> cls) {
        if (cls==null){
            throw new IllegalArgumentException("The class must not be null");
        }
        final List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            allFields.addAll(Arrays.asList(declaredFields));
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }

    private static Class<?> getClassByStr(String typeName){
        switch (typeName){
            case "date":
                return Date.class;
            case "BigDecimal":
                return BigDecimal.class;
            case "String":
                return String.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "double":
                return double.class;
            case "boolean":
                return boolean.class;
            case "float":
                return float.class;
            case "byte":
                return byte.class;
            case "short":
                return short.class;
            case "char":
                return char.class;
            case "Integer":
                return Integer.class;
            case "Double":
                return Double.class;
            case "Float":
                return Float.class;
            case "Boolean":
                return Boolean.class;
            case "Byte":
                return Byte.class;
            case "Short":
                return Short.class;
            case "Long":
                return Long.class;
            case "Character":
                return Character.class;
            default:
                throw new IllegalArgumentException("找不到该类型的class");
        }
    }

    private static Object getObjectByStr(String typeName, String value){
        switch (typeName){
            case "date":
                return DateUtil.parse(value);
            case "BigDecimal":
                return new BigDecimal(value);
            case "String":
                return value;
            case "int":
                return Integer.parseInt(value);
            case "long":
                return Long.parseLong(value);
            case "double":
                return Double.parseDouble(value);
            case "boolean":
                return Boolean.parseBoolean(value);
            case "float":
                return Float.parseFloat(value);
            case "byte":
                return Byte.parseByte(value);
            case "short":
                return Short.parseShort(value);
            case "char":
                return value.charAt(0);
            case "Integer":
                return Integer.valueOf(value);
            case "Double":
                return Double.valueOf(value);
            case "Float":
                return Float.valueOf(value);
            case "Boolean":
                return Boolean.valueOf(value);
            case "Byte":
                return Byte.valueOf(value);
            case "Short":
                return Short.valueOf(value);
            case "Long":
                return Long.valueOf(value);
            default:
                throw new IllegalArgumentException("找不到该类型的class");
        }
    }
}
