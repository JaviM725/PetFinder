package com.royal.msvet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MsVetApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsVetApplication.class, args);
    }
}
