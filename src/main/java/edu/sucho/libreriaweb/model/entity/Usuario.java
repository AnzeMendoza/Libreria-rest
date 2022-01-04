package edu.sucho.libreriaweb.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter


public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    @ManyToOne
    @JoinColumn(nullable = false)
    private Rol rol;

    @Column(nullable = false, unique = true, length = 100)
    private String userPassword;

    @Column(nullable = false, unique = true, length = 64)
    @Email(message = "No es un mail valido")
    private String username;

    @Column(nullable = false)
    private Boolean alta = true;

}
