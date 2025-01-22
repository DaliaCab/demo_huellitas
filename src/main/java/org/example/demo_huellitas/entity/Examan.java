package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "examen")
public class Examan {
    @Id
    @Column(name = "IDExamen", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDPaciente", nullable = false)
    private Paciente iDPaciente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDEmpleado", nullable = false)
    private Empleado iDEmpleado;

    @Column(name = "tipoExamen", nullable = false, length = 30)
    private String tipoExamen;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "resultados", length = 30)
    private String resultados;

    @Column(name = "fechaExamen")
    private LocalDate fechaExamen;

    @Column(name = "detalleExamen", length = 50)
    private String detalleExamen;

}