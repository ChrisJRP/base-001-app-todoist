package com.cjrp.base_001_app_todoist.dto;

import com.cjrp.base_001_app_todoist.entity.Priority;
import com.cjrp.base_001_app_todoist.entity.Proyecto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

// DTO para crear o actualizar una tarea.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Priority prioridad;
    private Date fechaCreacion;
    private Date fechaLimite;
    private Boolean estado;
    private Integer proyectoId; //integer
}
