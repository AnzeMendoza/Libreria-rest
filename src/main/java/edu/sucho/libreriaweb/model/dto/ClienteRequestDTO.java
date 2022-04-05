package edu.sucho.libreriaweb.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
public class ClienteRequestDTO {

    @Column(length = 8, unique = true, nullable = false ,updatable = false)
    private long documento;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String nombre;

    @NotEmpty(message = "El apellido es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String apellido;

    @NotEmpty(message = "El telefono es obligatorio")
    @Pattern(regexp = "^\\d+$", message = "Debe contener solo numeros.")
    @Size(min = 8, max = 20, message = "Debe tener mas de 8 digitos y menos de 20")
    private String telefono;

    @NotEmpty(message = "El username es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Email(message = "El username no cumple con el formato.")
    private String username;

    @NotEmpty(message = "El password es obligatorio")
    @Size(min = 8, max = 15, message = "Debe tener mas de 2 digitos y menos de 8")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$",
            message = "Minimo 8 caracteres\n" +
                    "Maximo 15\n" +
                    "Al menos una letra mayúscula\n" +
                    "Al menos una letra minucula\n" +
                    "Al menos un numero\n" +
                    "No espacios en blanco\n" +
                    "Al menos 1 de los siguientes caracteres especiales: @$!%*?& .")
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private int roleId;
}