package com.cjrp.base_001_app_todoist.repository;

import com.cjrp.base_001_app_todoist.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    List<Proyecto> findByUsuarioId(Integer UsuarioId);

    List<Proyecto> findByUsuarioIdAndEstado(Integer usuarioId, Boolean estado);
}

/*
    findBy: Indica que es una consulta de búsqueda.
    Usuario: Hace referencia a la propiedad usuario en la entidad Proyecto.
    Id: Hace referencia a la propiedad id en la entidad Usuario.
 */
