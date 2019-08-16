package xyz.iadmin.demo;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.*;


/**
 * mybatis-plus代码生成工具3.0版本 最新版 3.12
 * 详细配置：https://mp.baomidou.com/config/generator-config.html
 * @author xieshuang
 * 若使用请在maven添加以下依赖
 * <dependency>
 *             <groupId>org.apache.velocity</groupId>
 *             <artifactId>velocity</artifactId>
 *             <version>RELEASE</version>
 *         </dependency>
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {

        // 模块名
        String module = "iadmin-demo";
        // 是否多模块项目
        boolean isModule = true;
        // 包路径
        String packAge = MybatisPlusGenerator.class.getPackage().getName();
        String package1 = packAge+".visit_client";
        // 作者
        String author = "谢霜";
        // 数据库url
        String dataUrl = "127.0.0.1:3307";
        // 数据库用户名
        String username = "root";
        // 数据库密码
        String password = "root";
        // 数据库名
        String dataBase = "iadmin";
        // 逻辑删除字段，不要为null或者空
        String logicDeleteFieldName = "deleted";
        // 需要生成的表，正则表达式匹配前缀
        String tableName = "^sys.*";
        String[] ExcludeTable = new String[]{};
        // 需要生成的表的前缀，生成后将不含前缀
        String[] tableQ = new String[]{"sys_"};
        // 是否启用redis
        boolean enableRedis = true;
        // 是否在entity生成jsr303校验注解
        boolean enableValidator = true;
        // 是否生成 CRUD代码
        boolean enableCrud = true;
        // 是否使用 lombok
        boolean enableLombok = true;
        // 是否使用 Swagger
        boolean enableSwagger = true;
        // 时间类型对应策略
        DateType dateType = DateType.ONLY_DATE;
        String filePath;

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setDateType(dateType);
        gc.setOpen(false);
        gc.setSwagger2(enableSwagger);
        String projectPath = System.getProperty("user.dir");
        if (isModule){
            filePath = projectPath +"/"+ module +"/src/main/java";
            gc.setOutputDir(projectPath +"/"+ module +"/src/main/java");
        }else {
            filePath = projectPath + "/src/main/java";
            gc.setOutputDir(projectPath + "/src/main/java");
        }
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(false);
        gc.setAuthor(author);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl("jdbc:mysql://"+dataUrl+"/"+dataBase+"?characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDbQuery(new MySqlQuery() {
            /**
             * 重写父类预留查询自定义字段<br>
             * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
             * 模板中调用：  table.fields 获取所有字段信息，
             * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
             */
            @Override
            public String[] fieldCustom() {
                return new String[]{"NULL", "PRIVILEGES"};
            }
        });
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);全局大写命名 ORACLE 注意
        strategy.setInclude(tableName);
        strategy.setTablePrefix(tableQ);
        if (logicDeleteFieldName!=null&&!logicDeleteFieldName.equals("")){
            strategy.setLogicDeleteFieldName(logicDeleteFieldName);
        }
        strategy.setEntityLombokModel(enableLombok);
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        if (ExcludeTable.length>0){
            // 排除生成的表
            strategy.setExclude(new String[]{"test"});
        }
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        mpg.setStrategy(strategy);
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("enableRedis", enableRedis);
                this.setMap(map);
            }
        };
        if (enableCrud){
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            String templatePath = "/generator/query.java.vm";
            System.out.println(packToDir(package1));
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return filePath + packToDir(package1) +
                            "/controller/query/" + tableInfo.getEntityName() + "Query" + StringPool.DOT_JAVA;
                }
            });
            cfg.setFileOutConfigList(focList);
        }
        mpg.setCfg(cfg);
        TemplateConfig tc = new TemplateConfig();
        if (enableCrud){
            tc.setController("/generator/controller.java.vm");
            tc.setService("/generator/service.java.vm");
            tc.setServiceImpl("/generator/serviceImpl.java.vm");
        }
        if (enableValidator){
            tc.setEntity("/generator/entity.java.vm");
        }
        mpg.setTemplate(tc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(package1);
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        mpg.execute();
    }

    private static String packToDir(String s){
        return "/"+String.join("/", Arrays.asList(s.split("\\.")));
    }
}
