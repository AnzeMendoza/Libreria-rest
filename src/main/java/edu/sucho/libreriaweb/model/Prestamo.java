package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @PastOrPresent(message = "La fecha debe ser actual o anterior a la de hoy")
    private Date fechaPrestamo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @FutureOrPresent(message = "La fecha debe ser actual o posterior a la de hoy")
    private Date fechaDevolucion;

    private Boolean alta = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_libro")
    private Libro libro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;
}
