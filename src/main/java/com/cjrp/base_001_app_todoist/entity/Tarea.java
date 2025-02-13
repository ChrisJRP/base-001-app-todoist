package com.cjrp.base_001_app_todoist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numeroTarea;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(length = 250)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority prioridad;

    @Column(nullable = false, name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "fecha_limite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLimite;

    @Column(nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;


}
