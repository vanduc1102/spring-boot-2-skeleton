package com.wizeline.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(
    value = "/data/project/skeleton/config/application.yml",
    ignoreResourceNotFound = true)
@ComponentScans(@ComponentScan(basePackages = "com.wizeline.skeleton"))
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
