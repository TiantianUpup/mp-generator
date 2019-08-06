package com.h2t.study;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2019/08/05 11:52
 */
public class MpGenerator {
    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.h2t.study";

    /**
     * 代码生成路径
     */
    private static final String OUTPUT_DIR = "C:\\Users\\hetiantian\\Desktop\\test";

    /**
     * 代码注释作者
     */
    private static final String AUTHOR = "hetiantian";

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    /**
     * 数据库信息
     */
    private static final String DATABASE = "test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE
            + "?characterEncoding=UTF8&serverTimezone=UTC";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //项目所在地址
        gc.setOutputDir(OUTPUT_DIR);
        //注释作者
        gc.setAuthor(AUTHOR);
        //生成文件不打开
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // XML 二级缓存
        gc.setEnableCache(false);
        //生成result map
        // XML ResultMap
        gc.setBaseResultMap(true);
        //生成java mysql字段映射
        // XML columList
        gc.setBaseColumnList(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(PACKAGE_NAME);
        pc.setEntity("po");
        pc.setXml("mapper");
        pc.setController("controller");
        pc.setService("service");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        //不生成mapper xml文件
        //templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 实体是否使用Lombok插件
        strategy.setEntityLombokModel(false);
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.h2t.study.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.h2t.study.BaseServiceImpl");
        // 控制层是否使用Rest风格
        strategy.setRestControllerStyle(true);

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
