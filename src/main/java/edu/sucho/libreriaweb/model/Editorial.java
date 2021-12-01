package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Min: 2 y Max: 64 caracteres")
    @Column(length = 64, unique = true, nullable = false)
    private String nombre;
    
    //@NotEmpty(message = "El alta es obligatorio")
    private Boolean alta = true;

    //@OneToMany(mappedBy = "editorial")
    //private List<Libro> libros;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editorial editorial = (Editorial) o;
        return nombre.equals(editorial.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
