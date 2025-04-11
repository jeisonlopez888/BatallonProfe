package org.example;

public class Soldado {
    private String id;
    private String nombre;
    private Rango rango;
    private Funcion funcion;
    private int edad;
    private boolean disponible;

    public Soldado(String id, String nombre, Rango rango, Funcion funcion, int edad, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.rango = rango;
        this.funcion = funcion;
        this.edad = edad;
        this.disponible = disponible;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Rango getRango() {
        return rango;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public int getEdad() {
        return edad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
