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

    private JacksonDataFormat formatRpta=new JacksonDataFormat(Respuesta.class);

    // @ConfigProperty(name = "app.jms.queue-processed")
    // private String queue_in;

    // @ConfigProperty(name = "app.jms.queue-end")
    // private String queue_out;

    @ConfigProperty(name = "app.camel.rest.host.distribute")
    private String host_dist;

    @ConfigProperty(name = "app.camel.rest.port.distribute")
    private int port_dist;

    @Inject
    private MessageSender messageSender;

    @Override
    public void configure() throws Exception {
        // from(String.format("jms:queue:%s",queue_in))
        //     .log("Received a message - ${body} - sending to end Distribute")
        //     .unmarshal(formatRpta)
        //     .process(new DistributeProcessor())
        //     .marshal(formatRpta)
        // .to(String.format("jms:queue:%s",queue_out));

        restConfiguration()
        .component("platform-http")
        .host(host_dist)
        .port(port_dist);
    
        rest("/receptor")
            .post("/mensaje")
            .to("direct:procesarMensaje");
        
        from("direct:procesarMensaje")
            .log("Received a message - ${body} - sending to end Distribute")
            .unmarshal(formatRpta)
            .process(new DistributeProcessor(messageSender));
          
        }

}
