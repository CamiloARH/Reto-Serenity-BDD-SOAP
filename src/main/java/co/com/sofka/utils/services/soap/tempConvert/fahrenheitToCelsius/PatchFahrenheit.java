package co.com.sofka.utils.services.soap.tempConvert.fahrenheitToCelsius;

public enum PatchFahrenheit {
    FAHRENHEIT_PATCH(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\tempConvert\\fahrenheitToCelsius\\fahrenheitToCelsius.xml");

    private final String value;

    PatchFahrenheit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
