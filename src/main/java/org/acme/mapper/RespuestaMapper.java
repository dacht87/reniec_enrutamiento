package org.acme.mapper;
import org.acme.bean.Respuesta;

// La clase RespuestaMapper se utiliza para convertir objetos Respuesta a cadenas (String)
public class RespuestaMapper {

    // Método que convierte un objeto Respuesta a su representación en cadena
    public String toString(Respuesta texto) {
        // Se crea un StringBuilder para construir la cadena
        StringBuilder sb = new StringBuilder();

        // Se agrega el contenido de la propiedad 'respuesta' del objeto Respuesta al StringBuilder
        sb.append(texto.respuesta);

        // Se devuelve la representación en cadena construida
        return sb.toString();
    }
}
