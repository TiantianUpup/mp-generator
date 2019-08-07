### 所需依赖
```
 <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.0.7</version>
    </dependency>

    <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-generator</artifactId>
      <version>3.1.2</version>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>6.0.6</version>
    </dependency>

    <!-- 模板引擎mybaties-plus  代码生成 -->
 
    <dependency>
      <groupId>org.freemarker</groupId>
      <artifactId>freemarker</artifactId>
      <version>2.3.23</version>
    </dependency>
```


### 完整代码生成器代码
```
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
        mpg.setGlobalConfig(buildGlobalConfig());
        // 数据源配置
        mpg.setDataSource(buildDataSourceConfig());
        // 包配置
        mpg.setPackageInfo(buildPackageConfig());
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
        mpg.setStrategy(buildStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 全局构造配置类
     *
     * @return
     * */
    private static GlobalConfig buildGlobalConfig() {
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
        return gc;
    }

    /**
     * 数据库配置信息
     *
     * @return
     * */
    private static DataSourceConfig buildDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USERNAME);
        dsc.setPassword(PASSWORD);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //将数据库中timestamp转换成date
                if ( fieldType.toLowerCase().contains( "timestamp" ) ) {
                    return DbColumnType.DATE;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        return dsc;
    }

    private static PackageConfig buildPackageConfig() {
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent(PACKAGE_NAME);
        pc.setEntity("po");
        pc.setXml("mapper");
        pc.setController("controller");
        pc.setService("service");
        return pc;
    }

    private static StrategyConfig buildStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        // 命名规则
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 默认生成的po类不继承，手动修改继承
        //strategy.setSuperEntityClass((String) null);
        // 实体是否使用Lombok插件
        strategy.setEntityLombokModel(false);
        // 自定义 service 父类
        strategy.setSuperServiceClass("com.h2t.study.BaseService");
        // 自定义 service 实现类父类
        strategy.setSuperServiceImplClass("com.h2t.study.BaseServiceImpl");
        // 控制层是否使用Rest风格
        strategy.setRestControllerStyle(true);
        return strategy;
    }
}
```


### 目前代码生成器的不足
![many errors， many bugs](https://upload-images.jianshu.io/upload_images/9358011-53be450defc5a1ee.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
- 生成的po自动会继承Model类
- 生成的po类缺少`@TableName`、`@TableField`等注解与数据库字段进行映射
这些不足需要手动进行修改。聪明的网友如果知道如果修改，请一定要告诉我！！！



