package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;
import edu.sucho.libreriaweb.service.inter.PrestamoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModelMapperDTOImpl implements ModelMapperDTO {

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PrestamoService prestamoService;

    @Override
    public AutorResponseDTO autorToDto(Autor autor) {
        return modelMapper.map(autor, AutorResponseDTO.class);
    }

    @Override
    public List<AutorResponseDTO> listAutorToDto(List<Autor> autores) {
        return autores.stream()
                .map(this::autorToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO clienteToDto(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    public List<ClienteResponseDTO> listClienteToDto(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::clienteToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EditorialResponseDTO editorialToDto(Editorial editorial) {
        return modelMapper.map(editorial, EditorialResponseDTO.class);
    }

    @Override
    public List<EditorialResponseDTO> listEditorialToDto(List<Editorial> editoriales) {
        return editoriales.stream()
                .map(this::editorialToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LibroResponseDTO libroToDto(Libro libro) {
        return modelMapper.map(libro, LibroResponseDTO.class);
    }

    @Override
    public List<LibroResponseDTO> listLibroToDto(List<Libro> libros) {
        return libros.stream()
                .map(this::libroToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PrestamoResponseDTO prestamoToDto(Prestamo prestamo) {

        return modelMapper.map(prestamo, PrestamoResponseDTO.class);
    }

    @Override
    public List<PrestamoResponseDTO> listPrestamoToDto(List<Prestamo> prestamos) {
        return prestamos.stream()
                .map(this::prestamoToDto)
                .collect(Collectors.toList());
    }

}
