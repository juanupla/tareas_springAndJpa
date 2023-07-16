package com.juanupla.tareas.models.dtos.comandoTarea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class comandoTarea {
    private String nombre;
    private String prioridad;
}
