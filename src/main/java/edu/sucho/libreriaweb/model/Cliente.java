package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long documento;

    @Column(length = 64)
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String nombre;

    @Column(length = 64)
    @NotEmpty(message = "El apellido es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String apellido;

    @Column(length = 64)
    private String telefono;

    private Boolean alta = true;
}
