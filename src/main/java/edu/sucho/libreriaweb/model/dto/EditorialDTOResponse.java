package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EditorialDTOResponse {
    private Integer id;
    private String nombre;
    private Boolean alta;
    private List<LibroDTO> libros;
}
