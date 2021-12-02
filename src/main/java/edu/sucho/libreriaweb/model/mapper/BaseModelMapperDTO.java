package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.LibroDTO;
import edu.sucho.libreriaweb.model.entity.Libro;

public interface BaseModelMapperDTO {
    LibroDTO libroToDto(Libro libro);

    LibroDTO convertToDto(Libro libro);
}
