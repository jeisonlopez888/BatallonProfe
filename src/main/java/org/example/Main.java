package org.example;

import java.time.LocalDate;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Crear un batallón
        Batallon batallon = new Batallon("Batallón 1", "B001");

        // ----- PRUEBA: CREAR -----
        VehiculoTransporteTropa transporteNuevo = new VehiculoTransporteTropa("VT999", "Modelo X", 2022, 5000, EstadoOperativo.DISPONIBLE, 40);
        batallon.agregarVehiculoTransporte(transporteNuevo);
        System.out.println("Vehículo agregado:");
        System.out.println(transporteNuevo);

        // ----- PRUEBA: LEER -----
        System.out.println("\nListado de vehículos de transporte:");
        for (Vehiculo v : batallon.getVehiculosTransporteTropa()) {
            System.out.println(v);
        }

        // ----- PRUEBA: ACTUALIZAR -----
        System.out.println("\nEditando vehículo VT999...");
        batallon.editarVehiculoDirecto("VT999", "Modelo Z", 2023, 6000, EstadoOperativo.EN_MANTENIMIENTO);
        System.out.println("Vehículo editado:");
        for (Vehiculo v : batallon.getVehiculosTransporteTropa()) {
            if (v.getId().equals("VT999")) {
                System.out.println(v);
            }
        }

        // ----- PRUEBA: ELIMINAR -----
        System.out.println("\nEliminando vehículo VT999...");
        batallon.eliminarVehiculo("VT999");

        System.out.println("Vehículos después de eliminar:");
        for (Vehiculo v : batallon.getVehiculosTransporteTropa()) {
            System.out.println(v);
        }


        // Registrar vehículos de transporte de tropas
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT001", "Transporte Tropa Modelo A", 2015, 12000, EstadoOperativo.DISPONIBLE, 55));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT002", "Transporte Tropa Modelo B", 2018, 8000, EstadoOperativo.EN_MISION, 30));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT003", "Transporte Tropa Modelo C", 2015, 12800, EstadoOperativo.EN_MANTENIMIENTO, 70));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT004", "Transporte Tropa Modelo D", 2020, 6000, EstadoOperativo.DISPONIBLE, 45));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT005", "Transporte Tropa Modelo E", 2012, 18000, EstadoOperativo.DISPONIBLE, 60));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT006", "Transporte Tropa Modelo F", 2015, 14000, EstadoOperativo.EN_MISION, 50));

        // Registrar vehículos blindados
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB001", "Blindado Modelo A", 2010, 25000, EstadoOperativo.DISPONIBLE, 3));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB002", "Blindado Modelo B", 2012, 30000, EstadoOperativo.EN_MANTENIMIENTO, 0));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB003", "Blindado Modelo C", 2015, 55000, EstadoOperativo.EN_MISION, 1));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB004", "Blindado Modelo D", 2011, 85000, EstadoOperativo.DISPONIBLE, 2));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB005", "Blindado Modelo E", 2016, 45000, EstadoOperativo.DISPONIBLE, 3));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB006", "Blindado Modelo A", 2010, 25000, EstadoOperativo.DISPONIBLE, 60));

        // Registrar vehículos de apoyo
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA001", "Apoyo Modelo A", 2015, 15000, EstadoOperativo.DISPONIBLE, TipoFuncion.COMUNICACIONES));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA002", "Apoyo Modelo B", 2017, 15400, EstadoOperativo.EN_MISION, TipoFuncion.LOGISTICA));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA003", "Apoyo Modelo C", 2014, 19000, EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA004", "Apoyo Modelo D", 2019, 11000, EstadoOperativo.DISPONIBLE, TipoFuncion.COMUNICACIONES));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA005", "Apoyo Modelo E", 2021, 14600, EstadoOperativo.EN_MISION, TipoFuncion.LOGISTICA));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA006", "Apoyo Modelo F", 2012, 17000, EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO));

        // Registrar misiones (el método ya genera automáticamente el ID)
        batallon.registrarMision(LocalDate.of(2023, 10, 1), "Zona de conflicto norte", 30, "VT001");
        batallon.registrarMision(LocalDate.of(2022, 6, 5), "Zona de conflicto B SUR", 25, "VB001");
        batallon.registrarMision(LocalDate.of(2023, 10, 10), "Zona de conflicto Centro", 20, "VA002");
        batallon.registrarMision(LocalDate.of(2021, 10, 12), "Zona de conflicto occidente", 65, "VT005");
        batallon.registrarMision(LocalDate.of(2023, 10, 25), "Zona de conflicto occidente", 30, "VB003");
        batallon.registrarMision(LocalDate.of(2022, 7, 30), "Zona de conflicto sur", 55, "VT006");
        batallon.registrarMision(LocalDate.of(2023, 9, 7), "Zona de conflicto centro", 40, "VT002");
        batallon.registrarMision(LocalDate.of(2023, 12, 1), "Zona de conflicto norte", 30, "VT002");
        batallon.registrarMision(LocalDate.of(2019, 5, 19), "Zona de conflicto norte", 58, "VT002");
        batallon.registrarMision(LocalDate.of(2023, 12, 1), "Zona de conflicto norte", 25, "VT002");
        batallon.registrarMision(LocalDate.of(2024, 11, 28), "Zona de conflicto norte", 70, "VT002");

        // Mostrar información general
        batallon.mostrarInformacion();

        // Buscar misiones por ubicación y fecha
        LinkedList<Mision> resultado = batallon.buscarMisionesPorUbicacionYFechas(
                "Zona de conflicto centro",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2024, 12, 31)
        );

        System.out.println("\nMisiones en 'Zona de conflicto centro' entre 2022 y 2024:");
        for (Mision m : resultado) {
            System.out.println("ID: " + m.getId() + ", Fecha: " + m.getFecha() + ", Vehículo: " + m.getVehiculoId());
        }

        // Mostrar el vehículo con más misiones
        Vehiculo masActivo = batallon.getVehiculoConMasMisiones();
        System.out.println("\nVehículo con más misiones: ID " + masActivo.getId() +
                ", Misiones completadas: " + masActivo.getMisionesCompletadas());
    }
}

