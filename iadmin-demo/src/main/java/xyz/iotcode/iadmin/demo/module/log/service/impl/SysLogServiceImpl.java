package xyz.iotcode.iadmin.demo.module.log.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import xyz.iotcode.iadmin.core.base.dto.TimeDTO;
import xyz.iotcode.iadmin.core.common.log.SaveLog;
import xyz.iotcode.iadmin.demo.common.util.AddressUtil;
import xyz.iotcode.iadmin.demo.module.log.controller.query.SysLogQuery;
import xyz.iotcode.iadmin.demo.module.log.entity.SysLog;
import xyz.iotcode.iadmin.demo.module.log.mapper.SysLogMapper;
import xyz.iotcode.iadmin.demo.module.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.iotcode.iadmin.core.wrapper.WrapperFactory;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2019-08-27
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<SysLog> ipage(SysLogQuery query) {
        TimeDTO.setBeginAndEndTime(query);
        return this.page(query.createPage(), new WrapperFactory<SysLog>().create(query));
    }

    @Override
    public boolean isave(ProceedingJoinPoint joinPoint, SysLog param) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 请求的方法名
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if (ArrayUtil.isNotEmpty(argNames)){
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < argNames.length; i++) {
                jsonObject.put(argNames[i], argValues[i]);
            }
            param.setParams(jsonObject.toJSONString());
        }
        SaveLog log = method.getAnnotation(SaveLog.class);
        // 注解上的描述
        param.setDescription(log.value());
        param.setAddress(AddressUtil.getCityInfo(param.getRequestIp()));
        param.setLogLevel(log.level());
        param.setMethod(methodName);
        return this.save(param);
    }

    @Override
    public boolean iupdate(SysLog param) {
        return this.updateById(param);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean iremove(List<Integer> list) {
        for (Integer integer : list) {
            this.iremove(integer);
        }
        return true;
    }

    @Override
    public SysLog igetById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean iremove(Integer id) {
        return this.removeById(id);
    }
}
