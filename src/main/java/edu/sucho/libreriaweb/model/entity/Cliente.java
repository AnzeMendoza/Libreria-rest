package edu.sucho.libreriaweb.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
@EqualsAndHashCode

@Table(name = "cliente")

public class Cliente extends Usuario {

    @Column(length = 8, unique = true, nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private Long documento;

    @Column(length = 64, nullable = false)
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String nombre;

    @Column(length = 64, nullable = false)
    @NotEmpty(message = "El apellido es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String apellido;

    @Column(length = 20, nullable = false)
    @NotEmpty(message = "El telefono es obligatorio")
    private String telefono;

    public Cliente(Integer Id, Rol rol, String userPassword, @Email(message = "No es un mail valido") String username, Boolean alta) {
        super(Id, rol, userPassword, username, alta);
    }
}
