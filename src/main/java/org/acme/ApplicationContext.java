package org.acme;

import org.apache.camel.component.jms.JmsComponent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.msg.client.jakarta.jms.JmsConnectionFactory;
import com.ibm.msg.client.jakarta.jms.JmsConstants;
import com.ibm.msg.client.jakarta.jms.JmsFactoryFactory;
import com.ibm.msg.client.jakarta.wmq.WMQConstants;
import com.ibm.msg.client.jakarta.wmq.common.CommonConstants;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;

@ApplicationScoped
public class ApplicationContext {

    // Propiedad de configuración para el host de JMS
    @ConfigProperty(name = "app.jms.host")
    private String host;
    public void setHost(String host) {
        this.host = host;
    }
    // Propiedad de configuración para el puerto de JMS
    @ConfigProperty(name = "app.jms.port")
    private int port;
    public void setPort(int port) {
        this.port = port;
    }
    // Propiedad de configuración para el canal de JMS
    @ConfigProperty(name = "app.jms.channel")
    private String channel;
    public void setChannel(String channel) {
        this.channel= channel;
    }
    // Propiedad de configuración para el administrador de colas de JMS
    @ConfigProperty(name = "app.jms.queuemgr")
    private String queuemgr;
    public void setQueuemgr(String queuemgr) {
        this.queuemgr= queuemgr;
    }
    // Propiedad de configuración para el usuario de JMS
    @ConfigProperty(name = "app.jms.user")
    private String user;
    public void setUser(String user) {this.user= user;
    }
    // Propiedad de configuración para la contraseña de JMS

    @ConfigProperty(name = "app.jms.password")
    private String password;
    public void setPassword(String password) {
        this.password= password;
    }
    // Método de producción (producer method) que crea y retorna una ConnectionFactory
    @Produces
    @Default
    public ConnectionFactory connectionFactory() throws JMSException {
        // Declaraciones de variables
        JmsFactoryFactory ff;
        JmsConnectionFactory factory;

        try {
            // Obtener una instancia de JmsFactoryFactory para el proveedor Jakarta WMQ
            ff = JmsFactoryFactory.getInstance(JmsConstants.JAKARTA_WMQ_PROVIDER);

            // Crear una ConnectionFactory usando JmsFactoryFactory
            factory = ff.createConnectionFactory();

            // Configuración de propiedades en la ConnectionFactory para la conexión JMS
            // Establece el modo de conexión a cliente para la fábrica
            factory.setIntProperty(CommonConstants.WMQ_CONNECTION_MODE, CommonConstants.WMQ_CM_CLIENT);

// Establece el nombre del host para la conexión
            factory.setStringProperty(CommonConstants.WMQ_HOST_NAME, this.host);

// Establece el puerto para la conexión
            factory.setIntProperty(CommonConstants.WMQ_PORT, this.port);

// Establece el nombre del canal para la conexión
            factory.setStringProperty(CommonConstants.WMQ_CHANNEL, this.channel);

// Establece el nombre del gestor de colas (queue manager) para la conexión
            factory.setStringProperty(CommonConstants.WMQ_QUEUE_MANAGER, this.queuemgr);

// Establece el usuario para la autenticación en la conexión
            factory.setStringProperty(WMQConstants.USERID, this.user);

// Establece la contraseña para la autenticación en la conexión
            factory.setStringProperty(WMQConstants.PASSWORD, this.password);

// Habilita la autenticación de usuario mediante el uso de MQCSP (MQ Channel Security Exit)
            factory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);

// Establece el tipo de codificación para el intercambio de mensajes JMS
            factory.setIntProperty(JmsConstants.JMS_IBM_ENCODING, WMQConstants.WMQ_ENCODING_NATIVE);

        } catch (JMSException je) {
            // Manejo de excepciones JMS, se lanza nuevamente la excepción
            throw je;
        }

        // Retorna la ConnectionFactory configurada
        return factory;
    }
    
    @Produces
    @Default
    public JmsComponent jms() throws JMSException {
        // Se crea una nueva instancia de JmsComponent
        try (var jmsComponent = new JmsComponent()) {
            // Se configura el componente JmsComponent con la conexión factory
            jmsComponent.setConnectionFactory(connectionFactory());
            // Se devuelve el componente configurado
            return jmsComponent;
        } catch (Exception e) {
            // Se manejan excepciones, y se lanza una nueva excepción JMSException si ocurre un error al crear el componente
            throw new JMSException("Error al crear el componente JMS");
        }
    }
//
}
