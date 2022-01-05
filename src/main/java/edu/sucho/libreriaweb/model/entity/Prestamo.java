package edu.sucho.libreriaweb.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prestamo")
@EqualsAndHashCode
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @EqualsAndHashCode.Include
    private Boolean alta = true;

    @NotNull(message = "Cliente no puede ser nulo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @NotNull(message = "fechaDevolución no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "La fecha debe ser actual o posterior a la de hoy")
    @EqualsAndHashCode.Include
    private Calendar fechaDevolucion;

    @NotNull(message = "fechaPrestamo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "La fecha debe ser actual o anterior a la de hoy")
    @EqualsAndHashCode.Include
    private Calendar fechaPrestamo;

    @NotNull(message = "Cliente no puede ser nulo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_libro")
    private Libro libro;

}
