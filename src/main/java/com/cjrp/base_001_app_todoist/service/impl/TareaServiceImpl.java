package com.cjrp.base_001_app_todoist.service.impl;

import com.cjrp.base_001_app_todoist.dto.TareaDTO;
import com.cjrp.base_001_app_todoist.dto.TareaResponseDTO;
import com.cjrp.base_001_app_todoist.entity.Proyecto;
import com.cjrp.base_001_app_todoist.entity.Tarea;
import com.cjrp.base_001_app_todoist.repository.ProyectoRepository;
import com.cjrp.base_001_app_todoist.repository.TareaRepository;
import com.cjrp.base_001_app_todoist.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    TareaRepository tareaRepository;

    @Override //Crear tarea
    public TareaResponseDTO crearTarea(TareaDTO tareaDTO) {

        Proyecto proyecto = proyectoRepository.findById(tareaDTO.getProyectoId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrada"));

        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        tarea.setFechaCreacion(new Date());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setEstado(true);
        tarea.setProyecto(proyecto);

        Tarea tareaNueva = tareaRepository.save(tarea);

        return mapToTareaResponseDTO(tareaNueva);
    }

    @Override // Obtener tarea por id
    public TareaResponseDTO getTareaById(Integer Id) {

        Tarea tareaEncontrada = tareaRepository.findById(Id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        return mapToTareaResponseDTO(tareaEncontrada);
    }

    @Override //Lista de tareas generales
    public List<TareaResponseDTO> getTareasByProyectoId(Integer proyectoId) {
        List<Tarea> tareas = tareaRepository.findByProyectoId(proyectoId);
        return tareas.stream()
                // .filter(tarea -> tarea.getEstado()) // Filtrar solo tareas activas
                .map(this::mapToTareaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override // Lista de tareas Activas
    public List<TareaResponseDTO> listaTareasEstadoActivo(Integer proyectoId) {

        List<Tarea> tareasActivas = tareaRepository.findByProyectoIdAndEstado(proyectoId, true);

        return tareasActivas.stream()
                .map(this::mapToTareaResponseDTO)
                .collect(Collectors.toList());
    }

    @Override // Actualizar Tarea
    public TareaResponseDTO actualizarTarea(Integer id, TareaDTO tareaDTO) {

        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setEstado(tareaDTO.getEstado());
        //tarea.setFechaCreacion(tareaDTO.getFechaCreacion());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        //no tendrías porque actualizar el proyecto del que viene

        Tarea tareaActualizada = tareaRepository.save(tarea);

        return mapToTareaResponseDTO(tareaActualizada);
    }

    @Override //Eliminar tarea
    public void eliminarTarea(Integer id) {

        Tarea tareaEncontrada = tareaRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tareaEncontrada.setEstado(false);
        tareaRepository.save(tareaEncontrada);
    }


    // mapeo para convertir Tarea en TareaResponseDTO
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

    // en vez de tareaRepository.findById(id)
    private Tarea obtenerTareaPorId(Integer id) {
        return tareaRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));
    }
}
