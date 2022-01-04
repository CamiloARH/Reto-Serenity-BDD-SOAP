package co.com.sofka.stepdefinitions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.HashMap;

public class ServiceSetup {
    protected static final String URL_BASE = "https://www.w3schools.com";
    protected static final String RESOURCE = "/xml/tempconvert.asmx";
    protected Actor actor = new Actor("Camilo");

    protected void setUp(){
        actorCan();
    }
    private void actorCan(){
        actor.can(CallAnApi.at(URL_BASE));
    }

    protected HashMap<String, Object> headersCelsius(){
        HashMap<String, Object> headerCollection = new HashMap<>();
        headerCollection.put("Content-Type","text/xml;charset=UTF-8\n");
        headerCollection.put("SOAPAction","https://www.w3schools.com/xml/CelsiusToFahrenheit");
        return headerCollection;
    }
    protected HashMap<String, Object> headersFahrenheit(){
        HashMap<String, Object> headerCollection = new HashMap<>();
        headerCollection.put("Content-Type","text/xml;charset=UTF-8\n");
        headerCollection.put("SOAPAction","https://www.w3schools.com/xml/FahrenheitToCelsius");
        return headerCollection;
    }
}
