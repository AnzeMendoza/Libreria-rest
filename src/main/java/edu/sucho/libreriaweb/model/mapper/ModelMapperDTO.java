package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;

import java.util.List;

public interface ModelMapperDTO {
    AutorDTO autorToDto(Autor libro);

    List<AutorDTO> listAutorToDto(List<Autor> libros);

    ClienteDTO clienteToDto(Cliente libro);

    List<ClienteDTO> listClienteToDto(List<Cliente> libros);

    EditorialDTOResponse editorialToDto(Editorial libro);

    List<EditorialDTOResponse> listEditorialToDto(List<Editorial> libros);

    LibroDTO libroToDto(Libro libro);

    List<LibroDTO> listLibroToDto(List<Libro> libros);

    PrestamoDTO prestamoToDto(Prestamo prestamo);

    List<PrestamoDTO> listPrestamoToDto(List<Prestamo> prestamos);
}
