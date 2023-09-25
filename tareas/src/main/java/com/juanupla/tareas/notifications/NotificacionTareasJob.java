//package com.juanupla.tareas.notifications;
//
//import com.juanupla.tareas.entities.TareaEntity;
//import com.juanupla.tareas.models.Tarea;
//import com.juanupla.tareas.repositoriesJpa.TareaJpa;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cglib.core.Local;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class NotificacionTareasJob implements Job {
//
////    @Autowired
////    private JavaMailSender mailSender;
//
//
//    private static TareaJpa tareaJpa;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//     //Constructor para inyectar el JavaMailSender
////    public NotificacionTareasJob(JavaMailSender mailSender) {
////        this.mailSender = mailSender;
////    }
//
//    // Método que se ejecuta cuando el trabajo es disparado por Quartz
////    @Override
////    public void execute(JobExecutionContext context) throws JobExecutionException {
////        // Aquí implementa la lógica para enviar las notificaciones
////        // Verifica las tareas pendientes y envía las notificaciones correspondientes
////        List<Tarea> tareasPendientes = obtenerTareasPendientes();
////
////        // Itera sobre las tareas y verifica si alguna está próxima a vencer
////        for (Tarea tarea : tareasPendientes) {
////            enviarNotificacionPorEmail(tarea);
////        }
////    }
//
//    private List<Tarea> obtenerTareasPendientes() {
//        // Aquí implementa el código para obtener las tareas pendientes desde la base de datos
//        // Retorna la lista de tareas pendientes
//        List<Tarea> tareas = new ArrayList<>();
//        List<TareaEntity> list = tareaJpa.findAll();
//        for (TareaEntity ta : list) {
//            if (estaCercaDeVencer(ta)) {
//                tareas.add(modelMapper.map(ta, Tarea.class));
//            }
//        }
//        return tareas;
//    }
//
//    private boolean estaCercaDeVencer(TareaEntity tarea) {
//        // Aquí implementa la lógica para verificar si una tarea está cerca de vencer
//        // Puedes comparar la fecha límite de la tarea con la fecha actual y determinar si está cerca de vencer
//        long diferenciaEnDias = ChronoUnit.DAYS.between(LocalDate.now(), tarea.getFechaLimite());
//        return diferenciaEnDias > 0 && diferenciaEnDias < 7;
//    }
//
////    private void enviarNotificacionPorEmail(Tarea tarea) {
////        try {
////            MimeMessage message = mailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message, true);
////            helper.setTo(tarea.getUsuario().getEmail());
////            helper.setSubject("Recordatorio: Tarea próxima a vencer");
////            helper.setText("Hola " + tarea.getUsuario().getNombreUsuario() + ",\n\n"
////                    + "Te recordamos que la siguiente tarea está próxima a vencer:\n\n"
////                    + "Título: " + tarea.getNombre() + "\n"
////                    + "Fecha límite: " + tarea.getFechaLimite() + "\n\n"
////                    + "¡No olvides completarla a tiempo!\n\n"
////                    + "Saludos,\n"
////                    + "El equipo de Gestión de Tareas");
////            mailSender.send(message);
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        }
////    }
//}