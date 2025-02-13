package com.cjrp.base_001_app_todoist.controller;

import com.cjrp.base_001_app_todoist.dto.TareaDTO;
import com.cjrp.base_001_app_todoist.dto.TareaResponseDTO;
import com.cjrp.base_001_app_todoist.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//@RequestMapping("/api/v1/tarea")
@RequestMapping("/api/v1/proyecto/{proyectoId}/tareas")
public class TareaController {

    //omitiendo Autowired
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crearTarea(@PathVariable Integer proyectoId, @RequestBody TareaDTO tareaDTO){

        TareaResponseDTO tareaNueva = tareaService.crearTarea(proyectoId,tareaDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tareaNueva);
    }


    @GetMapping("/id/{tareaId}")
    public ResponseEntity<TareaResponseDTO> obtenerTareaPorId(@PathVariable Integer proyectoId, @PathVariable Integer tareaId){
        //validarId(id);
        //TareaResponseDTO tareaEncontrada = tareaService.getTareaById(id);
        TareaResponseDTO tareaEncontrada = tareaService.getTareaById(proyectoId,tareaId);
        return ResponseEntity.ok(tareaEncontrada);
    }

    @GetMapping
    public ResponseEntity<List<TareaResponseDTO>> listaTareasActivasPorProyectoId(@PathVariable Integer proyectoId){
        //validarId(proyectoId);
        List<TareaResponseDTO> listadoTareasActivas = tareaService.listaTareasEstadoActivo(proyectoId);
        return ResponseEntity.ok(listadoTareasActivas);
    }

    @PutMapping("/id/{tareaId}")
    public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Integer proyectoId, @PathVariable Integer tareaId, @RequestBody TareaDTO tareaDTO){
        //validarId(id);
        //TareaResponseDTO tareaActualizada = tareaService.actualizarTarea(id, tareaDTO);
        TareaResponseDTO tareaActualizada = tareaService.actualizarTarea(proyectoId, tareaId, tareaDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("/id/{tareaId}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Integer proyectoId, @PathVariable Integer tareaId){
        //validarId(id);
//        tareaService.eliminarTarea(id);
        tareaService.eliminarTarea(proyectoId, tareaId);
        return ResponseEntity.noContent().build();
    }


    private void validarId(Integer id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo");
        }
    }


    //Obtener tarea por numeroTarea Opt1
    @GetMapping("/{numeroTarea}")
    public ResponseEntity<TareaResponseDTO> obtenerTareaPorNumeroTarea(@PathVariable Integer proyectoId, @PathVariable Integer numeroTarea){
        //validarId(id);
        TareaResponseDTO tareaEncontrada = tareaService.getTareaByNumeroTarea(proyectoId,numeroTarea);
        return ResponseEntity.ok(tareaEncontrada);
    }

    //Actualizar tarea por numeroTarea Opt1
    @PutMapping("/{numeroTarea}")
    public ResponseEntity<TareaResponseDTO> actualizarTareaPorNumeroTarea(@PathVariable Integer proyectoId, @PathVariable Integer numeroTarea, @RequestBody TareaDTO tareaDTO){
        //validarId(id);
        TareaResponseDTO tareaActualizada = tareaService.actualizarTareaPorNumeroTarea(proyectoId, numeroTarea, tareaDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    //Eliminar tarea por numeroTarea Opt1
    @DeleteMapping("/{numeroTarea}")
    public ResponseEntity<Void> eliminarTareaPorNumeroTarea(@PathVariable Integer proyectoId, @PathVariable Integer numeroTarea){
        //validarId(id);
        tareaService.eliminarTareaPorNumeroTarea(proyectoId, numeroTarea);
        return ResponseEntity.noContent().build();
    }



}
