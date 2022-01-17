package edu.sucho.libreriaweb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    @NotEmpty(message = "El titulo es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener min 2 caracteres y menos de 64")
    private String titulo;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "El ISBN es obligatorio")
    @Size(min = 10, max = 13, message = "Debe tener min 10 caracteres y menos de 13")
    @Pattern(regexp = "[\\d*\\-]{10,13}", message = "Debe contener solo numeros.")
    private Long isbn;

    @Column
    @NotEmpty(message = "Es recomendable ingresar el año")
    @Size(min = 4, max = 4, message = "Debe tener 4 caracteres")
    @Pattern(regexp = "[\\d*\\-]{4}", message = "Debe contener solo numeros.")
    private Integer anio;

    @Column
    @NotEmpty(message = "El numero de ejempleres es obligatorio")
    private Integer ejemplares;

    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;

    @Column(nullable = false)
    private Boolean alta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_autor")
    @NotNull(message = "Autor no puede ser nulo")
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_editorial")
    @NotNull(message = "Editorial no puede ser nulo")
    private Editorial editorial;

    public Libro() {
        this.alta = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id) && Objects.equals(titulo, libro.titulo) && Objects.equals(isbn, libro.isbn) && Objects.equals(anio, libro.anio) && Objects.equals(ejemplares, libro.ejemplares) && Objects.equals(ejemplaresPrestados, libro.ejemplaresPrestados) && Objects.equals(ejemplaresRestantes, libro.ejemplaresRestantes) && Objects.equals(alta, libro.alta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, isbn, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta);
    }

   }
