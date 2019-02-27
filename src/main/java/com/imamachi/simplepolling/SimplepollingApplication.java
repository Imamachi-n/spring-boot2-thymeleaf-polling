package com.imamachi.simplepolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SimplepollingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimplepollingApplication.class, args);
    }

}

//@SpringBootApplication
//public class SimplepollingApplication extends SpringBootServletInitializer {
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(SimplepollingApplication.class);
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(SimplepollingApplication.class, args);
//    }
//}