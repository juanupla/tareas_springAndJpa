package com.juanupla.tareas.services;

import com.juanupla.tareas.models.Tarea;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface TareaService {
    Tarea newTarea(String nombreTarea, LocalDateTime fechaLimite, String prioridad, boolean estaCompleta,String descripcion, String nombreUsuario);
/*
Creación y gestión de tareas: Una vez que los usuarios hayan iniciado sesión, podrán crear nuevas tareas,
 establecer una fecha límite, asignarles una prioridad y una etiqueta. También podrán editar, marcar como completadas y eliminar tareas existentes.
 */
}
