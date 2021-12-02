package edu.sucho.libreriaweb.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperDTO {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
