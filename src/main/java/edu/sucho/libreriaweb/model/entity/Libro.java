package edu.sucho.libreriaweb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "libro")
@EqualsAndHashCode
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 64)
    @NotEmpty(message = "El titulo es obligatorio")
    @Size(min = 2, max = 64, message = "Debe tener min 2 caracteres y menos de 64")
    @EqualsAndHashCode.Include
    private String titulo;

    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private Long isbn;

    @EqualsAndHashCode.Include
    private Integer anio;
    @EqualsAndHashCode.Include
    private Integer ejemplares;
    @EqualsAndHashCode.Include
    private Integer ejemplaresPrestados;
    @EqualsAndHashCode.Include
    private Integer ejemplaresRestantes;
    @EqualsAndHashCode.Include
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



    public void actualizarStockPostPrestamo() {
        this.ejemplaresPrestados++;
        this.ejemplaresRestantes = this.ejemplares - this.ejemplaresPrestados;
    }

    public void actualizarStockPostDevolucion() {
        this.ejemplaresPrestados--;
        this.ejemplaresRestantes = this.ejemplares - this.ejemplaresPrestados;
    }
}
