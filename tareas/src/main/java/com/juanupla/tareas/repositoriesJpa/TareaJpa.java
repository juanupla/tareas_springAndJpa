package com.juanupla.tareas.repositoriesJpa;

import com.juanupla.tareas.entities.TareaEntity;
import com.juanupla.tareas.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaJpa extends JpaRepository<TareaEntity, Long> {
}
