package com.cjrp.base_001_app_todoist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//(1)
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

}

