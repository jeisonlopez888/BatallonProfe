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
        LOG.info("testRegistrarMision inicio");

        Batallon batallon = new Batallon("Batallón Norte", "BN001");

        // Crear soldados
        Soldado soldado1 = new Soldado("S001", "Juan Torres", Rango.CABOPRIMERO, Funcion.ARTILLERO, 28, true);
        LinkedList<Soldado> soldadosDisponibles = new LinkedList<>();
        soldadosDisponibles.add(soldado1);

        // Agregar vehículo
        VehiculoBlindado vehiculo  = new VehiculoBlindado("VB001", "Camión A", 2020, 15000, 15, EstadoOperativo.DISPONIBLE, 5);
        batallon.agregarVehiculoBlindado(vehiculo);

        // Registrar misión (verifica que este método agregue la misión internamente)
        batallon.registrarMision("mision", LocalDate.of(2025, 4, 14), "Base Sur", 1, "VB001", soldadosDisponibles);

        // Verificar que la misión fue registrada
        assertFalse(batallon.getListMisiones().isEmpty(), "La lista de misiones no debe estar vacía.");

        Mision m = batallon.getListMisiones().get(0);

        assertEquals("mision1", m.getId());
        assertEquals("Base Sur", m.getUbicacion());
        assertEquals(1, m.getListPersonal().size());

        LOG.info("testRegistrarMision fin");
    }




    @Test
    public void testKilometrajePromedioVehiculos() {
        LOG.info("testKilometrajePromedioVehiculos inicio");

        // Crear el batallón
        Batallon batallon = new Batallon("Batallon Alfa", "Base Norte");

        // Transporte de tropa
        batallon.getListVehiculosTransporteTropa().add(new VehiculoTransporteTropa("T001", "TranspA", 2020, 1000.0,14, EstadoOperativo.DISPONIBLE, 10));
        batallon.getListVehiculosTransporteTropa().add(new VehiculoTransporteTropa("T002", "TranspB", 2021, 2000.0,12, EstadoOperativo.EN_MISION, 12));
        double promedioTropa = batallon.calcularKilometrajePromedioTransporteTropa();
        assertEquals(1500.0, promedioTropa, 0.001, "Promedio transporte tropa incorrecto");

        // Blindados
        batallon.getListVehiculosBlindados().add(new VehiculoBlindado("B001", "BlindA", 2019, 3000.0,25, EstadoOperativo.DISPONIBLE, 4));
        batallon.getListVehiculosBlindados().add(new VehiculoBlindado("B002", "BlindB", 2018, 4000.0,19, EstadoOperativo.EN_MISION, 5));
        double promedioBlindado = batallon.calcularKilometrajePromedioBlindados();
        assertEquals(3500.0, promedioBlindado, 0.001, "Promedio blindados incorrecto");

        // Apoyo
        batallon.getListVehiculosApoyo().add(new VehiculoApoyo("A001", "ApoyoA", 2022, 500.0,10, EstadoOperativo.EN_MANTENIMIENTO, TipoFuncion.MEDICO));
        batallon.getListVehiculosApoyo().add(new VehiculoApoyo("A002", "ApoyoB", 2023, 1500.0,13, EstadoOperativo.DISPONIBLE, TipoFuncion.LOGISTICA));
        double promedioApoyo = batallon.calcularKilometrajePromedioApoyo();
        assertEquals(1000.0, promedioApoyo, 0.001, "Promedio apoyo incorrecto");

        LOG.info("testKilometrajePromedioVehiculos fin");
    }



    @Test
    public void testBuscarMisionesPorUbicacionYFechas() {
        LOG.info("testBuscarMisionesPorUbicacionYFechas inicio");

        // Crear instancia del batallón
        Batallon batallon = new Batallon("Batallón Sur", "BS001");

        // Crear y agregar misiones al batallón
        Mision m1 = new Mision("M001", LocalDate.of(2025, 4, 1), "Zona A", 3, "V001", new LinkedList<>());
        Mision m2 = new Mision("M002", LocalDate.of(2025, 4, 5), "Zona A", 4, "V002", new LinkedList<>());
        Mision m3 = new Mision("M003", LocalDate.of(2025, 4, 10), "Zona B", 2, "V003", new LinkedList<>());
        Mision m4 = new Mision("M004", LocalDate.of(2025, 4, 7), "Zona A", 5, "V004", new LinkedList<>());

        batallon.getListMisiones().add(m1);
        batallon.getListMisiones().add(m2);
        batallon.getListMisiones().add(m3);
        batallon.getListMisiones().add(m4);

        // Definir criterios de búsqueda
        String ubicacionBuscada = "Zona A";
        LocalDate fechaInicio = LocalDate.of(2025, 4, 3);
        LocalDate fechaFin = LocalDate.of(2025, 4, 8);

        // Ejecutar método
        LinkedList<Mision> resultado = batallon.buscarMisionesPorUbicacionYFechas(ubicacionBuscada, fechaInicio, fechaFin);

        // Verificar resultados
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(m2));
        assertTrue(resultado.contains(m4));
        assertFalse(resultado.contains(m1)); // fuera del rango
        assertFalse(resultado.contains(m3)); // ubicación distinta

        LOG.info("testBuscarMisionesPorUbicacionYFechas fin");
    }





    @Test
    public void testAsignarSoldadoAMision() {
        LOG.info("testAsignarSoldadoAMision inicio");

        // Crear batallón
        Batallon batallon = new Batallon("Batallón Central", "BC001");

        // Crear soldado disponible
        Soldado soldado = new Soldado("S001", "Carlos Pérez", Rango.SARGENTO, Funcion.ENFERMERO, 30, true);

        // Agregar soldado a la lista del batallón (si la tienes implementada)
        batallon.getListSoldados().add(soldado); // Solo si tienes esta lista, opcional

        // Crear lista de soldados inicial (vacía)
        LinkedList<Soldado> soldadosIniciales = new LinkedList<>();

        // Crear misión y agregarla al batallón
        Mision mision = new Mision("M001", LocalDate.of(2025, 4, 10), "Zona Norte", 3, "VB001", soldadosIniciales);
        batallon.getListMisiones().add(mision);

        // Ejecutar el método que se va a probar
        batallon.asignarSoldadoAMision("M001", soldado);

        // Obtener misión asignada
        LinkedList<Mision> misiones = batallon.getListMisiones();
        Mision misionAsignada = misiones.getFirst(); // Solo hay una

        // Verificaciones
        assertTrue(misionAsignada.getListPersonal().contains(soldado), "El soldado debe estar asignado a la misión.");
        assertFalse(soldado.isDisponible(), "El soldado debe estar marcado como no disponible.");

        LOG.info("testAsignarSoldadoAMision fin");
    }


    @Test
    public void testLiberarSoldadoDeMision() {
        LOG.info("testLiberarSoldadoDeMision inicio");

        // Crear batallón
        Batallon batallon = new Batallon("Batallon central","BC001");

        // Crear soldados asignados a una misión (marcados como no disponibles)
        Soldado soldado1 = new Soldado("S1", "Juan", Rango.SARGENTO, Funcion.ARTILLERO, 30, false);
        Soldado soldado2 = new Soldado("S2", "Pedro", Rango.CABOSEGUNDO, Funcion.COMUNICACIONES, 25, false);
        LinkedList<Soldado> soldadosAsignados = new LinkedList<>();
        soldadosAsignados.add(soldado1);
        soldadosAsignados.add(soldado2);

        // Agregar misión al batallón
        Mision mision = new Mision("M001", LocalDate.of(2025, 4, 1), "Zona norte", 2, "VT001", soldadosAsignados);
        batallon.getListMisiones().add(mision);

        // Asegurar que el batallón pueda devolver todos los soldados (esto puede variar según tu implementación)
        batallon.getTodosLosSoldados().addAll(soldadosAsignados); // Asegúrate de tener este método o lista

        // Ejecutar
        batallon.liberarSoldadosDeMision("M001");

        // Verificar que los soldados ahora estén disponibles
        for (Soldado s : soldadosAsignados) {
            assertTrue(s.isDisponible(), "El soldado " + s.getNombre() + " debería estar disponible después de liberar la misión.");
        }

        LOG.info("testLiberarSoldadoDeMision fin");
    }


    @Test
    public void testObtenerSoldadosPorFuncion() {
        LOG.info("testObtenerSoldadosPorFuncion inicio");

        // Crear instancia del batallón
        Batallon batallon = new Batallon("Batallon central","BC001");

        // Crear soldados con distintas funciones
        Soldado soldado1 = new Soldado("S1", "Carlos", Rango.CABOSEGUNDO, Funcion.ARTILLERO, 28, true);
        Soldado soldado2 = new Soldado("S2", "Luis", Rango.SARGENTO, Funcion.COMUNICACIONES, 32, true);
        Soldado soldado3 = new Soldado("S3", "Andrés", Rango.TENIENTE, Funcion.ARTILLERO, 35, true);

        // Agregar soldados al batallón
        batallon.getListSoldados().add(soldado1);
        batallon.getListSoldados().add(soldado2);
        batallon.getListSoldados().add(soldado3);

        // Ejecutar método
        LinkedList<Soldado> resultado = batallon.obtenerSoldadosPorFuncion(Funcion.ARTILLERO);

        // Validar que solo se obtienen los soldados con función INFANTERIA
        assertEquals(2, resultado.size(), "Debe haber 2 soldados con función ARTILLERIA");
        assertTrue(resultado.contains(soldado1), "Debe incluir al soldado Carlos");
        assertTrue(resultado.contains(soldado3), "Debe incluir al soldado Andrés");
        assertFalse(resultado.contains(soldado2), "No debe incluir al soldado Luis");

        LOG.info("testObtenerSoldadosPorFuncion fin");
    }


    @Test
    public void testObtenerSoldadosPorRango() {
        LOG.info("testObtenerSoldadosPorRango inicio");

        // Crear instancia del batallón
        Batallon batallon = new Batallon("Batallon central","BC001");

        // Crear soldados con distintos rangos
        Soldado soldado1 = new Soldado("S1", "Camilo", Rango.CABOPRIMERO, Funcion.MEDICO, 25, true);
        Soldado soldado2 = new Soldado("S2", "Jorge", Rango.SARGENTO, Funcion.LOGISTICO, 30, true);
        Soldado soldado3 = new Soldado("S3", "Diana", Rango.CABOPRIMERO, Funcion.COMUNICACIONES, 27, true);

        // Agregar soldados al batallón
        batallon.getListSoldados().add(soldado1);
        batallon.getListSoldados().add(soldado2);
        batallon.getListSoldados().add(soldado3);

        // Ejecutar método para rango CABO
        LinkedList<Soldado> resultado = batallon.obtenerSoldadosPorRango(Rango.CABOPRIMERO);

        // Validaciones
        assertEquals(2, resultado.size(), "Debe haber 2 soldados con rango CABOPRIMERO");
        assertTrue(resultado.contains(soldado1), "Debe incluir al soldado Camilo");
        assertTrue(resultado.contains(soldado3), "Debe incluir a la soldado Diana");
        assertFalse(resultado.contains(soldado2), "No debe incluir al soldado Jorge");

        LOG.info("testObtenerSoldadosPorRango completado exitosamente");
    }


    @Test
    public void testCalcularEdadPromedio() {
        LOG.info("testCalcularEdadPromedio inicio");

        // Crear instancia del batallón
        Batallon batallon = new Batallon("Batallon central","BC001");

        // Agregar soldados con edades conocidas
        Soldado soldado1 = new Soldado("S1", "Laura", Rango.CABOSEGUNDO, Funcion.LOGISTICO, 24, true);
        Soldado soldado2 = new Soldado("S2", "Carlos", Rango.SARGENTO, Funcion.LOGISTICO, 30, true);
        Soldado soldado3 = new Soldado("S3", "Andrés", Rango.TENIENTE, Funcion.COMUNICACIONES, 26, true);

        batallon.getListSoldados().add(soldado1);
        batallon.getListSoldados().add(soldado2);
        batallon.getListSoldados().add(soldado3);

        // Calcular edad promedio
        double promedioEsperado = (24 + 30 + 26) / 3.0; // 26.666...
        double promedioCalculado = batallon.calcularEdadPromedio();

        // Verificar el valor esperado con margen de error pequeño
        assertEquals(promedioEsperado, promedioCalculado, 0.001, "La edad promedio debe coincidir");

        LOG.info("testCalcularEdadPromedio completado exitosamente");
    }


    @Test
    public void testBuscarSoldadoPorId() {
        LOG.info("testBuscarSoldadoPorId inicio");

        // Crear instancia del batallón
        Batallon batallon = new Batallon("Batallon central","BC001");

        // Crear y agregar soldados
        Soldado soldado1 = new Soldado("S001", "Daniel", Rango.CABOTERCERO, Funcion.ENFERMERO, 25, true);
        Soldado soldado2 = new Soldado("S002", "Andrea", Rango.SARGENTO, Funcion.ENFERMERO, 30, true);

        batallon.getListSoldados().add(soldado1);
        batallon.getListSoldados().add(soldado2);

        // Buscar soldado existente
        Soldado encontrado = batallon.buscarSoldadoPorId("S001");
        assertNotNull(encontrado, "El soldado con ID S001 debe encontrarse");
        assertEquals("Daniel", encontrado.getNombre(), "El nombre del soldado encontrado debe ser Daniel");

        // Buscar soldado inexistente
        Soldado noEncontrado = batallon.buscarSoldadoPorId("S999");
        assertNull(noEncontrado, "El soldado con ID S999 no debe encontrarse");

        LOG.info("testBuscarSoldadoPorId completado exitosamente");
    }






















}


