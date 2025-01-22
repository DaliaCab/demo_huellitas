package org.example.demo_huellitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "facturacion")
public class Facturacion {
    @Id
    @Column(name = "numFactura", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCliente", nullable = false)
    private Cliente iDCliente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDEmpleado", nullable = false)
    private Empleado iDEmpleado;

    @Column(name = "totalFactura", nullable = false)
    private Float totalFactura;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "impuestos", nullable = false)
    private Float impuestos;

    @Column(name = "fechaFactura", nullable = false)
    private LocalDate fechaFactura;

    @Column(name = "metodoPago", nullable = false, length = 30)
    private String metodoPago;

}