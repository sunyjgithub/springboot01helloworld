package com.atguigu.config;


import com.atguigu.entity.Person;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    @ConfigurationProperties(prefix = "person")
    public Person person(){
        return new Person();
    }
}
