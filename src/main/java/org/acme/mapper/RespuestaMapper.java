package org.acme.mapper;
import org.acme.bean.Respuesta;

public class RespuestaMapper {

    public String toString(Respuesta texto){
        
        StringBuilder sb = new StringBuilder();
        sb.append(texto.respuesta);
        return sb.toString();
    }    
}
