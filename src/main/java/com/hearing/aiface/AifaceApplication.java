package com.hearing.aiface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

@MapperScan("com.hearing.aiface.dao")
@ServletComponentScan
@SpringBootApplication
public class AifaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AifaceApplication.class, args);
    }
//public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
//    List<String> warnings = new ArrayList<String>();
//    boolean overwrite = true;
//    File configFile = new File("/home/hearing/WorkSpace/SpringBoot/aiface/src/main/resources/gen.xml");
//    ConfigurationParser cp = new ConfigurationParser(warnings);
//    Configuration config = cp.parseConfiguration(configFile);
//    DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//    myBatisGenerator.generate(null);
//}
}
