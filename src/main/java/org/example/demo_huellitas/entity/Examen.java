package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "examen")
public class Examen {
    @Id
    @Column(name = "idexamen", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpaciente", nullable = false)
    private Paciente iDPaciente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idempleado", nullable = false)
    private Empleado iDEmpleado;

    @Column(name = "tipoexamen", nullable = false, length = 30)
    private String tipoExamen;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "resultados", length = 30)
    private String resultados;

    @Column(name = "fechaexamen")
    private LocalDate fechaExamen;

    @Column(name = "detalleexamen", length = 50)
    private String detalleExamen;

}