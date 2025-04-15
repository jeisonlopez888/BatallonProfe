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


    public Mision(String id, LocalDate fecha, String ubicacion, int cantidadPersonal, String vehiculoId,  LinkedList<Soldado> listPersonal) {
        this.id = id;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.cantidadPersonal = cantidadPersonal;
        this.vehiculoId = vehiculoId;
        this.listPersonal = listPersonal;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadPersonal() {
        return cantidadPersonal;
    }

    public void setCantidadPersonal(int cantidadPersonal) {
        this.cantidadPersonal = cantidadPersonal;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public LinkedList<Soldado> getListPersonal() {
        return listPersonal;
    }

    public void setListPersonal(LinkedList<Soldado> listPersonal) {
        this.listPersonal = listPersonal;
    }



    public void agregarSoldado(Soldado soldado) {
        if (soldado.isDisponible()) {
            if (listPersonal.size() < cantidadPersonal) {  // Verificar si hay espacio en la misión
                listPersonal.add(soldado);
                soldado.setDisponible(false); // Marcar como no disponible
            } else {
                System.out.println("No hay suficiente espacio para más soldados en esta misión.");
            }
        } else {
            System.out.println("El soldado no está disponible.");
        }
    }

    public void eliminarSoldado(Soldado soldado) {
        if (listPersonal.remove(soldado)) {
            soldado.setDisponible(true); // Marcar como disponible nuevamente
        } else {
            System.out.println("El soldado no está asignado a esta misión.");
        }
    }

    @Override
    public String toString() {
        return "Misión: " + id + ", Fecha: " + fecha + ", Ubicación: " + ubicacion + ", Vehículo ID: " + vehiculoId + ", Personal asignado: " + listPersonal.size() + "/" + cantidadPersonal;
    }


    public String getListaSoldados() {
        return listPersonal.toString();
    }
}






