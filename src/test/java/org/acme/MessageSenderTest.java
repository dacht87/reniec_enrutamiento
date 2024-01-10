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
    private ProducerTemplate producerTemplate;

    @InjectMocks
    private MessageSender messageSender;

    @ConfigProperty(name = "app.jms.queue-end")
    private String queue_out;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
