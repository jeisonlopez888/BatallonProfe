package org.example;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class BatallonTest {
    private static final Logger LOG = Logger.getLogger(BatallonTest.class.getName());

    @Test
    public void testRegistrarMision() {
        LOG.info("prueba testRegistrarMision inicio");

        // Preparar el batallón y un vehículo
        Batallon batallon = new Batallon("Batallon Bravo", "Base Central");
        VehiculoBlindado vehiculo = new VehiculoBlindado("V001", "TanqueX", 2020, 1500.0, EstadoOperativo.DISPONIBLE, 5);

        // Agregar el vehículo al batallón
        batallon.getListVehiculosBlindados().add(vehiculo);

        // Ejecutar método: registrar una misión para ese vehículo
        batallon.registrarMision(LocalDate.of(2025, 4, 10), "Zona Norte", 8, "V001");

        // Verificaciones
        assertEquals(1, batallon.getListMisiones().size(), "Debe haber una misión registrada");
        assertEquals(1, vehiculo.getMisionesCompletadas(), "El vehículo debe tener una misión completada");
        assertEquals("V001", batallon.getListMisiones().get(0).getVehiculoId(), "La misión debe estar asociada al vehículo correcto");

        LOG.info("prueba testRegistrarMision fin");


    }


    @Test
    public void testKilometrajePromedioVehiculos() {
        LOG.info("testKilometrajePromedioVehiculos inicio");

        // Crear el batallón
        Batallon batallon = new Batallon("Batallon Alfa", "Base Norte");

        // Transporte de tropa
        batallon.getListVehiculosTransporteTropa().add(new VehiculoTransporteTropa("T001", "TranspA", 2020, 1000.0, EstadoOperativo.DISPONIBLE, 10));
        batallon.getListVehiculosTransporteTropa().add(new VehiculoTransporteTropa("T002", "TranspB", 2021, 2000.0, EstadoOperativo.EN_MISION, 12));
        double promedioTropa = batallon.calcularKilometrajePromedioTransporteTropa();
        assertEquals(1500.0, promedioTropa, 0.001, "Promedio transporte tropa incorrecto");

        // Blindados
        batallon.getListVehiculosBlindados().add(new VehiculoBlindado("B001", "BlindA", 2019, 3000.0, EstadoOperativo.DISPONIBLE, 4));
        batallon.getListVehiculosBlindados().add(new VehiculoBlindado("B002", "BlindB", 2018, 4000.0, EstadoOperativo.EN_MISION, 5));
        double promedioBlindado = batallon.calcularKilometrajePromedioBlindados();
        assertEquals(3500.0, promedioBlindado, 0.001, "Promedio blindados incorrecto");

        // Apoyo
        batallon.getListVehiculosApoyo().add(new VehiculoApoyo("A001", "ApoyoA", 2022, 500.0, EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO));
        batallon.getListVehiculosApoyo().add(new VehiculoApoyo("A002", "ApoyoB", 2023, 1500.0, EstadoOperativo.DISPONIBLE, TipoFuncion.LOGISTICA));
        double promedioApoyo = batallon.calcularKilometrajePromedioApoyo();
        assertEquals(1000.0, promedioApoyo, 0.001, "Promedio apoyo incorrecto");

        LOG.info("testKilometrajePromedioVehiculos fin");
    }



    @Test
    public void testBuscarMisionesPorUbicacionYFechas() {
        LOG.info("prueba testBuscarMisionesPorUbicacionYFechas inicio");

        Batallon batallon = new Batallon("Bravo", "Zona Sur");

        // Crear misiones con diferentes ubicaciones y fechas
        Mision m1 = new Mision("M1", LocalDate.of(2023, 1, 10), "Bogotá", 10, "V1");
        Mision m2 = new Mision("M2", LocalDate.of(2023, 1, 15), "Bogotá", 12, "V2");
        Mision m3 = new Mision("M3", LocalDate.of(2023, 1, 20), "Medellín", 8, "V3");
        Mision m4 = new Mision("M4", LocalDate.of(2023, 1, 25), "Bogotá", 15, "V4");

        // Agregar a la lista del batallón
        LinkedList<Mision> lista = batallon.getListMisiones();
        lista.add(m1);
        lista.add(m2);
        lista.add(m3);
        lista.add(m4);

        // Filtrar por Bogotá entre el 12 y el 26 de enero
        LinkedList<Mision> resultado = batallon.buscarMisionesPorUbicacionYFechas(
                "Bogotá", LocalDate.of(2023, 1, 12), LocalDate.of(2023, 1, 26)
        );

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(m2));
        assertTrue(resultado.contains(m4));
        assertFalse(resultado.contains(m1)); // Fuera del rango
        assertFalse(resultado.contains(m3)); // Otra ubicación

        LOG.info("prueba testBuscarMisionesPorUbicacionYFechas fin");
    }

}


