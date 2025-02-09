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
@RequestMapping("/api/v1/tarea")
public class TareaController {

    //omitiendo Autowired
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @PostMapping
    public ResponseEntity<TareaResponseDTO> crearTarea(@RequestBody TareaDTO tareaDTO){

        TareaResponseDTO tareaNueva = tareaService.crearTarea(tareaDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tareaNueva);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> obtenerTareaPorId(@PathVariable Integer id){
        //validarId(id);
        TareaResponseDTO tareaEncontrada = tareaService.getTareaById(id);
        return ResponseEntity.ok(tareaEncontrada);
    }

    @GetMapping("/listatareas/{proyectoId}")
    public ResponseEntity<List<TareaResponseDTO>> listaTareasActivasPorProyectoId(@PathVariable Integer proyectoId){
        //validarId(proyectoId);
        List<TareaResponseDTO> listadoTareasActivas = tareaService.listaTareasEstadoActivo(proyectoId);
        return ResponseEntity.ok(listadoTareasActivas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaResponseDTO> actualizarTarea(@PathVariable Integer id, @RequestBody TareaDTO tareaDTO){
        //validarId(id);
        TareaResponseDTO tareaActualizada = tareaService.actualizarTarea(id, tareaDTO);
        return ResponseEntity.ok(tareaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Integer id){
        //validarId(id);
        tareaService.eliminarTarea(id);
        return ResponseEntity.noContent().build();
    }


    private void validarId(Integer id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo");
        }
    }




}
