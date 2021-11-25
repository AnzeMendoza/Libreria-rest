package edu.sucho.libreriaweb.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "editorial")
public class Editorial {

    @Id
    @NotNull(message = "El id de Editorial no puede ser nulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 2, max = 64, message = "Min: 2 y Max: 64 caracteres")
    @Column(length = 64, unique = true, nullable = false)
    private String nombre;
    
    //@NotEmpty(message = "El alta es obligatorio")
    private Boolean alta = true;

<<<<<<< HEAD
    //@OneToMany(mappedBy = "editorial")
    //private List<Libro> libros;
}
=======
/*    @OneToMany(mappedBy = "editorial")
    private List<Libro> libros;*/
}
>>>>>>> bcb4a8676ec247d3aaea56193bf8d3219ea2d1f1
