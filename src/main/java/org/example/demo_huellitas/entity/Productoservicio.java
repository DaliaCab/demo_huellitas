package org.example.demo_huellitas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "productoservicio")
public class Productoservicio {
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "valor", nullable = false)
    private Float valor;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

}