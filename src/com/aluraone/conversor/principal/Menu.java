package com.aluraone.conversor.principal;

public class Menu {
    public Menu() {
    }

    public String mensajeMenu(){
        return """
                ---*************************************---
                Bienvenido al conversor de moneda:
                 1) Dólar USD ==> Peso chileno CLP
                 2) Peso chileno CLP ==> Dólar USD
                 3) Dólar USD ==> Peso argentino ARS
                 4) Peso argentino ARS ==> Dólar USD
                 5) Dólar USD ==> Real brasileño BRL
                 6) Real brasileño BRL ==> Dólar USD
                 7) Historial de conversiones
                 8) Salir
                
                 Elija una opción:
                 ---************************************---""";
    }
}
