package com.juanupla.tareas.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanupla.tareas.models.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    @NotNull
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaLimite;
    @NotNull
    private String prioridad;
    private String descripcion;
    @NotNull
    private Usuario usuario;
    @NotNull
    private boolean realizada;
}
