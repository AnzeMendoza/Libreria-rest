package edu.sucho.libreriaweb.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperDto {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
