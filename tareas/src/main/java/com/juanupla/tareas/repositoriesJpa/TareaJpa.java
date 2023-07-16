package com.juanupla.tareas.repositoriesJpa;

import com.juanupla.tareas.entities.TareaEntity;
import com.juanupla.tareas.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TareaJpa extends JpaRepository<TareaEntity, Long> {

    //@Query("SELECT m FROM TareaEntity m WHERE m.nombreUsuario.id = :userId")
    Optional<List<TareaEntity>> findAllByUsuarioId(Long UsuarioId);

    Optional<List<TareaEntity>> findAllByNombre(String nombre);

    Optional<List<TareaEntity>> findAllByPrioridad(String prioridad);
}
