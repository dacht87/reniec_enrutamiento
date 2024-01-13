package org.acme;
import com.ibm.msg.client.jakarta.jms.JmsConnectionFactory;
import com.ibm.msg.client.jakarta.jms.JmsFactoryFactory;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.camel.component.jms.JmsComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ApplicationContextTest {
    ApplicationContext context = new ApplicationContext();

    @Test
    public void testConnectionFactory() throws JMSException {
        // Configurar datos de prueba
        // Establece el nombre del host para la conexión
        context.setHost("qm1-ibm-mq.reniec.svc.cluster.local");
        // Establece el puerto para la conexión
        context.setPort(1414);
        // Establece el canalpara la conexión
        context.setChannel("DEV.APP.SVRCONN");
        // Establece el administrador de colas para la conexión
        context.setQueuemgr("qm1");
        // Establece el usuario para la conexión
        context.setUser("root");
        // Establece la contraseña  para la conexión
        context.setPassword("passw0rd");


        // Llamar al método de prueba
        ConnectionFactory connectionFactory = context.connectionFactory();


        // Aserciones adicionales
        assertNotNull(connectionFactory);

    }

    @Test
    void testConnectionFactoryWithInvalidConfiguration() {


        // Configuracion  del contexto con configuración incorrecta, por ejemplo, host no válido.
        context.setHost("invalidHost");

        assertThrows(JMSException.class, context::connectionFactory);
    }


    @Nested
    @Testcontainers
    class ApplicationContextIntegrationTest {
        // Configuración de un contenedor Docker para el broker JMS
        @Container
        private final GenericContainer<?> testContainer = new GenericContainer<>("your-jms-broker-image")
                .withExposedPorts(1414); // Configuración de los puertos

        // Instancia de la clase ApplicationContext que se va a probar
        private ApplicationContext context;

        // Método que se ejecuta antes de cada prueba para realizar la configuración inicial
        @BeforeEach
        void setUp() {
            // Crear una instancia de ApplicationContext
            context = new ApplicationContext();

            // Configurar el host y el puerto del ApplicationContext con la información del contenedor Docker
            context.setHost(testContainer.getHost());
            context.setPort(testContainer.getMappedPort(1414));
        }
    }
}
