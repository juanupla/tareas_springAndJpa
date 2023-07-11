package com.juanupla.tareas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuarios")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombreUsuario;
    @Column
    private String password;

    @OneToMany(mappedBy = "usuario")
    List<TareaEntity> tareas;
}
