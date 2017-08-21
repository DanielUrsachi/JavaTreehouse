package com.teamtreehouse.giflib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan // pentru scanarea controllerurilor in package
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args); // pornesc web - aplicatia
    }
    //TODO:Modeling, Storing, and Presenting Data course ! ! !
    //TODO:Using the MVC Architecture course ! ! !
}