package org.acme;

import org.acme.bean.Respuesta;
import org.acme.mapper.RespuestaMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RespuestaMapperTest {
    @Test
    void testToString() {
        // Arrange: Configuración inicial
        RespuestaMapper respuestaMapper = new RespuestaMapper(); // Creamos una instancia del mapper
        Respuesta respuesta = new Respuesta(); // Creamos una instancia de la clase Respuesta
        respuesta.respuesta="prueba, mensaje!"; // Asignamos el valor al campo respuesta

        // Act
        String resultado = respuestaMapper.toString(respuesta);// Llamamos al método que estamos probando

        // Assert
        assertEquals("prueba, mensaje!", resultado); // Verificamos que el resultado sea el esperado
    }

}
