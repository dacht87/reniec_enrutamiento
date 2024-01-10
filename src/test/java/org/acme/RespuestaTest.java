package org.acme;
import org.acme.bean.Respuesta;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RespuestaTest {
    @Test
    void testConstructorConRespuesta() {
        // Arrange
        String respuesta = "Hola Mundo";

        // Act
        Respuesta respuestaObj = new Respuesta(respuesta);

        // Assert
        assertThat(respuestaObj.respuesta).isEqualTo(respuesta);
    }

    @Test
    void testConstructorPorDefecto() {
        // Act
        Respuesta respuestaObj = new Respuesta();

        // Assert
        assertThat(respuestaObj.respuesta).isNull();
    }
}
