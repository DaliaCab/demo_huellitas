package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @Column(name = "IDPaciente", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCliente", nullable = false)
    private Cliente iDCliente;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "fechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "especie", nullable = false, length = 30)
    private String especie;

    @Column(name = "raza", nullable = false, length = 30)
    private String raza;

}