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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

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
}
