package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperDTOImpl implements ModelMapperDTO {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public AutorDTO autorToDto(Autor autor) {
        return modelMapper.map(autor, AutorDTO.class);
    }

    @Override
    public List<AutorDTO> listAutorToDto(List<Autor> autores) {
        return autores.stream()
                .map(this::autorToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO clienteToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    @Override
    public List<ClienteDTO> listClienteToDto(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::clienteToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EditorialDTOResponse editorialToDto(Editorial editorial) {
        return modelMapper.map(editorial, EditorialDTOResponse.class);
    }

    @Override
    public List<EditorialDTOResponse> listEditorialToDto(List<Editorial> editoriales) {
        return editoriales.stream()
                .map(this::editorialToDto)
                .collect(Collectors.toList());
    }

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
    public PrestamoDTO prestamoToDto(Prestamo prestamo) {
        return modelMapper.map(prestamo, PrestamoDTO.class);
    }

    @Override
    public List<PrestamoDTO> listPrestamoToDto(List<Prestamo> prestamos) {
        return prestamos.stream()
                .map(this::prestamoToDto)
                .collect(Collectors.toList());
    }
}
