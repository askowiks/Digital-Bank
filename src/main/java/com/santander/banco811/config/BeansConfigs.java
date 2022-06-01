package com.santander.banco811.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfigs {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
