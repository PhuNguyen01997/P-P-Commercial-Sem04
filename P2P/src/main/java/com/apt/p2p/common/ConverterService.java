package com.apt.p2p.common;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterService {
    @Bean(name = "hideCardNumber")
    Converter HideCardNumberConverter() {
        return new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                StringBuilder builder = new StringBuilder(mappingContext.getSource());
                int mark = builder.length() - 4;
                for (int i = 0; i < mark; i++) {
                    builder.replace(i, i + 1, "*");
                }
                for (int i = 4; i < builder.length(); i += 5) {
                    builder.insert(i, " ");
                }

                return builder.toString();
            }
        };
    }

    @Bean(name = "removeSpaceNumber")
    Converter RemoveSpaceNumberConverter(){
        return new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return mappingContext.getSource().replaceAll("\\s+", "");
            }
        };
    }

    @Bean(name = "productAddPathImage")
    Converter productAddPathImage(){
        return new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return "/images/product/" + mappingContext.getSource();
            }
        };
    }

    @Bean(name = "setNullDestination")
    Converter setNullDestination(){
        return new Converter() {
            @Override
            public Object convert(MappingContext mappingContext) {
                return null;
            }
        };
    }
}
