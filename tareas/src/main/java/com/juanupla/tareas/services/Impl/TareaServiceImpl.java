package com.juanupla.tareas.services.Impl;

import com.juanupla.tareas.entities.TareaEntity;
import com.juanupla.tareas.models.Tarea;
import com.juanupla.tareas.models.Usuario;
import com.juanupla.tareas.repositoriesJpa.TareaJpa;
import com.juanupla.tareas.services.TareaService;
import com.juanupla.tareas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TareaJpa tareaJpa;
    @Autowired
    private UsuarioService usuarioService;

    public Tarea newTarea(String nombreTarea, LocalDateTime fechaLimite, String prioridad, boolean estaCompleta,String descripcion, String nombreUsuario){


        Usuario usuario = usuarioService.getUsuario(nombreUsuario);

        Tarea tarea = new Tarea();
        tarea.setNombre(nombreTarea);
        tarea.setFechaInicio(LocalDateTime.now());
        tarea.setFechaLimite(fechaLimite);
        tarea.setPrioridad(prioridad);
        tarea.setRealizada(estaCompleta);
        tarea.setUsuario(usuario);
        tarea.setDescripcion(descripcion);


        TareaEntity tareaEntity = tareaJpa.save(modelMapper.map(tarea,TareaEntity.class));
        return modelMapper.map(tareaEntity,Tarea.class);


    }
}
