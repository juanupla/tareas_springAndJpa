package com.juanupla.tareas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column
    private LocalDateTime fechaInicio;
    @Column
    private LocalDateTime fechaLimite;
    @Column
    private String prioridad;
    @Column
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "tarea_usuario_id")
    private UsuarioEntity usuario;
    @Column
    private boolean realizada;

}
