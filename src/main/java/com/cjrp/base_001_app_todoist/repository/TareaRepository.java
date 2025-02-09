package com.cjrp.base_001_app_todoist.repository;

import com.cjrp.base_001_app_todoist.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    List<Tarea> findByProyectoId(Integer proyectoId); // lista de tareas por proyecto

    // Buscar tareas por ID de proyecto y estado activo
    List<Tarea> findByProyectoIdAndEstado(Integer proyectoId, Boolean estado);
}
