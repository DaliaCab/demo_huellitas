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
    @Column(name = "numfactura", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente iDCliente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idempleado", nullable = false)
    private Empleado iDEmpleado;

    @Column(name = "totalfactura", nullable = false)
    private Float totalFactura;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "impuestos", nullable = false)
    private Float impuestos;

    @Column(name = "fechafactura", nullable = false)
    private LocalDate fechaFactura;

    @Column(name = "metodopago", nullable = false, length = 30)
    private String metodoPago;

}