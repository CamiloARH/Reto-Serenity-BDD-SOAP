package co.com.sofka.stepdefinitions.services.soap.tempConvert.celsiusToFahrenheit;


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
import static co.com.sofka.utils.services.soap.tempConvert.celsiusToFahrenheit.PatchCelsius.CELSIUS_PATCH;
import static co.com.sofka.utils.services.soap.tempConvert.celsiusToFahrenheit.ResponseCelsius.CELSIUS_RESPONSE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.containsString;


public class CelsiusStepDefinition extends ServiceSetup {

    private DatoAndOput datos;
    public static Logger log = Logger.getLogger(CelsiusStepDefinition.class);

    @Given("que el usario quiere saber a cuantos grados Fahrenheit esta")
    public void queElUsarioQuiereSaberACuantosGradosFahrenheitEsta() {
        try{
            super.setUp();
            datos = new DatoAndOput();
            log.info("Se realiza el setUp del Scenario 1 Celsius");
        }catch (Exception e){
            log.error("Fallo el el setUp del Scenario 1 Celsius");
        }
    }
    @When("el usuario digita {int}")
    public void elUsuarioDigita(Integer celsius) {
        try{
            datos.setFirstData(celsius);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(headersCelsius())
                            .andTheBodyRequest(bodyRequest())
            );
            log.info("Se ingresa el dato de entrada en Celsius del Scenario 1");
        }catch (Exception e){
            log.error("Fallo el dato de entrada");
        }
    }
    @Then("el usuario deberia ver {int}")
    public void elUsuarioDeberiaVer(Integer fahrenheit) {
        try{
            datos.setOutcome(fahrenheit);
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
    @Given("que el usario quiere saber los grados Fahrenheit en los que esta")
    public void queElUsarioQuiereSaberLosGradosFahrenheitEnLosQueEsta() {
        try{
            super.setUp();
            datos = new DatoAndOput();
            log.info("Se realiza el setUp del Scenario 2 Celsius");
        }catch (Exception e){
            log.error("Fallo el el setUp del Scenario 2 Celsius");
        }

    }
    @When("el usuario digita {string} grados Centigrados")
    public void elUsuarioDigitaGradosCentigrados(String celsius) {
        try{
            datos.setWrongData(celsius);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE)
                            .andTheHeaders(headersCelsius())
                            .andTheBodyRequest(bodyRequestError())
            );
            log.info("Se ingresa el dato de entrada en Celsius del Scenario 2");
        }catch (Exception e){
            log.error("Fallo el dato de entrada");
        }

    }
    @Then("el usuario deberia ver que es {string}")
    public void elUsuarioDeberiaVerQueEs(String fahrenheit) {
        try{
            datos.setWrongOutcome(fahrenheit);
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

    private DatoAndOput datos(){
        return datos;
    }

    private String bodyRequest(){
        return String.format(readFile(CELSIUS_PATCH.getValue()), datos().getFirstData());
    }

    private String bodyResponse(){
        return String.format(CELSIUS_RESPONSE.getValue(), datos().getOutcome());
    }

    private String bodyRequestError(){
        return String.format(readFile(CELSIUS_PATCH.getValue()), datos().getWrongData());
    }

    private String bodyResponseError(){
        return String.format(CELSIUS_RESPONSE.getValue(), datos().getWrongOutcome());
    }
}
