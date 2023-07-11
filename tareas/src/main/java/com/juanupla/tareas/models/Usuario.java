package com.juanupla.tareas.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @NotNull
    private Long id;
    @NotNull
    private String nombreUsuario;
    @NotNull
    private String password;
}
