package com.cjrp.base_001_app_todoist.controller;


import com.cjrp.base_001_app_todoist.dto.ProyectoDTO;
import com.cjrp.base_001_app_todoist.dto.ProyectoResponseDTO;
import com.cjrp.base_001_app_todoist.service.ProyectoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/api/v1/proyecto")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }


    @PostMapping
    public ResponseEntity<ProyectoResponseDTO> crearProyecto(@RequestBody ProyectoDTO proyectoDTO){

        ProyectoResponseDTO proyectoCreado = proyectoService.crearProyecto(proyectoDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(proyectoCreado);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> obtenerProyectoPorId(@PathVariable Integer id){

        ProyectoResponseDTO proyectoEncontrado = proyectoService.getProyectoById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(proyectoEncontrado);
    }

    @GetMapping("/listaproyectos/{usuarioId}")
    public ResponseEntity<List<ProyectoResponseDTO>> listaProyectosActivosPorUsuario(@PathVariable Integer usuarioId){
        List<ProyectoResponseDTO> listaProyectos = proyectoService.listaProyectosEstadoActivo(usuarioId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listaProyectos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> actualizarProyecto(
            @PathVariable Integer id,
            @RequestBody ProyectoDTO proyectoDTO){

        ProyectoResponseDTO proyectoActualizado = proyectoService.actualizarProyecto(id, proyectoDTO);
        return ResponseEntity
                .ok(proyectoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Integer id){
        proyectoService.eliminarProyecto(id);
        return ResponseEntity.noContent().build();
    }

    private void validarId(Integer id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID debe ser un número positivo");
        }
    }


    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Integer id) {

        proyectoService.elimminarProyecto(id);

        return ResponseEntity.ok("Proyecto eliminado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProyecto(@PathVariable Integer id) {

        proyectoService.elimminarProyecto(id);

        return ResponseEntity.status(HttpStatus.OK).body("Proyecto eliminado exitosamente");
    }
     */





}
