package com.aluraone.conversor.principal;

import com.aluraone.conversor.modelo.Moneda;
import com.aluraone.conversor.modelo.MonedaDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner seleccion = new Scanner(System.in);
        List<Moneda> monedasConversiones = new ArrayList<>();
        Gson gson = new GsonBuilder()
                //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        Menu menu = new Menu();
        while (true){
            System.out.println(menu.mensajeMenu()+"\n");
            var opcion = seleccion.nextLine().replace(" ","");
            if (opcion.equalsIgnoreCase("8")){
                break;
            };
            String clave = "YOUR-API-KEY";
            String url = "https://v6.exchangerate-api.com/v6/"+clave+"/pair/";


            switch (opcion){
                case "1":
                    url += "USD/CLP";
                    break;
                case "2":
                    url += "CLP/USD";
                    break;
                case "3":
                    url += "USD/ARS";
                    break;
                case "4":
                    url += "ARS/USD";
                    break;
                case "5":
                    url += "USD/BRL";
                    break;
                case "6":
                    url += "BRL/USD";
                    break;
                case "7":
                    System.out.println("HISTÓRICOS: ");
                    break;
                default:
                    System.out.println("Ingrese opción Válida");
            }

            try {
                if (monedasConversiones.isEmpty() && opcion.equalsIgnoreCase("7")){
                    System.out.println("Historial vacío");
                }else if (opcion.equalsIgnoreCase("7")){
                    monedasConversiones.forEach(System.out::println);
                }else {
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();
                    HttpResponse<String> response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();
                    //System.out.println("El JSON: "+json);

                    MonedaDTO miMonedaDTO = gson.fromJson(json, MonedaDTO.class);
                    //System.out.println(miMonedaDTO);
                    System.out.println("Ingrese Cantidad: ");
                    var cantidad = Integer.valueOf(seleccion.nextLine());

                    Moneda miMoneda = new Moneda(miMonedaDTO);
                    miMoneda.setCantidad(cantidad);
                    miMoneda.resultadoConversion();
                    System.out.println(miMoneda);
                    monedasConversiones.add(miMoneda);
                }

            }catch (IllegalArgumentException | IOException | InterruptedException | JsonSyntaxException e){
                System.out.println("Error intente otra vez");
            }catch (IllegalStateException e){
                System.out.println("Algo pasa: "+e );
            }

        }

    }
}
