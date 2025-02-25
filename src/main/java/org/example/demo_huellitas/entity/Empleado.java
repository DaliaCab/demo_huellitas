package org.example.demo_huellitas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @Column(name = "IDEmpleado", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "cargo", nullable = false, length = 10)
    private String cargo;

    @Column(name = "numTarjetaProfesional", nullable = false, length = 30)
    private String numTarjetaProfesional;

    @Column(name = "correo", nullable = false, length = 30)
    private String correo;

    @Column(name = "fechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "especialidad", length = 30)
    private String especialidad;

    @Column(name = "contrasena", nullable = false, length = 30)
    private String contrasena;

}