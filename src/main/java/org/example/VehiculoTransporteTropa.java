package org.example;

public class VehiculoTransporteTropa extends Vehiculo {
    private int capacidadSoldados;

    public VehiculoTransporteTropa(String id, String modelo, int anioFabricacion, double kilometraje, int misionesCompletadas, EstadoOperativo estadoOperativo, int capacidadSoldados) {
        super(id, modelo, anioFabricacion, kilometraje, misionesCompletadas, estadoOperativo);
        this.capacidadSoldados = capacidadSoldados;
    }

    @Override
    public void desplazar() {
        System.out.println("El vehiculo de tropas se esta moviendo");
    }

    public int getCapacidadSoldados() {
        return capacidadSoldados;
    }

    public void setCapacidadSoldados(int capacidadSoldados) {
        this.capacidadSoldados = capacidadSoldados;
    }
}
