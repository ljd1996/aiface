package com.hearing.aiface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hearing.aiface.dao")
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
