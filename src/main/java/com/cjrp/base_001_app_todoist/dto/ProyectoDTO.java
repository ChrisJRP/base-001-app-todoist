package com.cjrp.base_001_app_todoist.dto;

import com.cjrp.base_001_app_todoist.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO para crear y actualizar el proyecto
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoDTO {

    private Integer id;
    private String nombre;
    private String color;
    private Boolean estado;
    private Integer usuarioId; //integer
}