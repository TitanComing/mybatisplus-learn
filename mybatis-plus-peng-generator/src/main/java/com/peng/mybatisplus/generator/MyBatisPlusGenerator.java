package com.peng.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * Created by peng on 2020/2/11.
 */
public class MyBatisPlusGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if(scanner.hasNext()){
            String ipt = scanner.next();
            if(StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 生成文件的输出目录, 默认 D 盘根目录
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/mybatis-plus-peng-generator/src/main/java");
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 是否在xml中添加二级缓存配置
        gc.setEnableCache(true);
        // Active Record 设计模式，实现数据对象Object到关系数据库的映射，实体类只需继承 Model 类即可实现基本 CRUD 操作
        gc.setActiveRecord(false);
        //开启 BaseResultMap
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        //设置 日期类型，避免生成时使用 jdk8 的LocalDatetime类型
        gc.setDateType(DateType.ONLY_DATE);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(false);
        // 作者
        gc.setAuthor("peng");
        // 是否打开输出目录
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包模块名
        pc.setModuleName(scanner("模块名")); //crud
        pc.setParent("com.peng.mybatisplus");   //最终是： com.peng.mybatisplus.crud
        // pc.setEntity(null);
        // pc.setMapper(null);
        // pc.setService(null);
        // pc.setServiceImpl(null);
        // pc.setXml(null);
        mpg.setPackageInfo(pc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库类型【必须
        dsc.setDbType(DbType.MYSQL);
        // 对于mysql，schema和database可以理解为等价的
        // dsc.setSchemaName(null);
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("peng");
        mpg.setDataSource(dsc);

        // 策略配置(数据库表配置)
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略(下划线转驼峰命名)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略(下划线转驼峰命名)
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 是否启动Lombok配置
        strategy.setEntityLombokModel(true);
        // 自定义继承的Entity类全称，带包名
        //strategy.setSuperEntityClass("com.peng.mybatisplus.samples.generator.common.BaseEntity");
        // 自定义基础的Entity类，公共字段
        // strategy.setSuperEntityColumns("id");
        // 自定义controller父类
        //strategy.setSuperControllerClass("com.peng.mybatisplus.samples.generator.common.BaseController");
        // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 字段前缀
        // strategy.setFieldPrefix("");
        mpg.setStrategy(strategy);


        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setMapper(null);
        // templateConfig.setXml(null);
        templateConfig.setService(null);     //不生成service
        templateConfig.setServiceImpl(null); //不生成serviceImpl
        templateConfig.setController(null);  //不生成controller
        mpg.setTemplate(templateConfig);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();

    }
}
