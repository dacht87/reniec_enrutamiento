package org.acme.bindy.ftp;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
// La anotación @RegisterForReflection se utiliza para informar a Quarkus sobre las clases que deben registrarse para la reflexión
@RegisterForReflection
// La anotación @FixedLengthRecord indica que la clase representa un registro de longitud fija
@FixedLengthRecord(length=128, paddingChar=' ')
//@FixedLengthRecord(length=20, paddingChar=' ')
public class Header {


    // La anotación @DataField se utiliza para marcar campos que representan campos de un registro de longitud fija
    // La propiedad 'pos' indica la posición del campo en el registro y 'length' la longitud del campo
    @DataField(pos = 1, length=4)
    public String version;
    // Campo en la posición 5 con longitud 4
    @DataField(pos = 5, length=4)
    public String lonCabecera;
    // Campo en la posición 9 con longitud 3
    @DataField(pos = 9, length=3)
    public String tipoServicio;
    // Campo en la posición 12 con longitud 9
    @DataField(pos = 12, length=9)
    public String longTotalTrama;
    // Campo en la posición 21 con longitud 22
    @DataField(pos = 21, length=22)
    public String fragmentacion;
    // Campo en la posición 43 con longitud 9
    @DataField(pos = 43, length=9)
    public String ttl;
    // Campo en la posición 52 con longitud 1
    @DataField(pos = 52, length=1)
    public String tipoConsulta;
    // Campo en la posición 53 con longitud 16
    @DataField(pos = 53, length=16)
    public String caractVerif;
    // Campo en la posición 69 con longitud 10
    @DataField(pos = 69, length=10)
    public String codInstitucion;
    // Campo en la posición 79 con longitud 10
    @DataField(pos = 79, length=10)
    public String codServerReniec;
    // Campo en la posición 89 con longitud 10
    @DataField(pos = 89, length=10)
    public String agenciaInstSolic;
    // Campo en la posición 99 con longitud 10
    @DataField(pos = 99, length=10)
    public String usuarioFinalInst;
    // Campo en la posición 109 con longitud 10
    @DataField(pos = 109, length=10)
    public String hostFinalInst;
    // Campo en la posición 119 con longitud 10
    @DataField(pos = 119, length=10)
    public String reservado;

    // Método que devuelve la trama generada por la concatenación de todos los campos del registro
    public String getTramaHeader() {
        return version + lonCabecera + tipoServicio + longTotalTrama + fragmentacion + ttl + tipoConsulta + caractVerif + codInstitucion + codServerReniec + agenciaInstSolic + usuarioFinalInst + hostFinalInst + reservado;
    }

    // Override del método toString para proporcionar una representación de cadena personalizada
    @Override
        public String toString() {
            return "";
        }

}
