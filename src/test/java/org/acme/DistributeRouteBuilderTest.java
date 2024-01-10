package org.acme;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.direct.DirectEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
 class DistributeRouteBuilderTest {
    @Test
    void testDistributeRoute() throws Exception {
        // Crear un contexto Camel
        CamelContext context = new DefaultCamelContext();

        // Agregar la ruta de prueba al contexto
        context.addRoutes(new DistributeRouteBuilder());

        // Iniciar el contexto
        context.start();

        try {
            // Obtener el endpoint direct:procesarMensaje
            Endpoint directEndpoint = context.getEndpoint("direct:procesarMensaje", DirectEndpoint.class);

            // Crear un intercambio con el cuerpo del mensaje de prueba
            Exchange exchange = directEndpoint.createExchange();
            exchange.getIn().setBody("Test Message");

            // Enviar el intercambio al endpoint direct:procesarMensaje
            directEndpoint.createProducer().process(exchange);

            // Verificar el resultado


        } finally {
            // Detener el contexto después de la prueba
            context.stop();
        }
    }

    // Clase interna que representa la ruta de prueba
    private static class DistributeRouteBuilder extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("direct:procesarMensaje")
                    .log("Received a message - ${body} - sending to end Distribute");

        }
    }
}
