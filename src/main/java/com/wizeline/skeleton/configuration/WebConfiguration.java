package com.wizeline.skeleton.configuration;

import com.wizeline.skeleton.interceptor.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

  @Autowired private LoggerInterceptor loggerInterceptor;

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(loggerInterceptor).addPathPatterns("/**");
  }
}
