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
    @Max(value = 9999999999999L, message = "EL isbn supera el numero maximo permitido")
    private Long isbn;

    @NotNull(message = "Es obligatorio ingresar el año")
    @Min(value = 868, message = "El año no puede ser menor 868")
    private Integer anio;

    @NotNull(message = "El numero de ejemplares es obligatorio")
    private Integer ejemplares;

    @NotNull(message = "El numero de ejemplares prestados es obligatorio")
    private Integer ejemplaresPrestados;

    @NotNull(message = "El numero de ejemplares restantes es obligatorio")
    private Integer ejemplaresRestantes;

    @NotNull(message = "El autor es obligatorio")
    private Integer autorId;

    @NotNull(message = "La editorial es obligatoria")
    private Integer editorialId;
}
