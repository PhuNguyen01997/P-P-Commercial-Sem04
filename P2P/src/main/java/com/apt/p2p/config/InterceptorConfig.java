package com.apt.p2p.config;

import com.apt.p2p.common.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InterceptorConfig {
    @Bean
    public MyInterceptor MyInterceptor() {
        MyInterceptor myInterceptor = new MyInterceptor();
        Map<String, String> naviPortalHeader = new HashMap<>();
        myInterceptor.setNaviPortalHeader(naviPortalHeader);

        return myInterceptor;
    }
}
