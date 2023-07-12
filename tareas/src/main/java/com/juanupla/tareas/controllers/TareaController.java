package com.juanupla.tareas.controllers;

import com.juanupla.tareas.entities.TareaEntity;
import com.juanupla.tareas.models.Tarea;
import com.juanupla.tareas.models.dtos.TareaDTO;
import com.juanupla.tareas.services.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @PostMapping("/newTarea")
    public ResponseEntity<Tarea> nuevaTarea(@RequestBody @Valid TareaDTO tarea){
         Tarea tarea1 = tareaService.newTarea(tarea.getNombre(),tarea.getFechaLimite(), tarea.getPrioridad(),tarea.isRealizada(),tarea.getDescripcion(),tarea.getUsuario().getNombreUsuario());
         if(tarea1 != null){
             return ResponseEntity.ok(tarea1);
         }
         else {
             throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
         }

    }

    @GetMapping("{id}/AllTareasByUserId")
    public ResponseEntity<List<Tarea>> tareas(@PathVariable @Valid Long id){
        List<Tarea> lista = tareaService.findAllByNombreUsuarioId(id);
        if(lista.isEmpty()){
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(lista);
        }
    }

    @PutMapping("/updateTarea")
    public ResponseEntity<Tarea> updateTarea(@RequestBody @Valid Tarea tarea){
        Tarea result = tareaService.updateTarea(tarea);
        if (result != null){
            return ResponseEntity.ok(result);
        }
        else
        {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteTarea/{id}")
        public ResponseEntity<Boolean> delete(Long id){
            Boolean result = tareaService.deleteTarea(id);
            return ResponseEntity.ok(result);
        }
}
