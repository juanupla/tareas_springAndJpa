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
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TareaJpa tareaJpa;
    @Autowired
    private UsuarioService usuarioService;

    public Tarea newTarea(TareaDTO tareaDTO){


        Usuario usuario = usuarioService.getUsuario(tareaDTO.getUsuario().getNombreUsuario());

        Tarea tarea = new Tarea();
        tarea.setNombre(tareaDTO.getNombre());
        tarea.setFechaInicio(LocalDate.now());
        tarea.setFechaLimite(tareaDTO.getFechaLimite());
        tarea.setPrioridad(tareaDTO.getFechaLimite().toString());
        tarea.setRealizada(tareaDTO.isRealizada());
        tarea.setUsuario(tareaDTO.getUsuario());
        tarea.setDescripcion(tareaDTO.getDescripcion());


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
        resul.setDescripcion(tarea.getDescripcion());
        resul.setNombre(tarea.getNombre());
        resul.setPrioridad(tarea.getPrioridad());
        resul.setRealizada(tarea.isRealizada());
        resul.setFechaLimite(tarea.getFechaLimite());
        //modelMapper.map(resul,Tarea.class);
        TareaEntity fin = tareaJpa.save(resul);
        return modelMapper.map(fin,Tarea.class);

    }
    public boolean deleteTarea(Long idEliminar){
       TareaEntity tare = tareaJpa.getReferenceById(idEliminar);
       if(tare != null){

           tareaJpa.deleteById(idEliminar);
            return true;
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
