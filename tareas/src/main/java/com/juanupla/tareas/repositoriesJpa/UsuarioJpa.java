package com.juanupla.tareas.repositoriesJpa;

import com.juanupla.tareas.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioJpa extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByNombreUsuarioAndPassword(String nombreUsuario, String passwprd);
    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);
}
