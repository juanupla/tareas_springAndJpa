package com.juanupla.tareas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tareas")
@Entity
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
    private LocalDate fechaInicio;
    @Column
    private LocalDate fechaLimite;
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
