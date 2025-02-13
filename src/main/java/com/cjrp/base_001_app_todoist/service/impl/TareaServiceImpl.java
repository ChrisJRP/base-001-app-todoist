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

    private final ProyectoRepository proyectoRepository;
    private final TareaRepository tareaRepository;

    public TareaServiceImpl(ProyectoRepository proyectoRepository, TareaRepository tareaRepository) {
        this.proyectoRepository = proyectoRepository;
        this.tareaRepository = tareaRepository;
    }

    @Override //Crear tarea
    public TareaResponseDTO crearTarea(Integer proyectoId, TareaDTO tareaDTO) {

        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrada"));


       if(tareaDTO.getFechaLimite() == null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se ha ingresado Fecha límite");
       }

       if(tareaDTO.getFechaLimite().before(new Date())){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"La fecha límite no puede ser anterior a la fecha de creación.");
       }

        // Obtener el número más alto de tarea en este proyecto
        Integer ultimoNumero = tareaRepository.findMaxNumeroTareaByProyectoId(proyectoId);
        int nuevoNumero = (ultimoNumero == null) ? 1 : ultimoNumero + 1;


        Tarea tarea = Tarea.builder()
                .numeroTarea(nuevoNumero)
                .nombre(tareaDTO.getNombre())
                .descripcion(tareaDTO.getDescripcion())
                .prioridad(tareaDTO.getPrioridad())
                .fechaCreacion(new Date())
                .fechaLimite(tareaDTO.getFechaLimite())
                .estado(true)
                .proyecto(proyecto)
                .build();
/*
        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        tarea.setFechaCreacion(new Date());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setEstado(true);
        tarea.setProyecto(proyecto);
*/

        Tarea tareaNueva = tareaRepository.save(tarea);

        return mapToTareaResponseDTO(tareaNueva);
    }

    @Override // Obtener tarea por id
    public TareaResponseDTO getTareaById(Integer proyectoId, Integer tareaId) {

        Tarea tareaEncontrada = tareaRepository.findByIdAndProyectoId(tareaId, proyectoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        return mapToTareaResponseDTO(tareaEncontrada);
    }

    @Override //Lista de tareas de un proyecto
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
    public TareaResponseDTO actualizarTarea(Integer proyectoId, Integer tareaId, TareaDTO tareaDTO) {

        //Tarea tarea = tareaRepository.findById(tareaId)
        Tarea tarea = tareaRepository.findByIdAndProyectoId(tareaId, proyectoId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        //tarea.setEstado(tareaDTO.getEstado());
        //tarea.setFechaCreacion(tareaDTO.getFechaCreacion());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        //no tendrías porque actualizar el proyecto del que viene

        Tarea tareaActualizada = tareaRepository.save(tarea);

        return mapToTareaResponseDTO(tareaActualizada);
    }

    @Override //Eliminar tarea
    public void eliminarTarea(Integer proyectoId, Integer tareaId) {

        //Tarea tareaEncontrada = tareaRepository.findById(tareaId)
        Tarea tareaEncontrada = tareaRepository.findByIdAndProyectoId(tareaId, proyectoId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tareaEncontrada.setEstado(false);
        tareaRepository.save(tareaEncontrada);
    }


    // mapeo para convertir Tarea en TareaResponseDTO
    private TareaResponseDTO mapToTareaResponseDTO(Tarea tarea){
        return TareaResponseDTO.builder()
                .id(tarea.getId())
                .numeroTarea(tarea.getNumeroTarea())
                .nombre(tarea.getNombre())
                .descripcion(tarea.getDescripcion())
                .prioridad(tarea.getPrioridad())
                .fechaCreacion(tarea.getFechaCreacion())
                .fechaLimite(tarea.getFechaLimite())
                .estado(tarea.getEstado())
                .proyectoId(tarea.getProyecto().getId())
                .build();

    }

    // No se usa, pero sería bueno en vez de tareaRepository.findById(id)
    private Tarea obtenerTareaPorId(Integer id) {
        return tareaRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));
    }



    @Override // Obtener tarea por numeroTarea Opt1
    public TareaResponseDTO getTareaByNumeroTarea(Integer proyectoId, Integer numeroTarea) {
        Tarea tareaEncontrada = tareaRepository.findByProyectoIdAndNumeroTarea(proyectoId, numeroTarea)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        return mapToTareaResponseDTO(tareaEncontrada);
    }

    @Override // Actualizar Tarea por numero Tarea Opt1
    public TareaResponseDTO actualizarTareaPorNumeroTarea(Integer proyectoId, Integer numeroTarea, TareaDTO tareaDTO) {

        Tarea tarea = tareaRepository.findByProyectoIdAndNumeroTarea(proyectoId, numeroTarea)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tarea.setNombre(tareaDTO.getNombre());
        tarea.setDescripcion(tareaDTO.getDescripcion());
        //tarea.setEstado(tareaDTO.getEstado());
        //tarea.setFechaCreacion(tareaDTO.getFechaCreacion());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setPrioridad(tareaDTO.getPrioridad());
        //no tendrías porque actualizar el proyecto del que viene
        Tarea tareaActualizada = tareaRepository.save(tarea);
        return mapToTareaResponseDTO(tareaActualizada);
    }

    @Override //Eliminar tarea por numero Tarea Opt1
    public void eliminarTareaPorNumeroTarea(Integer proyectoId, Integer numeroTarea) {

        Tarea tareaEncontrada = tareaRepository.findByProyectoIdAndNumeroTarea(proyectoId, numeroTarea)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarea no encontrada"));

        tareaEncontrada.setEstado(false);
        tareaRepository.save(tareaEncontrada);
    }








}
