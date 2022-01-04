Feature: Covertidor de grados Celsius a Fahrenheit
  Como Usuario que va a viajar
  necesito saber que el convertidor este funcionando bien
  para poder saber con seguridad la conversion de la temperatura

  Scenario Outline: Convertir los grados celsius
    Given que el usario quiere saber a cuantos grados Fahrenheit esta
    When el usuario digita <celsiusTemp>
    Then el usuario deberia ver <fahrentheitTemp>
    Examples:
  |celsiusTemp|fahrentheitTemp|
  |     0     |       32      |
  |     25    |       77      |

  Scenario: Escribir los numeros como letras
    Given que el usario quiere saber los grados Fahrenheit en los que esta
    When el usuario digita "Cero" grados Centigrados
    Then el usuario deberia ver "Error"