package edu.sucho.libreriaweb.model.entity;

import java.util.Objects;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cliente")
@Builder
public class Cliente {

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

    @Column
    private String username;

    @Column
    private String password;

    @ManyToOne
    private Role role;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.documento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        return true;
    }
    
}
