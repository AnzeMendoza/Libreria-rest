package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
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

    PrestamoDTOResponse prestamoToDto(Prestamo prestamo);

    List<PrestamoDTOResponse> listPrestamoToDto(List<Prestamo> prestamos);

    }
