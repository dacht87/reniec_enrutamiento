
package org.acme.processor;
import org.apache.camel.Processor;
import org.apache.camel.Exchange;
import org.acme.bean.Respuesta;
import org.acme.mapper.RespuestaMapper;

public class DistributeProcessor implements Processor{

    RespuestaMapper mapper =new RespuestaMapper();

    @Override
    public void process(Exchange exchange) throws Exception {

        System.out.println("Distribute: ");

        Respuesta resp = exchange.getIn().getBody(Respuesta.class);

        System.out.println("Distribute: "+resp.respuesta);
        
        exchange.getIn().setBody(mapper.toString(resp));
        //exchange.getIn().setBody("00020128000000023000                      0000000002RENIECPERURENIECDE2116    RENIEC001 0000INS00070600648  HOST000000          0001");
    }
}