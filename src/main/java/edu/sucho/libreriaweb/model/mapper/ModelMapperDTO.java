package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;

import java.util.List;

public interface ModelMapperDTO {
    AutorResponseDTO autorToDto(Autor libro);

    List<AutorResponseDTO> listAutorToDto(List<Autor> libros);

    ClienteResponseDTO clienteToDto(Cliente libro);

    List<ClienteResponseDTO> listClienteToDto(List<Cliente> libros);

    EditorialResponseDTO editorialToDto(Editorial libro);

    List<EditorialResponseDTO> listEditorialToDto(List<Editorial> libros);

    LibroResponseDTO libroToDto(Libro libro);

    List<LibroResponseDTO> listLibroToDto(List<Libro> libros);

    PrestamoResponseDTO prestamoToDto(Prestamo prestamo);

    List<PrestamoResponseDTO> listPrestamoToDto(List<Prestamo> prestamos);

    }
