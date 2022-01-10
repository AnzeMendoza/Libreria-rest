package edu.sucho.libreriaweb.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rol rol;

    @Column(nullable = false, unique = true, length = 100)
    private String password;

    @Column(nullable = false, unique = true, length = 64)
    @Email(message = "No es un mail valido")
    private String username;

    @Column(nullable = false)
    private Boolean alta = true;
}

