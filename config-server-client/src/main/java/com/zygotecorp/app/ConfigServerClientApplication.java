package com.zygotecorp.app;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigServerClientApplication {
	private static Logger logger =  LoggerFactory.getLogger(ConfigServerClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerClientApplication.class, args);
	}

//@RefreshScope
//@RestController
//class MessageRestController {
// 
//    @Value("${msg:Hello world - Config Server is not working..pelase check}")
//    private String msg;
// 
//    @RequestMapping("/msg")
//    String getMsg() {
//        return this.msg;
//    }
//}

//public void setEnvironment(Environment environment) {
//    //Set up Relative path of Configuration directory/folder, should be at the root of the project or the same folder where the jar/war is placed or being run from
//    String configFolder = "config";
//    //All static property file names here
//    List<String> propertyFiles = Arrays.asList("application.properties","server.properties");
//    //This is also useful for appending the profile names
//    Arrays.asList(environment.getActiveProfiles()).stream().forEach(environmentName -> propertyFiles.add(String.format("application-%s.properties", environmentName))); 
//    for (String configFileName : propertyFiles) {
//        File configFile = new File(configFolder, configFileName);
//        System.out.println("\n\n\n\n");
//        System.out.println(String.format("looking for configuration %s from %s", configFileName, configFolder));
//        FileSystemResource springResource = new FileSystemResource(configFile);
////        ((Object) logger).log(Level.INFO, "Config file : {0}", (configFile.exists() ? "FOund" : "Not Found"));
//        if (configFile.exists()) {
//            try {
//            	System.out.println(String.format("Loading configuration file %s", configFileName));
//                PropertiesFactoryBean pfb = new PropertiesFactoryBean();
//                pfb.setFileEncoding("UTF-8");
//                pfb.setLocation(springResource);
//                pfb.afterPropertiesSet();
//                Properties properties = pfb.getObject();
//                PropertiesPropertySource externalConfig = new PropertiesPropertySource("externalConfig", properties);
//                ((ConfigurableEnvironment) environment).getPropertySources().addFirst(externalConfig);
//            } catch (IOException ex) {
//             //LOGGER.log(Level.SEVERE, null, ex);
//            }
//        } else {
//        	System.out.println(String.format("Cannot find Configuration file %s... \n\n\n\n", configFileName));
//
//        }
//
//    }
//}

}

