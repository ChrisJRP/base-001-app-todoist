package com.cjrp.base_001_app_todoist.service.impl;

import com.cjrp.base_001_app_todoist.dto.ProyectoDTO;
import com.cjrp.base_001_app_todoist.dto.ProyectoResponseDTO;
import com.cjrp.base_001_app_todoist.dto.TareaResponseDTO;
import com.cjrp.base_001_app_todoist.entity.Proyecto;
import com.cjrp.base_001_app_todoist.entity.Tarea;
import com.cjrp.base_001_app_todoist.entity.Usuario;
import com.cjrp.base_001_app_todoist.repository.ProyectoRepository;
import com.cjrp.base_001_app_todoist.repository.TareaRepository;
import com.cjrp.base_001_app_todoist.repository.UsuarioRepository;
import com.cjrp.base_001_app_todoist.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override // Crear un proyecto
    public ProyectoResponseDTO crearProyecto(ProyectoDTO proyectoDTO) {

        // Obtener el usuario desde la base de datos
        Usuario usuario = usuarioRepository.findById(proyectoDTO.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(proyectoDTO.getNombre());
        proyecto.setColor(proyectoDTO.getColor());
        proyecto.setEstado(true);
        proyecto.setUsuario(usuario);

        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);

        return mapToProyectoResponseDTO(proyectoGuardado);
    }

    @Override  // Obtener un proyecto por ID
    public ProyectoResponseDTO getProyectoById(Integer id) {

        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Proyecto no encontrado"));

        return mapToProyectoResponseDTO(proyecto);
    }

    @Override // Obtener todos los proyectos de un usuario
    public List<ProyectoResponseDTO> getProyectosByUsuarioId(Integer usuarioId) {

        List<Proyecto> proyectos = proyectoRepository.findByUsuarioId(usuarioId);
        return proyectos.stream()
                .map(this::mapToProyectoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override // listado de proyectos con esstado activo desde id Usuario
    public List<ProyectoResponseDTO> listaProyectosEstadoActivo(Integer usuarioId) {

        List<Proyecto> proyectosActivos = proyectoRepository.findByUsuarioIdAndEstado(usuarioId, true);
        return proyectosActivos.stream()
                .map(this::mapToProyectoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override // Actualizar un proyecto
    public ProyectoResponseDTO actualizarProyecto(Integer id, ProyectoDTO proyectoDTO) {

        //Usuario usuario = usuarioRepository.findById(proyectoDTO.getUsuarioId()).orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        Proyecto proyectoEncontrado = proyectoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Proyecto no encontrado"));

        proyectoEncontrado.setNombre(proyectoDTO.getNombre());
        proyectoEncontrado.setColor(proyectoDTO.getColor());
        proyectoEncontrado.setEstado(proyectoDTO.getEstado());
       // proyecto.setUsuario(usuario);

        Proyecto proyectoActualizado = proyectoRepository.save(proyectoEncontrado);
        return mapToProyectoResponseDTO(proyectoActualizado);
    }

    @Override // Eliminar un proyecto (eliminación lógica)
    public void eliminarProyecto(Integer id) {

        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Proyecto no encontrado"));
        proyecto.setEstado(false);
        proyectoRepository.save(proyecto);
    }

    // Método para convertir Proyecto a ProyectoResponseDTO
    private ProyectoResponseDTO mapToProyectoResponseDTO(Proyecto proyecto){
        return ProyectoResponseDTO.builder()
                .id(proyecto.getId())
                .nombre(proyecto.getNombre())
                .color(proyecto.getColor())
                .estado(proyecto.getEstado())
                .usuarioId(proyecto.getUsuario().getId())
                .tareas(tareaRepository.findByProyectoId(proyecto.getId()).stream()
                        .map(this::mapToTareaResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    // Método para convertir Tarea a TareaResponseDTO
    private TareaResponseDTO mapToTareaResponseDTO(Tarea tarea){
        return TareaResponseDTO.builder()
                .id(tarea.getId())
                .nombre(tarea.getNombre())
                .descripcion(tarea.getDescripcion())
                .prioridad(tarea.getPrioridad())
                .fechaCreacion(tarea.getFechaCreacion())
                .fechaLimite(tarea.getFechaLimite())
                .estado(tarea.getEstado())
                .proyectoId(tarea.getProyecto().getId())
                .build();
    }


    // Se podría usar para evitar proyectoRepository.findById(proyectoId)
    private Proyecto obtenerProyectoPorId(Integer proyectoId) {
        return proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado"));
    }


    /*
    private ProyectoResponseDTO mapToProyectoResponseDTO(Proyecto proyecto){
    return ProyectoResponseDTO.builder()
            .id(proyecto.getId())
            .nombre(proyecto.getNombre())
            .color(proyecto.getColor())
            .estado(proyecto.getEstado())
            .usuarioId(proyecto.getUsuario().getId())
            .tareas(tareaRepository.findByProyectoIdAndEstado(proyecto.getId(), true).stream()
                    .map(this::mapToTareaResponseDTO)
                    .collect(Collectors.toList()))
            .build();
    }
     */

}
