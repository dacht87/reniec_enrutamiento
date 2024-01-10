package org.acme.bean;

// Clase que representa una respuesta con un campo 'respuesta'
public class Respuesta {
    // Campo que almacena la respuesta
    public String respuesta;

    // Constructor que permite inicializar la respuesta al crear un objeto de la clase
    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    // Constructor por defecto, no recibe parámetros y deja 'respuesta' en su valor predeterminado
    public Respuesta() {
    }
}
