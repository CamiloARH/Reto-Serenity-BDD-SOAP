package co.com.sofka.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.StandardCharsets;

public class ReturnSoapServiceResponse implements Question<String> {
    public static Log log= LogFactory.getLog(ReturnSoapServiceResponse.class);

    @Override
    public String answeredBy(Actor actor) {

//        log.info(LastResponse.received().answeredBy(actor).body().prettyPrint());
        return new String(LastResponse.received().answeredBy(actor).body().asByteArray(), StandardCharsets.UTF_8);
    }
    public static ReturnSoapServiceResponse returnSoapServiceResponse(){
        return new ReturnSoapServiceResponse();
    }
}
