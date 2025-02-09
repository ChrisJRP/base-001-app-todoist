package com.cjrp.base_001_app_todoist.dto;

import com.cjrp.base_001_app_todoist.entity.Priority;
import com.cjrp.base_001_app_todoist.entity.Proyecto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

// Para devolver datos de una tarea en las respuestas.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TareaResponseDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Priority prioridad;
    private Date fechaCreacion;
    private Date fechaLimite;
    private Boolean estado;
    private Integer proyectoId; //integer

}
