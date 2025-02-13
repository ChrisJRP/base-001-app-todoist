package com.cjrp.base_001_app_todoist.service;

import com.cjrp.base_001_app_todoist.dto.TareaDTO;
import com.cjrp.base_001_app_todoist.dto.TareaResponseDTO;
import com.cjrp.base_001_app_todoist.entity.Proyecto;
import com.cjrp.base_001_app_todoist.entity.Tarea;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TareaService {

    //Crear tarea
    public TareaResponseDTO crearTarea(Integer proyectoId, TareaDTO tareaDTO);

    // Obtener tarea por id
    public TareaResponseDTO getTareaById(Integer proyectoId, Integer tareaId);

    // Obtener todas las tareas de un proyecto
    public List<TareaResponseDTO> getTareasByProyectoId(Integer proyectoId);

    // Buscar tareas por ID de proyecto y estado activo
    public List<TareaResponseDTO> listaTareasEstadoActivo(Integer proyectoId);

    // Actualizar tarea
    public TareaResponseDTO actualizarTarea(Integer proyectoId, Integer tareaId,  TareaDTO tareaDTO);

    // Eliminar una tarea
    public void eliminarTarea(Integer proyectoId, Integer tareaId);


    // Obtener tarea por numeroTarea Opt1
    public  TareaResponseDTO getTareaByNumeroTarea(Integer proyectoId, Integer numeroTarea);

    // Actualizar Tarea por numeroTarea Opt1
    public TareaResponseDTO actualizarTareaPorNumeroTarea(Integer proyectoId, Integer numeroTarea, TareaDTO tareaDTO);

    // Eliminar una tarea por numeroTarea Opt1
    public void eliminarTareaPorNumeroTarea(Integer proyectoId, Integer numeroTarea);
}
