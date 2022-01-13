package edu.sucho.libreriaweb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import lombok.EqualsAndHashCode;

@Entity
@Getter
@Setter
@Table(name = "editorial")
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64, unique = true, nullable = false)
    private String nombre;
    
    private Boolean alta = true;

    @OneToMany(mappedBy = "editorial")
    private List<Libro> libros;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editorial editorial = (Editorial) o;
        return Objects.equals(nombre, editorial.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
