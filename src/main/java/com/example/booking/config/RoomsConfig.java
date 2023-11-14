package com.example.booking.config;

import com.example.booking.service.RoomsService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomsConfig {
    @Bean
    public RoomsService employeeBean() {
        return new RoomsService();
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
