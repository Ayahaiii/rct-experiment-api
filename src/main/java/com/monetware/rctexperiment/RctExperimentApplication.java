package com.monetware.rctexperiment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.monetware.rctexperiment.business.dao")
public class RctExperimentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RctExperimentApplication.class, args);
    }

}
