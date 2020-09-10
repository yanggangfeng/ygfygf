package com.example.annotationdemo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class })
@RestController
@MapperScan(basePackages = "com.example.annotationdemo.user.dao")
@EnableAspectJAutoProxy
public class AnnotationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnnotationDemoApplication.class, args);
    }


    @RequestMapping("/ygf")
    public String name(){
        return "YGF";
    }

}
