package org.example;

import java.time.LocalDate;

import java.time.LocalDate;

public class Mision {
    private String id;
    private LocalDate fecha;
    private String ubicacion;
    private int cantidadPersonal; // Cantidad de personal asignado
    private String vehiculoId; // ID del vehículo involucrado

    public Mision(String id, LocalDate fecha, String ubicacion, int cantidadPersonal, String vehiculoId) {
        this.id = id;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.cantidadPersonal = cantidadPersonal;
        this.vehiculoId = vehiculoId;
    }

    // Métodos getters
    public String getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCantidadPersonal() {
        return cantidadPersonal;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }
}





