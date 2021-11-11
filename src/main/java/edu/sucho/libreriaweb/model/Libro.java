package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

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

    @PastOrPresent(message = "Debe ingresar un año menor al presente.")
    @DateTimeFormat(pattern = "yyyy")
    @Temporal(TemporalType.DATE)
    private Date anio;

    @Range(min = 0, message = "Debe ser mayor 0")
    @Column(nullable = false)
    private Integer ejemplares;

    @Range(min = 0,  message = "Debe ser mayor 0")
    @Column(nullable = false)
    private Integer ejemplaresPrestados;

    @Range(min = 0, message = "Debe ser mayor 0")
    @Column(nullable = false)
    private Integer ejemplaresRestantes;

    private Boolean alta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_autor")
    //@Column(nullable = false)
    private Autor autor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_editorial")
    //@Column(nullable = false)
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
