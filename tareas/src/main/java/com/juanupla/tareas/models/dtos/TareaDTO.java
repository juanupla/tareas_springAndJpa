package com.juanupla.tareas.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.juanupla.tareas.models.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    @NotNull
    private String nombre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")//en el config se agrego objectMapper.registerModule(new JavaTimeModule());
    //para que no se rompa la app, pero el formato devuelto es poco amigable. a raiz de eso, esta notacion para darle un formato más amigable
    private LocalDateTime fechaLimite;
    @NotNull
    private String prioridad;
    private String descripcion;
    private Usuario usuario;
    @NotNull
    private boolean realizada;
}
