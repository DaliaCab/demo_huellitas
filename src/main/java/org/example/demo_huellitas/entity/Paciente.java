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
    @Column(name = "idpaciente", nullable = false)
    private Integer idpaciente;

    @JoinColumn(name = "idcliente", nullable = false)
    private Integer iDCliente;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "fechanacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "especie", nullable = false, length = 30)
    private String especie;

    @Column(name = "genero", nullable = false, length = 30)
    private String genero;

    @Column(name = "raza", nullable = false, length = 30)
    private String raza;

    @Column(name = "color", nullable = false, length = 30)
    private String color;

    @Column(name = "peso", nullable = false)
    private Double peso;

    @Column(name = "tamano", nullable = false)
    private Double tamano;

    @Column(name = "alergias", nullable = false)
    private String alergias;

    @Column(name = "enfermedadescronicas", nullable = false)
    private String enfermedadesCronicas;

    @Column(name = "vacunas", nullable = false)
    private String vacunas;

    @Column(name = "estado", nullable = false)
    private String estado;
}