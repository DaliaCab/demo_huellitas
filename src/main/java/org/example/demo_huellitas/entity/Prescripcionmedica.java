package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "prescripcionmedica")
public class Prescripcionmedica {
    @Id
    @Column(name = "idprescripcionmedica", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idempleado", nullable = false)
    private Empleado iDEmpleado;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpaciente", nullable = false)
    private Paciente iDPaciente;

    @Column(name = "notas", nullable = false, length = 50)
    private String notas;

    @Column(name = "indicaciones", length = 200)
    private String indicaciones;

}