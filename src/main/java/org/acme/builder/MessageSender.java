package org.acme.builder;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MessageSender {

    @ConfigProperty(name = "app.jms.queue-end")
    private String queue_out;

    @Inject
    private ProducerTemplate producerTemplate;

    public void enviarMensajeACola(String mensaje) {
        producerTemplate.sendBody(String.format("jms:queue:%s",queue_out), mensaje);
    }
}