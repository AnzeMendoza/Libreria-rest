package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.LibroDTO;
import edu.sucho.libreriaweb.model.entity.Libro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseModelMapperImpl implements BaseModelMapperDTO{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibroDTO libroToDto(Libro libro) {
        return modelMapper.map(libro, LibroDTO.class);
    }

    @Override
    public LibroDTO convertToDto(Libro libro) {
        return modelMapper.map(libro, LibroDTO.class);
    }
}
