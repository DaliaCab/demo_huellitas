package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cita")
public class Cita {
    @Id
    @Column(name = "IDCita", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPaciente", nullable = false)
    private Paciente iDPaciente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDEmpleado", nullable = false)
    private Empleado iDEmpleado;

    @Column(name = "fechaHora", nullable = false)
    private Instant fechaHora;

}