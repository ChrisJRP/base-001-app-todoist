package com.cjrp.base_001_app_todoist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//(1)
// Role. 2 se adhiere el atributo

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Date fecha;

    @Enumerated(EnumType.STRING)
    private Role role;

 /*   @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Proyecto> proyectos;*/


}

