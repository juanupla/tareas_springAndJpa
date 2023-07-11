package com.juanupla.tareas.services.Impl;

import com.juanupla.tareas.entities.UsuarioEntity;
import com.juanupla.tareas.models.Usuario;
import com.juanupla.tareas.models.dtos.UsuarioDTO;
import com.juanupla.tareas.repositoriesJpa.UsuarioJpa;
import com.juanupla.tareas.services.UsuarioService;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.Optional;
@Service
public class UsuarioServiceImpl implements UsuarioService
{

    @Autowired
    private UsuarioJpa usuariosJpa;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Usuario singIn(String nombreUsuario, String password){

        Optional<UsuarioEntity> user = usuariosJpa.findByNombreUsuario(nombreUsuario);
        if(user.isEmpty()){
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setPassword(password);
            UsuarioEntity userEntitySaved = usuariosJpa.save(modelMapper.map(usuario,UsuarioEntity.class));

            return modelMapper.map(userEntitySaved,Usuario.class);
        }
        else {
            throw new EntityExistsException("ya existe el usuario ingresado");
        }

    }
    @Override
    public UsuarioDTO logIn(String nombreUsuario, String password){
        Optional<UsuarioEntity> user = usuariosJpa.findByNombreUsuarioAndPassword(nombreUsuario,password);
        if(user.isPresent()){
            return modelMapper.map(user, UsuarioDTO.class);
        }
        else {
            throw new EntityExistsException(HttpStatus.BAD_REQUEST.getReasonPhrase());
        }
    }
    @Override
    public Usuario getUsuario(String nombreUsuario){
        Optional<UsuarioEntity> user = usuariosJpa.findByNombreUsuario(nombreUsuario);
        if(user.isPresent()){
            return modelMapper.map(user,Usuario.class);
        }
        else
        {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }
}
