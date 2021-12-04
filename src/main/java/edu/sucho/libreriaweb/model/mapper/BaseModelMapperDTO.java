package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.EditorialDTO;
import edu.sucho.libreriaweb.model.dto.LibroDTO;
import edu.sucho.libreriaweb.model.dto.PrestamoDTO;
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.model.entity.Prestamo;

import java.util.List;

public interface BaseModelMapperDTO {
    LibroDTO libroToDto(Libro libro);

    List<LibroDTO> listLibroToDto(List<Libro> libros);

    EditorialDTO editorialToDto(Editorial libro);

    List<EditorialDTO> listEditorialToDto(List<Editorial> libros);

    PrestamoDTO prestamoToDto(Prestamo prestamo);

    List<PrestamoDTO> listPrestamoToDto(List<Prestamo> prestamos);
}
