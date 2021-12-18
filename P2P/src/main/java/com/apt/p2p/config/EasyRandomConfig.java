package com.apt.p2p.config;

import org.jeasy.random.EasyRandom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EasyRandomConfig {
    @Bean
    public EasyRandom EasyRandom(){
        EasyRandom random = new EasyRandom();
        return random;
    }
}
