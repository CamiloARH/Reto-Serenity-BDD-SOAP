package co.com.sofka.utils.services.soap.tempConvert.celsiusToFahrenheit;

public enum PatchCelsius {
    CELSIUS_PATCH(System.getProperty("user.dir")
            + "\\src\\test\\resources\\files\\services\\soap\\tempConvert\\celsiusToFahrenheit\\celsiusToFahrenheit.xml");

    private final String value;

    PatchCelsius(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
