package edu.sucho.libreriaweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDTO {
    
    private Integer id;
    private String titulo;
    private Long isbn;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    private String autor;
}
