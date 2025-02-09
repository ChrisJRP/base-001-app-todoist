package com.cjrp.base_001_app_todoist.service;

import com.cjrp.base_001_app_todoist.dto.ProyectoDTO;
import com.cjrp.base_001_app_todoist.dto.ProyectoResponseDTO;

import java.util.List;

public interface ProyectoService {

    // Crear un proyecto
    public ProyectoResponseDTO crearProyecto(ProyectoDTO proyectoDTO);

    // Obtener un proyecto por ID
    public ProyectoResponseDTO getProyectoById(Integer id);

    // Obtener todos los proyectos de un usuario
    public List<ProyectoResponseDTO> getProyectosByUsuarioId(Integer usuarioId);

    //Lista de tareas por estado Activo
    public  List<ProyectoResponseDTO> listaProyectosEstadoActivo(Integer usuarioId);

    // Actualizar un proyecto
    public ProyectoResponseDTO actualizarProyecto(Integer id, ProyectoDTO proyectoDTO);

    // Eliminar un proyecto (eliminación lógica)
    public void eliminarProyecto(Integer id);
}
