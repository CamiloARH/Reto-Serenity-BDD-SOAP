package co.com.sofka.stepdefinitions.services.soap.tempConvert.fahrenheitToCelsius;

import co.com.sofka.models.DatoAndOput;
import co.com.sofka.stepdefinitions.ServiceSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;

import org.apache.log4j.Logger;
import static co.com.sofka.questions.ReturnSoapServiceResponse.returnSoapServiceResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.services.FileUtilities.readFile;
import static co.com.sofka.utils.services.soap.tempConvert.fahrenheitToCelsius.PatchFahrenheit.FAHRENHEIT_PATCH;
import static co.com.sofka.utils.services.soap.tempConvert.fahrenheitToCelsius.ResponseFahrenheit.FAHRENHEIT_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;


public class FahrenheitStepDefinition extends ServiceSetup {

    private DatoAndOput datos;
    public static Logger log = Logger.getLogger(FahrenheitStepDefinition.class);

    @Given("que el usario quiere saber a cuantos grados celsius esta")
    public void queElUsarioQuiereSaberACuantosGradosCelsiusEsta() {
        try {
            super.setUp();
            datos = new DatoAndOput();
            log.info("Se realiza el setUp del Scenario 1 Fahrenheit");
        } catch (Exception e) {
            log.error("Fallo el el setUp del Scenario 1 Fahrenheit");
        }

    }

    @When("el usuario digita en el convertidor {int}")
    public void elUsuarioDigitaEnElConvertidor(Integer fahrenheit) {
        try{
            datos.setFirstData(fahrenheit);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(headersFahrenheit())
                            .andTheBodyRequest(bodyRequest())
            );
            log.info("Se ingresa el dato de entrada en Fahrenheit del Scenario 1");
        }catch (Exception e){
            log.error("Fallo el dato de entrada");
        }
    }

    @Then("el usuario deberia ver la respuesta de {int}")
    public void elUsuarioDeberiaVerLaRespuestaDe(Integer celcius) {
        try{
            datos.setOutcome(celcius);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion es: ",
                            returnSoapServiceResponse(),
                            containsString(bodyResponse()))
            );
            log.info("Se muestra el dato de salida del Scenario 1");
        }catch (Exception e){
            log.error("Fallo el dato de salida");
        }

    }

    //Scenario 2
    @Given("que el usario quiere saber los grados Celsius en los que esta")
    public void queElUsarioQuiereSaberLosGradosCelsiusEnLosQueEsta() {
        try {
            super.setUp();
            datos = new DatoAndOput();
            log.info("Se realiza el setUp del Scenario 2 Fahrenheit");
        } catch (Exception e) {
            log.error("Fallo el el setUp del Scenario 2 Fahrenheit");
        }
    }

    @When("el usuario digita {string} grados Fahrenheit")
    public void elUsuarioDigitaGradosFahrenheit(String fahrenheit) {
        try{
            datos.setWrongData(fahrenheit);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(headersFahrenheit())
                            .andTheBodyRequest(bodyRequestError())
            );
            log.info("Se ingresa el dato de entrada en Fahrenheit del Scenario 2");
        }catch (Exception e){
            log.error("Fallo el dato de entrada");
        }

    }

    @Then("el usuario deberia ver la respuesta {string}")
    public void elUsuarioDeberiaVerLaRespuesta(String celcius) {
        try{
            datos.setWrongOutcome(celcius);
            actor.should(
                    seeThatResponse("El código de rspuesta HTTP debe ser: ",
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("El resultado de la conversion es: ",
                            returnSoapServiceResponse(),
                            containsString(bodyResponseError()))
            );
            log.info("Se muestra el dato de salida del Scenario 2");
        }catch (Exception e){
            log.error("Fallo el dato de salida");
        }

    }

    private DatoAndOput datos() {
        return datos;
    }

    private String bodyRequest() {
        return String.format(readFile(FAHRENHEIT_PATCH.getValue()), datos().getFirstData());
    }

    private String bodyResponse() {
        return String.format(FAHRENHEIT_RESPONSE.getValue(), datos().getOutcome());
    }

    private String bodyRequestError() {
        return String.format(readFile(FAHRENHEIT_PATCH.getValue()), datos().getWrongData());
    }

    private String bodyResponseError() {
        return String.format(FAHRENHEIT_RESPONSE.getValue(), datos().getWrongOutcome());
    }
}
