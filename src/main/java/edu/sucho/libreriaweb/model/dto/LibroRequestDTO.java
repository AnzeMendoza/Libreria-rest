package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class LibroRequestDTO {

    @NotEmpty(message = "El titulo es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener min 2 caracteres y menos de 64")
    private String titulo;

    @NotNull(message = "El ISBN es obligatorio")
    @Max(value = 9999999999999l, message = "EL isbn supera el numero maximo permitido")
    private Long isbn;

    @NotNull(message = "Es obligatorio ingresar el año")
    @Min(value = 868, message = "El año no puede ser menor 868")
    private Integer anio;

    @NotNull(message = "El numero de ejempleres es obligatorio")
    private Integer ejemplares;

    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;

    @NotNull(message = "El autor es obligatorio")
    private Integer autorId;

    @NotNull(message = "La editorial es obligatoria")
    private Integer editorialId;


}
