package co.com.sofka.utils.services.soap.tempConvert.fahrenheitToCelsius;

public enum ResponseFahrenheit {
    FAHRENHEIT_RESPONSE("<FahrenheitToCelsiusResult>%s</FahrenheitToCelsiusResult>");

    private final String value;

    ResponseFahrenheit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
