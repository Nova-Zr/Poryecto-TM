package com.test.controlcar;

public class Instructores {
    String Nombre, Numero, Disponible;
    public Instructores() {

    }
    public Instructores(String _Nombre, String _Disponible, String _Numero) {
        this.Nombre = _Nombre;
        this.Disponible = _Disponible;
        this.Numero = _Numero;
    }
    public String getNombre() { return Nombre; }
    public String getDisponible() { return Disponible; }
    public String getNumero() { return Numero; }
}