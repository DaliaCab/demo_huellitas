package org.example.demo_huellitas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;


@Data
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "IDCliente", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "contrasena", nullable = false, length = 30)
    private String contrasena;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "correo", nullable = false, length = 30)
    private String correo;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "fechaAfiliacion")
    private LocalDate fechaAfiliacion;

}