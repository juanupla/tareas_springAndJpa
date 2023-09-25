package com.juanupla.tareas.config;




import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Esta anotación se utiliza para marcar una clase de configuración en Spring.
Indica que la clase contiene métodos que definen la configuración de la aplicación,
como la creación de beans y la configuración de componentes.
 */
@Configuration
/*
Se utiliza para enlazar automáticamente propiedades de configuración con campos de una clase.
Permite una fácil configuración y acceso a las propiedades en un archivo de
configuración, como application.properties.
 */
@ConfigurationProperties(prefix = "app")
@Data
public class SpringDocConfig {

    private String url;

    private String devName;

    private String devEmail;


    /*
    Esta anotación se utiliza para indicar que un método en una clase de
    configuración de Spring debe ser tratado como un bean y gestionado por el contenedor de Spring.
     */
    @Bean
    public OpenAPI openApi (
            /*
            Esta anotación se utiliza para inyectar valores de propiedades en los
            campos de una clase. Permite que las propiedades definidas en un archivo
            de configuración (como application.properties) sean inyectadas en las variables correspondientes.
             */
        @Value("${app.name}") String appName,
        @Value("${app.desc}") String appDescription,
        @Value("${app.version}") String appVersion){

        Info info = new Info()
                .title(appName)
                .version(appVersion)
                .description(appDescription)
                .contact(
                        new Contact()
                                .name(devName)
                                .email(devEmail));

        Server server = new Server()
                .url(url)
                .description(appDescription);

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .addServersItem(server);
    }

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){
        return new ModelResolver(objectMapper);
    }

}
