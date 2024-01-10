package org.acme;

import org.acme.bindy.ftp.Header;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class HeaderTest {
    @Test
    void testGetTramaHeader() {
        // Arrange: Configuración inicial
        Header header = new Header();
        header.version = "1234";
        header.lonCabecera = "5678";
        header.tipoServicio = "ABC";
        header.longTotalTrama = "987654321";
        header.fragmentacion = "0123456789012345678901";
        header.TTL = "999999999";
        header.tipoConsulta = "A";
        header.caractVerif = "0123456789ABCDEF";
        header.codInstitucion = "9876543210";
        header.codServerReniec = "1357924680";
        header.agenciaInstSolic = "2468013579";
        header.usuarioFinalInst = "1111111111";
        header.hostFinalInst = "localhost";
        header.reservado = "0000000000";

        // Act: Acción que estamos probando
        String resultado = header.getTramaHeader();

        // Assert: Verificación del resultado
        String tramaEsperada = "12345678ABC9876543210123456789012345678901999999999A0123456789ABCDEF9876543210135792468024680135791111111111localhost0000000000";

        String tramaGenerada = header.getTramaHeader().trim();  // Elimina espacios en blanco al principio y al final

        assertEquals(tramaEsperada, tramaGenerada);
        assertEquals(tramaEsperada, resultado);
    }
}
