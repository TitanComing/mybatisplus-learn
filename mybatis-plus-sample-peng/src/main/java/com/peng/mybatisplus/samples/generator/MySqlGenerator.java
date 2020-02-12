package com.peng.mybatisplus.samples.generator;

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
public class MySqlGenerator {
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
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/mybatis-plus-sample-peng/src/main/java");
        gc.setFileOverride(true);
        gc.setEnableCache(true);
        gc.setActiveRecord(false);
        //开启 BaseResultMap
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(false);
        gc.setAuthor("peng");
        gc.setOpen(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        //设置 日期类型，避免生成时使用 jdk8 的LocalDatetime类型
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("peng");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //模块名
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.peng.mybatisplus.samples.generator");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        templateConfig.setService(null);     //不生成service
        templateConfig.setController(null);  //不生成controller
        templateConfig.setServiceImpl(null); //不生成controller
        // templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略(下划线转驼峰命名)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 列名生成策略(下划线转驼峰命名)
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 是否启动Lombok配置
        strategy.setEntityLombokModel(false);
        // 自定义实体父类
        //strategy.setSuperEntityClass("com.peng.mybatisplus.samples.generator.common.BaseEntity");
        // 自定义controller父类
        //strategy.setSuperControllerClass("com.peng.mybatisplus.samples.generator.common.BaseController");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();

    }
}
