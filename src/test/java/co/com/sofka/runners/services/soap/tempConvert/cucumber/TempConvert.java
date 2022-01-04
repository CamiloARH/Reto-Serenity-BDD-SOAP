package co.com.sofka.runners.services.soap.tempConvert.cucumber;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        features = {"src/test/resources/features/services/soap/tempConvert/celsiusToFahrenheit/celsiusToFahrenheit.feature"
                ,"src/test/resources/features/services/soap/tempConvert/FahrenheitToCelsius/FahrenheitToCelsius.feature"},
        glue = {"co.com.sofka.stepdefinitions.services.soap.tempConvert.celsiusToFahrenheit"
                ,"co.com.sofka.stepdefinitions.services.soap.tempConvert.fahrenheitToCelsius"}
)
public class TempConvert {
}
