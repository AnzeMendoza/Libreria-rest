package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class PrestamoResponseDTO {
    private Integer id;
    private Boolean alta;
    private ClienteResponseDTO cliente;
    private Calendar fechaDevolucion;
    private Calendar fechaPrestamo;
    private LibroResponseDTO libro;
}
