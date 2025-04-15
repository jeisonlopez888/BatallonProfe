package org.example;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un batallón
        Batallon batallon = new Batallon("Batallón 1", "B001");




        // Registrar vehículos de transporte de tropas
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT001", "Transporte Tropa Modelo A", 2015, 12000,9, EstadoOperativo.DISPONIBLE, 55));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT002", "Transporte Tropa Modelo B", 2018, 8000,15, EstadoOperativo.EN_MISION, 30));
        batallon.agregarVehiculoTransporte(new VehiculoTransporteTropa("VT003", "Transporte Tropa Modelo C", 2015, 12800,18, EstadoOperativo.EN_MANTENIMIENTO, 70));

        // Registrar vehículos blindados
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB001", "Blindado Modelo A", 2015, 25000,10, EstadoOperativo.DISPONIBLE, 3));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB002", "Blindado Modelo B", 2012, 30000,20, EstadoOperativo.EN_MANTENIMIENTO, 0));
        batallon.agregarVehiculoBlindado(new VehiculoBlindado("VB003", "Blindado Modelo C", 2015, 55000,8, EstadoOperativo.EN_MISION, 1));

        // Registrar vehículos de apoyo
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA001", "Apoyo Modelo A", 2015, 15000,11, EstadoOperativo.DISPONIBLE, TipoFuncion.COMUNICACIONES));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA002", "Apoyo Modelo B", 2017, 15400,21, EstadoOperativo.EN_MISION, TipoFuncion.LOGISTICA));
        batallon.agregarVehiculoApoyo(new VehiculoApoyo("VA003", "Apoyo Modelo C", 2014, 19000,16, EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO));




        // Crear un soldado disponible
        Soldado soldado1 = new Soldado("S001", "Juan Pérez", Rango.SARGENTO, Funcion.MEDICO, 20, true);
        Soldado soldado2 = new Soldado("S002", "Jhon Lopez", Rango.CABOPRIMERO, Funcion.COMUNICACIONES, 22, true);
        Soldado soldado3 = new Soldado("S003", "Felipe Ramirez", Rango.MAYOR, Funcion.ARTILLERO, 37, true);
        Soldado soldado4 = new Soldado("S004", "Luis Gonsalez", Rango.SOLDADO, Funcion.MEDICO, 25, true);
        Soldado soldado5 = new Soldado("S005", "Raul Vallejo", Rango.SOLDADO, Funcion.ENFERMERO, 24, true);
        Soldado soldado6 = new Soldado("S006", "Pepito Perez", Rango.SOLDADO, Funcion.ARTILLERO, 24, true);
        Soldado soldado7 = new Soldado("S007", "juan gil", Rango.SOLDADO, Funcion.COMUNICACIONES, 24, true);
        Soldado soldado8 = new Soldado("S008", "rodrigo grueso", Rango.SOLDADO, Funcion.MEDICO, 24, true);
        Soldado soldado9 = new Soldado("S009", "Fernando Vallejo", Rango.SOLDADO, Funcion.ENFERMERO, 24, false);
        Soldado soldado10 = new Soldado("S010", "Raul garcia", Rango.SOLDADO, Funcion.ENFERMERO, 24, false);

        batallon.getListSoldados().add(soldado1);
        batallon.getListSoldados().add(soldado2);
        batallon.getListSoldados().add(soldado3);
        batallon.getListSoldados().add(soldado4);
        batallon.getListSoldados().add(soldado5);
        batallon.getListSoldados().add(soldado6);
        batallon.getListSoldados().add(soldado7);
        batallon.getListSoldados().add(soldado8);
        batallon.getListSoldados().add(soldado9);
        batallon.getListSoldados().add(soldado10);





        batallon.asignarSoldadoAMision(soldado1);
        batallon.asignarSoldadoAMision(soldado2);
        batallon.asignarSoldadoAMision(soldado3);
        batallon.asignarSoldadoAMision(soldado4);
        batallon.asignarSoldadoAMision(soldado5);
        batallon.asignarSoldadoAMision(soldado6);
        batallon.asignarSoldadoAMision(soldado7);
        batallon.asignarSoldadoAMision(soldado8);
        batallon.asignarSoldadoAMision(soldado9);
        batallon.asignarSoldadoAMision(soldado10);

        // Mostrar información general
        batallon.mostrarInformacion();





        batallon.mostrarSoldadosPorFuncion(Funcion.MEDICO);
        batallon.mostrarSoldadosPorFuncion(Funcion.ARTILLERO);
        batallon.mostrarSoldadosPorFuncion(Funcion.ENFERMERO);
        batallon.mostrarSoldadosPorFuncion(Funcion.COMUNICACIONES);


        batallon.mostrarSoldadosPorRango(Rango.CABOPRIMERO);
        batallon.mostrarSoldadosPorRango(Rango.CABOSEGUNDO);
        batallon.mostrarSoldadosPorRango(Rango.SOLDADO);
        batallon.mostrarSoldadosPorRango(Rango.MAYOR);
        batallon.mostrarSoldadosPorRango(Rango.SARGENTO);
        batallon.mostrarSoldadosPorRango(Rango.CABOPRIMERO);
        batallon.mostrarSoldadosPorRango(Rango.CAPITAN);
        batallon.mostrarSoldadosPorRango(Rango.CABOTERCERO);


        double edadPromedio = batallon.calcularEdadPromedio();
        System.out.println("\n\nEdad promedio del personal : "  + edadPromedio+ " años" );


        LinkedList<Soldado> soldadosAsignados = new LinkedList<>();

        // Agregar misión al batallón
        Mision mision1 = new Mision("mision1", LocalDate.of(2022, 10, 1), "Zona de conflicto sur", 3, "VT001",soldadosAsignados);
        Mision mision2 = new Mision("mision2", LocalDate.of(2023, 2, 11), "Zona de conflicto oriente", 3, "VT002", soldadosAsignados);
        Mision mision3 = new Mision("mision3", LocalDate.of(2023, 3, 15), "Zona de conflicto oriente", 3, "VB001", soldadosAsignados);
        Mision mision4 = new Mision("mision4", LocalDate.of(2023, 7, 6), "Zona de conflicto sur", 3, "VA001",soldadosAsignados);

        batallon.getListMisiones().add(mision1);
        batallon.getListMisiones().add(mision2);
        batallon.getListMisiones().add(mision3);
        batallon.getListMisiones().add(mision4);


        // Asignar soldado a la misión (esto cambia su disponibilidad)
        batallon.asignarSoldadoAMision("mision1", soldado1);
        batallon.asignarSoldadoAMision("mision1", soldado2);
        batallon.asignarSoldadoAMision("mision2", soldado3);
        batallon.asignarSoldadoAMision("mision2", soldado4);
        batallon.asignarSoldadoAMision("mision3", soldado5);
        batallon.asignarSoldadoAMision("mision3", soldado6);
        batallon.asignarSoldadoAMision("mision4", soldado7);
        batallon.asignarSoldadoAMision("mision4", soldado8);

        // Verificación
        System.out.println("¿Soldado disponible? " + soldado9.isDisponible()); // Debería ser false

        // Mostrar soldados asignados a la misión
        LinkedList<Object> listMisiones = new LinkedList<>();
        if (listMisiones.isEmpty()) {
            System.out.println("\nNo hay misiones registradas.");
        } else {
            Mision mision = (Mision) listMisiones.getLast(); // O .get(listMisiones.size() - 1)
            System.out.println("\nSoldados asignados a la misión: \n");
            for (Soldado s : mision.getListPersonal()) {
                System.out.println("- " + s.getNombre() + " (" + s.getRango() + ")");
            }
        }


        // Buscar misiones por ubicación y fecha
        LinkedList<Mision> resultado = batallon.buscarMisionesPorUbicacionYFechas(
                "Zona de conflicto sur",
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

        // metodo mostrar vehiculos por año de fabricacion
        LinkedList<Vehiculo> vehiculos2015 = batallon.getVehiculosPorAnioOrdenados(2015);
        System.out.println("\nVehículos fabricados en 2015:");
        for (Vehiculo v : vehiculos2015) {
            System.out.println(v.getClass().getSimpleName() + " - Modelo: " + v.getModelo() + " (ID: " + v.getId() + ")");
        }

        // Mostrar vehículos ordenados por misiones
        System.out.println("\nVehículos ordenados por misiones completadas:");
        batallon.mostrarVehiculosOrdenadosPorMisiones();


        // ----- PRUEBA: CREAR -----
        VehiculoTransporteTropa transporteNuevo = new VehiculoTransporteTropa("VT999", "Modelo X", 2022, 5000,17, EstadoOperativo.DISPONIBLE, 40);
        batallon.agregarVehiculoTransporte(transporteNuevo);
        System.out.println("\nVehículo agregado:");
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

        System.out.println("\nVehículos después de eliminar:");
        for (Vehiculo v : batallon.getVehiculosTransporteTropa()) {
            System.out.println(v);
        }


        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el ID de la misión que desea finalizar: ");
        String idMision = scanner.nextLine();

        batallon.liberarSoldadosDeMision(idMision);



        System.out.print("\n\nIngrese el ID del soldado a buscar: ");
        String idBuscado = scanner.nextLine();

        Soldado encontrado = batallon.buscarSoldadoPorId(idBuscado);

        if (encontrado != null) {
            System.out.println("\nSoldado encontrado: " + encontrado.getNombre() + " (" + encontrado.getRango() + ")");
            System.out.println(encontrado); // O puedes mostrar campos específicos
        } else {
            System.out.println("No se encontró ningún soldado con ese ID.");
        }


    }




}

