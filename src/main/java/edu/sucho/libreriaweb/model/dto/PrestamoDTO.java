package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class PrestamoDTO {
    private Integer id;
    private Boolean alta;
    private ClienteDTO cliente;
    private Calendar fechaDevolucion;
    private Calendar fechaPrestamo;
    private LibroDTO libro;
}
