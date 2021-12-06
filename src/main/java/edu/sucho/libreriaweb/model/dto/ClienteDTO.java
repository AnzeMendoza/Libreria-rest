package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
    private Long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private Boolean alta;
}
