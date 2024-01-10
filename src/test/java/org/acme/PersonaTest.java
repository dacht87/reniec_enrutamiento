package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.acme.bindy.ftp.Persona;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
@QuarkusTest
 class PersonaTest {
    // Crear una instancia de Persona
    Persona persona = new Persona();
    @Test
    void testGetSetDni() {

        // Establecer el DNI y luego verificar que el método getDni devuelve el valor correcto
        persona.setDni("12345678");
        assertEquals("12345678", persona.getDni());
    }

    @Test
    void testGetSetFechaNacimiento() {

        // Establecer la fecha de nacimiento y luego verificar que el método getFechaNacimiento devuelve el valor correcto
        persona.setFechaNacimiento("2000-01-01");
        assertEquals("2000-01-01", persona.getFechaNacimiento());
    }

    @Test
    void testToString() {

        // Establecer valores para DNI y fecha de nacimiento
        persona.setDni("12345678");
        persona.setFechaNacimiento("2000-01-01");

        // Verificar que el método toString devuelve la representación de cadena esperada
        assertEquals("Persona{dni='12345678', fechaNacimiento='2000-01-01'}", persona.toString());
    }


}
