package org.acme.builder;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageSender {

    // Se utiliza la anotación @ConfigProperty para inyectar el valor de la propiedad "app.jms.queue-end" desde la configuración
    @ConfigProperty(name = "app.jms.queue-end")
    private String queue_out;
    public void setQueueOut(String queueOut) {
        this.queue_out = queueOut;
    }
    // Se utiliza la anotación @Inject para inyectar un ProducerTemplate, que es una clase proporcionada por Camel para producir mensajes
    @Inject
    public ProducerTemplate producerTemplate;

    // Método que envía un mensaje a una cola JMS
    public void enviarMensajeACola(String mensaje) {
        // Se utiliza el ProducerTemplate para enviar el mensaje al destino de la cola JMS
        // El formato del endpoint JMS se construye utilizando la propiedad 'queue_out'
        producerTemplate.sendBody(String.format("jms:queue:%s", queue_out), mensaje);
    }
}