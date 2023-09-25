//package com.juanupla.tareas.notifications;
//
//import org.quartz.JobDetail;
//import org.quartz.SimpleTrigger;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//
//@Configuration
//public class QuartzConfiguration {
//    // Define un bean SchedulerFactoryBean para configurar Quartz
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("notificacionTareasTrigger") SimpleTrigger notificacionTareasTrigger) {
//        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//        schedulerFactory.setTriggers(notificacionTareasTrigger);
//        return schedulerFactory;
//    }
//
//    // Define un bean JobDetailFactoryBean para configurar el trabajo NotificacionTareasJob
////    @Bean
////    public JobDetailFactoryBean notificacionTareasJobDetail(NotificacionTareasJob notificacionTareasJob) {
////        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
////        jobDetailFactory.setJobClass(NotificacionTareasJob.class);
////        jobDetailFactory.setGroup("notificacionGroup");
////        jobDetailFactory.setDurability(true);
////        jobDetailFactory.setBeanName("notificacionTareasJob"); // Establece un nombre para el bean
////        jobDetailFactory.getJobDataMap().put("notificacionTareasJob", notificacionTareasJob);
////        return jobDetailFactory;
////    }
//
//    // Define un bean SimpleTriggerFactoryBean para configurar el trigger del trabajo NotificacionTareasJob
//    @Bean
//    public SimpleTriggerFactoryBean notificacionTareasTrigger(@Qualifier("notificacionTareasJobDetail") JobDetail jobDetail) {
//        SimpleTriggerFactoryBean triggerFactory = new SimpleTriggerFactoryBean();
//        triggerFactory.setJobDetail(jobDetail);
//        triggerFactory.setStartDelay(0L);
//        // Establece el intervalo para verificar y enviar notificaciones (por ejemplo, cada minuto)
//        triggerFactory.setRepeatInterval(60000L);
//        triggerFactory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
//        return triggerFactory;
//    }
//}
