package com.pxb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 扫描servlet
@ServletComponentScan
public class FilterdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterdemoApplication.class, args);
    }

}
