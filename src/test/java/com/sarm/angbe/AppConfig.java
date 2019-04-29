package com.sarm.angbe;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.sarm", "com.sarm.angbe" , "io.swagger.configuration"})
public class AppConfig {
}
