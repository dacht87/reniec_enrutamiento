
package org.acme.processor;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.acme.bean.Respuesta;
import org.acme.builder.MessageSender;
import org.acme.mapper.RespuestaMapper;


public class DistributeProcessor implements Processor{

    // Instancia de RespuestaMapper para convertir objetos Respuesta a String y viceversa
    RespuestaMapper mapper = new RespuestaMapper();

    // Instancia de MessageSender, que debe proporcionarse al construir un objeto DistributeProcessor
    MessageSender messageSender;

    // Constructor que recibe un MessageSender como parámetro
    public DistributeProcessor(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    // Método de la interfaz Processor que realiza el procesamiento de un intercambio (Exchange)
    @Override
    public void process(Exchange exchange) throws Exception {
        // Imprime un mensaje en la consola
        System.out.println("Distribute: ");

        // Obtiene el objeto Respuesta del cuerpo del intercambio
        Respuesta resp = exchange.getIn().getBody(Respuesta.class);

        // Imprime información sobre la respuesta obtenida
        System.out.println("Distribute: " + resp.respuesta);

        // Convierte el objeto Respuesta a su representación en cadena y establece el cuerpo del intercambio
        exchange.getIn().setBody(mapper.toString(resp));

        // Obtiene el cuerpo del intercambio como una cadena
        String mensaje = exchange.getIn().getBody(String.class);

        // Utiliza el MessageSender para enviar el mensaje a una cola (método enviarMensajeACola)
        messageSender.enviarMensajeACola(mensaje);
    }
}