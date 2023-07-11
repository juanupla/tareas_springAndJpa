package com.juanupla.tareas.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    @NotNull
    private Long id;
    @NotNull
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")//en el config se agrego objectMapper.registerModule(new JavaTimeModule());
    //para que no se rompa la app, pero el formato devuelto es poco amigable. a raiz de eso, esta notacion para darle un formato más amigable
    private LocalDateTime fechaLimite;
    @NotNull
    private String prioridad;
    private String descripcion;
    private Usuario usuario;
    @NotNull
    private boolean realizada;

}
