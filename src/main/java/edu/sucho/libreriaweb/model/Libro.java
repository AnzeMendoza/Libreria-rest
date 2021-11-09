package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 64)
    private String titulo;

    private Long isbn;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_autor")
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_editorial")
    private Editorial editorial;

}
