package org.example.demo_huellitas.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;


@Getter
@Setter
@Entity
@Table(name = "cita")
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos nulos

public class Cita {
    @Id
    @Column(name = "idcita", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpaciente", insertable = false, updatable = false) // Solo lectura
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idempleado", insertable = false, updatable = false) // Solo lectura
    private Empleado empleado;

    @Column(name = "fechahora", nullable = false)
    private LocalDateTime fechaHora;

    // Campos para el mapeo directo desde la consulta SQL
    @Column(name = "idpaciente", insertable = true, updatable = true)
    private Integer idPaciente;

    @Column(name = "idempleado", insertable = true, updatable = true)
    private Integer idEmpleado;

    // Campos transitorios para el response
    @Transient
    private String nombrePaciente;

    @Transient
    private String nombreEmpleado;

    // PostLoad para poblar campos transitorios
    @PostLoad
    private void postLoad() {
        if (paciente != null) {
            this.nombrePaciente = paciente.getNombre();
        }
        if (empleado != null) {
            this.nombreEmpleado = empleado.getNombre();
        }
    }
}