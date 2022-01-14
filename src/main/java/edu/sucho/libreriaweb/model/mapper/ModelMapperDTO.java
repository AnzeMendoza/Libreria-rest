package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;

import java.util.List;

public interface ModelMapperDTO {
    AutorResponseDTO autorToDto(Autor libro);

    List<AutorResponseDTO> listAutorToDto(List<Autor> libros);

    ClienteResponseDTO clienteToDto(Cliente libro);

    List<ClienteResponseDTO> listClienteToDto(List<Cliente> libros);

    EditorialDTOResponse editorialToDto(Editorial libro);

    List<EditorialDTOResponse> listEditorialToDto(List<Editorial> libros);

    LibroDTO libroToDto(Libro libro);

    List<LibroDTO> listLibroToDto(List<Libro> libros);

    PrestamoDTO prestamoToDto(Prestamo prestamo);

    List<PrestamoDTO> listPrestamoToDto(List<Prestamo> prestamos);
}
