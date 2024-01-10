package org.acme.bindy.ftp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
// La anotación @RegisterForReflection se utiliza para informar a Quarkus sobre las clases que deben registrarse para la reflexión
@RegisterForReflection
// La anotación @FixedLengthRecord indica que la clase representa un registro de longitud fija
@FixedLengthRecord(length=18, paddingChar=' ')
public class Persona {

    // La anotación @DataField se utiliza para marcar campos que representan campos de un registro de longitud fija
    // La propiedad 'pos' indica la posición del campo en el registro y 'length' la longitud del campo
    @DataField(pos = 1, length=8)
    public String dni;
    // Campo en la posición 9 con longitud 10
    @DataField(pos = 9, length=10)
    public String fechaNacimiento;

    // Constructor por defecto
    public Persona() {
    }

    // Getter y setter para el campo 'dni'
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    // Getter y setter para el campo 'fechaNacimiento'
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Override del método toString para proporcionar una representación de cadena personalizada
    @Override
        public String toString() {
        return String.format("Persona{dni='%s', fechaNacimiento='%s'}", dni, fechaNacimiento);
         }
}
