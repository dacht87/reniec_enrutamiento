package org.acme.builder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.acme.bean.Respuesta;
import org.acme.processor.DistributeProcessor;

@ApplicationScoped
public class DistributeRouteBuilder extends RouteBuilder {

    // Instancia un objeto JacksonDataFormat para deserializar respuestas en formato JSON a objetos Respuesta
    private JacksonDataFormat formatRpta = new JacksonDataFormat(Respuesta.class);

    // Configuraciones de host y puerto para la distribución de mensajes a través de Camel REST
    @ConfigProperty(name = "app.camel.rest.host.distribute")
    private String host_dist;

    @ConfigProperty(name = "app.camel.rest.port.distribute")
    private int port_dist;

    // Inyección de dependencia del componente MessageSender, que se utilizará para enviar mensajes
    @Inject
    private MessageSender messageSender;

    @Override
    public void configure() throws Exception {

        // Configuración global de REST
        restConfiguration()
                .component("platform-http")  // Configuración del componente REST para la plataforma HTTP
                .host(host_dist)  // Configuración del host para las rutas REST
                .port(port_dist);  // Configuración del puerto para las rutas REST

        // Definición de una ruta REST para el recurso "/receptor/mensaje" con método HTTP POST
        rest("/receptor")
                .post("/mensaje")
                .to("direct:procesarMensaje");  // Enviar la solicitud al punto de entrada direct:procesarMensaje

        // Definición de la ruta de procesamiento direct:procesarMensaje
        from("direct:procesarMensaje")
                .unmarshal(formatRpta)  // Deserializar el cuerpo del mensaje utilizando el formato especificado por formatRpta
                .process(new DistributeProcessor(messageSender));  // Procesar el mensaje utilizando la clase DistributeProcessor
        }

}
