package com.test.controlcar;

public class Usuario {
    String Placa, Numero,Hora;
    public Usuario() {
        //testing
    }
    public Usuario(String _Placa , String _Numero, String _Hora){
        this.Placa = _Placa;
        this.Numero = _Numero;
        this.Hora = _Hora;
    }
    public String getPlaca() {
        return Placa;
    }
    public String getNumero() {
        return Numero;
    }
    public String getHora() {
        return Hora;
    }
}