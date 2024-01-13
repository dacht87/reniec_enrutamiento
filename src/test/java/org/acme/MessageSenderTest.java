package org.acme;
import org.acme.builder.MessageSender;
import org.apache.camel.ProducerTemplate;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

 class MessageSenderTest {
     @Mock
// Se crea un mock para la interfaz ProducerTemplate, que se utilizará en las pruebas
     private ProducerTemplate producerTemplate;

     @InjectMocks
// Se inyectan los mocks en la instancia de la clase MessageSender para que puedan ser utilizados en las pruebas
     private MessageSender messageSender;

     @ConfigProperty(name = "app.jms.queue-end")
// Se obtiene el valor de la propiedad "app.jms.queue-end" desde la configuración
     private String queue_out;

     @BeforeEach
// Método que se ejecuta antes de cada prueba para realizar la configuración inicial
     void setUp() {
         // Inicialización de los mocks y la instancia de la clase MessageSender
         MockitoAnnotations.openMocks(this);

         // Configuración del valor de la cola de salida en la instancia de MessageSender
         messageSender.setQueueOut(queue_out);
     }

    @Test
    void testEnviarMensajeACola() {
        // Arrange
        String mensaje = "00020128000000023000                      0000000002RENKLCPERIRENKLCDE2116    RENKLC001 0000INS00070600648  HOST000000          706006481                     ";

        // Act
        messageSender.enviarMensajeACola(mensaje);

        // Assert
        verify(producerTemplate).sendBody(anyString(), eq(mensaje));
    }
}
