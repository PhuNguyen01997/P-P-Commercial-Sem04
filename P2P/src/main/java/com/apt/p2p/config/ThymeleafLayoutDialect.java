package com.apt.p2p.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafLayoutDialect {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
