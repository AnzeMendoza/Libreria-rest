package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.dto.*;
import edu.sucho.libreriaweb.model.entity.*;
import edu.sucho.libreriaweb.repository.PrestamoRepository;
import edu.sucho.libreriaweb.service.impl.PrestamoServiceImpl;
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
    public PrestamoDTOResponse prestamoToDto(Prestamo prestamo) {

        return modelMapper.map(prestamo, PrestamoDTOResponse.class);
    }

    @Override
    public List<PrestamoDTOResponse> listPrestamoToDto(List<Prestamo> prestamos) {
        return prestamos.stream()
                .map(this::prestamoToDto)
                .collect(Collectors.toList());
    }

}
