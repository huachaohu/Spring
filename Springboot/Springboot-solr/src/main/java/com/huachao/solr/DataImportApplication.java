package com.huachao.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class DataImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataImportApplication.class , args);
    }

}
