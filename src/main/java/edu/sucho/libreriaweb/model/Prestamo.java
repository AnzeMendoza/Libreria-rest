package edu.sucho.libreriaweb.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Calendar;
import java.util.Date;
<<<<<<< HEAD
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
=======
import java.util.GregorianCalendar;



>>>>>>> 543607ea0d2f3406fea0670c3f348c69efe4b0ec

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "prestamo")
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean alta = true;

    @NotNull(message = "Cliente no puede ser nulo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    @NotNull(message = "fechaDevolución no puede ser nulo")
    @Temporal(TemporalType.DATE)

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

    @FutureOrPresent(message = "La fecha debe ser actual o posterior a la de hoy")

    private Calendar fechaDevolucion;

    @NotNull(message = "fechaPrestamo no puede ser nulo")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)

    @PastOrPresent(message = "La fecha debe ser actual o anterior a la de hoy")
    private Calendar fechaPrestamo;

    @NotNull(message = "Cliente no puede ser nulo")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_libro")
    private Libro libro;

    private static final long serialVersionUID = 1L;
}
