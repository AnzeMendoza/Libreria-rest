package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {
    private long documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String username;
    private String password;
    private int roleId;
}