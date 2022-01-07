package edu.sucho.libreriaweb.model.entity;

import lombok.*;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte Id;

    @Column(length = 32,nullable = false,unique = true)
    private String type;
}
