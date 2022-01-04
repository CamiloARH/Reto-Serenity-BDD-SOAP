Feature: Covertidor de grados Fahrenheit a Celsius
  Como Usuario que va a viajar
  necesito saber que el convertidor este funcionando bien
  para poder saber con seguridad la conversion de la temperatura

  Scenario Outline: Convertir los grados fahrenheit
    Given que el usario quiere saber a cuantos grados celsius esta
    When el usuario digita en el convertidor <fahrenheitTemp>
    Then el usuario deberia ver la respuesta de <celsiusTemp>
    Examples:
      |fahrenheitTemp|celsiusTemp|
      |       32      |     0     |
      |       41      |     5     |

  Scenario: Escribir la resta de dos numeros
    Given que el usario quiere saber los grados Celsius en los que esta
    When el usuario digita "64-32" grados Fahrenheit
    Then el usuario deberia ver la respuesta "Error"