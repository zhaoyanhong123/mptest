package com.test.oss.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://** ")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("Access-Control-Allow-Origin")
               /* .exposedHeaders("header1", "header2")*/
                .allowCredentials(false).maxAge(3600);
    }

}
