package edu.sucho.libreriaweb.model;

import java.io.Serializable;
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
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Integer id;
    @Column(length = 8, unique = true, nullable = false ,updatable = false)
    private Long documento;

    @Column(length = 64,nullable = false)
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String nombre;

    @Column(length = 64,nullable = false)
    @NotEmpty(message = "El apellido es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener mas de 2 digitos y menos de 64")
    @Pattern(regexp = "[a-zA-Z ]{2,64}", message = "Debe contener solo letras.")
    private String apellido;

    @Column(length = 20, nullable = false)
    @NotEmpty(message = "El telefono es obligatorio")
    private String telefono;
    
    @Column(nullable = false)
    private Boolean alta = true;
    
}
