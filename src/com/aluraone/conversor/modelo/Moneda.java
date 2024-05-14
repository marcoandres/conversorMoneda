package com.aluraone.conversor.modelo;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Moneda implements Comparable<Moneda>{
    private String monedaInical;
    private String monedaFinal;
    private double cantidad;
    private double base_cambio;
    private double resultadoCambio;

    public Moneda(String monedaInical, String monedaFinal) {
        this.monedaInical = monedaInical;
        this.monedaFinal = monedaFinal;
    }

    public Moneda(MonedaDTO miMonedaDTO) {
        this.monedaInical = miMonedaDTO.base_code();
        this.monedaFinal = miMonedaDTO.target_code();
        this.base_cambio = miMonedaDTO.conversion_rate();

    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMonedaInical() {
        return monedaInical;
    }

    public void setMonedaInical(String monedaInical) {
        this.monedaInical = monedaInical;
    }

    public String getMonedaFinal() {
        return monedaFinal;
    }

    public void setMonedaFinal(String monedaFinal) {
        this.monedaFinal = monedaFinal;
    }

    public double getBase_cambio() {
        return base_cambio;
    }

    public void setBase_cambio(int base_cambio) {
        this.base_cambio = base_cambio;
    }

    public double getResultadoCambio() {
        return resultadoCambio;
    }

    public double resultadoConversion(){
        return resultadoCambio =base_cambio * this.cantidad;

    }

    LocalDateTime horaFecha = LocalDateTime.now();
    DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
    String horaConFormato = horaFormat.format(horaFecha);

    DecimalFormat df = new DecimalFormat("#.00");

    @Override
    public String toString() {
        return "Conversión: "+cantidad+" ["+monedaInical+ "] equivale a: "+
                df.format(resultadoCambio) +" ["+monedaFinal+"]\n" +
                "Fecha: "+horaConFormato+"\n";
    }
    @Override
    public int compareTo(Moneda otraMoneda) {
        return this.getMonedaInical().compareTo(otraMoneda.getMonedaInical());
    }
}
