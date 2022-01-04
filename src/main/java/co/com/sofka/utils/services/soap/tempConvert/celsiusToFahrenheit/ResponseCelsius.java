package co.com.sofka.utils.services.soap.tempConvert.celsiusToFahrenheit;

public enum ResponseCelsius {
    CELSIUS_RESPONSE("<CelsiusToFahrenheitResult>%s</CelsiusToFahrenheitResult>");

    private final String value;

    ResponseCelsius(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
