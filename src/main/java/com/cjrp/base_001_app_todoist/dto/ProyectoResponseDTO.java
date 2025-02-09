package com.cjrp.base_001_app_todoist.dto;

import com.cjrp.base_001_app_todoist.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

// Para devolver datos de un proyecto en las respuestas
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoResponseDTO {

    private Integer id;
    private String nombre;
    private String color;
    private Boolean estado;     //eliminacion
    private Integer usuarioId;  //usuario creador del proyecto. a diferencia del original es Integer
    private List<TareaResponseDTO> tareas; //Listado de tareas del proyecto
}
