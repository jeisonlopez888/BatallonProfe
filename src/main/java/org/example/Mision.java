package org.example;

import java.time.LocalDate;
import java.util.LinkedList;


public class Mision {
    private String id;
    private LocalDate fecha;
    private String ubicacion;
    private int cantidadPersonal; // Cantidad de personal asignado
    private String vehiculoId;
    private LinkedList<Soldado>listPersonal;// ID del vehículo involucrado

    public Mision(String id, LocalDate fecha, String ubicacion, int cantidadPersonal, String vehiculoId) {
        this.id = id;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.cantidadPersonal = cantidadPersonal;
        this.vehiculoId = vehiculoId;
        this.listPersonal = new LinkedList<>();
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

    public LinkedList<Soldado> getListPersonal() {
        return listPersonal;
    }

    public void setListPersonal(LinkedList<Soldado> listPersonal) {
        this.listPersonal = listPersonal;
    }

    // metodo para agregar un soldado a una mision
    public void agregarSoldado(Soldado soldado) {
        if (soldado.isDisponible()) {
            listPersonal.add(soldado);
            soldado.setDisponible(false); // Marcar como no disponible
        }
    }
}





