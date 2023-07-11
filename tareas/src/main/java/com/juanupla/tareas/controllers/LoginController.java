package com.juanupla.tareas.controllers;

import com.juanupla.tareas.models.Usuario;
import com.juanupla.tareas.models.dtos.UsuarioDTO;
import com.juanupla.tareas.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;




    @PostMapping("/enter")
    public ResponseEntity<Usuario> signIn(@RequestBody @Valid UsuarioDTO usuario){
        try {
            return ResponseEntity.ok(usuarioService.singIn(usuario.getNombreUsuario(), usuario.getPassword()));
        }
        catch (Error error){
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> logIn(@RequestBody @Valid UsuarioDTO usuario){
        try {
            return ResponseEntity.ok(usuarioService.logIn(usuario.getNombreUsuario(),usuario.getPassword()));
        }
        catch (Error error){
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }


}
