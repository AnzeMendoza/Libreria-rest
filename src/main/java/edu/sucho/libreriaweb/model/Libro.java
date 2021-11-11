package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    private Long isbn;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_autor")
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_editorial")
    private Editorial editorial;

    public Libro() {
        this.alta=true;
    }
    
    public void actualizarStockPostPrestamo(){
        this.ejemplaresPrestados++;
        this.ejemplaresRestantes = this.ejemplares-this.ejemplaresPrestados;
    }
    public void actualizarStockPostDevolucion(){
        this.ejemplaresPrestados--;
        this.ejemplaresRestantes = this.ejemplares-this.ejemplaresPrestados;
    }
}
