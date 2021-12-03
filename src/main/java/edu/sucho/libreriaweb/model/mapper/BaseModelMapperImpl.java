package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.EditorialDTO;
import edu.sucho.libreriaweb.model.dto.LibroDTO;
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.entity.Libro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BaseModelMapperImpl implements BaseModelMapperDTO{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibroDTO libroToDto(Libro libro) {
        return modelMapper.map(libro, LibroDTO.class);
    }

    @Override
    public List<LibroDTO> listLibroToDto(List<Libro> libros) {
        return libros.stream()
                .map(this::libroToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EditorialDTO editorialToDto(Editorial editorial) {
        return modelMapper.map(editorial, EditorialDTO.class);
    }

    @Override
    public List<EditorialDTO> listEditorialToDto(List<Editorial> editoriales) {
        return editoriales.stream()
                .map(this::editorialToDto)
                .collect(Collectors.toList());
    }
}
