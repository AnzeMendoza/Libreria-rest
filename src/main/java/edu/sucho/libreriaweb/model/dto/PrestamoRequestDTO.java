package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.util.Calendar;

@Getter
@Setter

public class PrestamoRequestDTO {

    @NotNull(message = "Cliente no puede ser nulo")
    private Long dniCliente;

    @NotNull(message = "fechaPrestamo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private Calendar fechaPrestamo;

    @NotNull(message = "fechaDevolución no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Calendar fechaDevolucion;

    @Size(min = 2, max = 64, message = "Debe tener min 2 caracteres y menos de 64")
    private String tituloLibro;

    private Long isbn ;
}
