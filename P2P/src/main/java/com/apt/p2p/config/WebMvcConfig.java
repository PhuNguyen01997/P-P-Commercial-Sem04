package com.apt.p2p.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler(("/static/**")).addResourceLocations(("classpath:/static/"));
        registry.addResourceHandler(("/assets/**")).addResourceLocations(("file:///D:/P-P-Commercial-Sem04/P2P/src/main/assets/"));
    }
}
