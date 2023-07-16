package com.juanupla.tareas.services.Impl;

import com.juanupla.tareas.entities.TareaEntity;
import com.juanupla.tareas.models.Tarea;
import com.juanupla.tareas.models.Usuario;
import com.juanupla.tareas.models.dtos.TareaDTO;
import com.juanupla.tareas.repositoriesJpa.TareaJpa;
import com.juanupla.tareas.services.TareaService;
import com.juanupla.tareas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.time.LocalDate;
import java.util.*;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TareaJpa tareaJpa;
    @Autowired
    private UsuarioService usuarioService;

    public Tarea newTarea(String nombreTarea, LocalDate fechaLimite, String prioridad, boolean estaCompleta, String descripcion, String nombreUsuario){


        Usuario usuario = usuarioService.getUsuario(nombreUsuario);

        Tarea tarea = new Tarea();
        tarea.setNombre(nombreTarea);
        tarea.setFechaInicio(LocalDate.now());
        tarea.setFechaLimite(fechaLimite);
        tarea.setPrioridad(prioridad);
        tarea.setRealizada(estaCompleta);
        tarea.setUsuario(usuario);
        tarea.setDescripcion(descripcion);


        TareaEntity tareaEntity = tareaJpa.save(modelMapper.map(tarea,TareaEntity.class));
        return modelMapper.map(tareaEntity,Tarea.class);


    }

    public List<Tarea> findAllByNombreUsuarioId(Long id){
        List<Tarea> lista = new ArrayList<>();
        Optional<List<TareaEntity>> list = tareaJpa.findAllByUsuarioId(id);
        if (list.isEmpty()){
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
        else {
            for (TareaEntity t: list.get()) {
                lista.add(modelMapper.map(t,Tarea.class));
            }
            return lista;
        }
    }

    public Tarea updateTarea(Tarea tarea){

        TareaEntity resul = tareaJpa.getReferenceById(tarea.getId());
        modelMapper.map(tarea,resul);
        TareaEntity fin = tareaJpa.save(resul);
        return modelMapper.map(fin,Tarea.class);

    }
    public boolean deleteTarea(Long idEliminar){
       TareaEntity tare = tareaJpa.getReferenceById(idEliminar);
       boolean active = false;
       if(tare != null){

           tareaJpa.deleteById(idEliminar);
            active = true;
            return active;
       }
       else {
           throw new ErrorResponseException(HttpStatus.BAD_REQUEST);

       }

    }
    public List<TareaDTO> listaTareas(String nombre, String prioridad){

        List<TareaDTO> list = new ArrayList<>();
        if(nombre != null){
            Optional<List<TareaEntity>> listEntity = tareaJpa.findAllByNombre(nombre);
            for (TareaEntity tarea: listEntity.get()) {
                list.add(modelMapper.map(tarea,TareaDTO.class));
            }
        }
        if(prioridad != null){
            Optional<List<TareaEntity>> listEntity2 = tareaJpa.findAllByPrioridad(prioridad);
            for (TareaEntity tarea2: listEntity2.get()) {
                list.add(modelMapper.map(tarea2,TareaDTO.class));
            }
        }
        if(nombre == null && prioridad == null){
            List<TareaEntity> listEntity3 = tareaJpa.findAll();
            for (TareaEntity tarea3: listEntity3) {
                list.add(modelMapper.map(tarea3,TareaDTO.class));
            }
        }
        list.sort(Comparator.comparing(TareaDTO::getFechaLimite));

        return list;
    }

}
