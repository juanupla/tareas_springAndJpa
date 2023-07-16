package com.juanupla.tareas.services;

import com.juanupla.tareas.models.Usuario;
import com.juanupla.tareas.models.dtos.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    Usuario singIn(String nombreUsuario, String password);

    String logIn(String nombreUsuario, String password);

    Usuario getUsuario(String nombreusuario);
}
