package com.cjrp.base_001_app_todoist.repository;

import com.cjrp.base_001_app_todoist.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByProyectoId(Integer proyectoId); // lista de tareas por proyecto

    // Buscar tareas por ID de proyecto y estado activo
    List<Tarea> findByProyectoIdAndEstado(Integer proyectoId, Boolean estado);


    Optional<Tarea> findByIdAndProyectoId(Integer id, Integer proyectoId);

    // Encontrar el número más alto de tarea en un proyecto
    @Query("SELECT MAX(t.numeroTarea) FROM Tarea t WHERE t.proyecto.id = :proyectoId")
    Integer findMaxNumeroTareaByProyectoId(@Param("proyectoId") Integer proyectoId);

    // Buscar tarea por proyecto y número de tarea dentro del proyecto
    Optional<Tarea> findByProyectoIdAndNumeroTarea(Integer proyectoId, Integer numeroTarea);


}
