package org.example;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;


public class Batallon {
    private String nombre;
    private String id;
    private LinkedList<Mision> listMisionesCompletadas;
    private LinkedList<VehiculoTransporteTropa> listVehiculosTransporteTropa;
    private LinkedList<VehiculoBlindado> listVehiculosBlindados;
    private LinkedList<VehiculoApoyo> listVehiculosApoyo;
    private LinkedList<Mision> listMisiones;
    private LinkedList<Soldado> listSoldados;



    public Batallon(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.listMisionesCompletadas = new LinkedList<>();
        this.listVehiculosTransporteTropa = new LinkedList<>();
        this.listVehiculosBlindados = new LinkedList<>();
        this.listVehiculosApoyo = new LinkedList<>();
        this.listMisiones = new LinkedList<>();
        this.listSoldados = new LinkedList<>();

    }


    // ================== CRUD Transporte Tropas ==================
    public void crearVehiculoTransporte(VehiculoTransporteTropa vehiculo) {
        listVehiculosTransporteTropa.add(vehiculo);
    }

    public VehiculoTransporteTropa leerVehiculoTransporte(String id) {
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            if (v.getId().equals(id)) return v;
        }return null;
    }

    public boolean actualizarVehiculoTransporte(String id, VehiculoTransporteTropa nuevo) {
        for (int i = 0; i < listVehiculosTransporteTropa.size(); i++) {
            if (listVehiculosTransporteTropa.get(i).getId().equals(id)) {
                listVehiculosTransporteTropa.set(i, nuevo);
                return true;
            }
        }return false;
    }

    public boolean eliminarVehiculoTransporte(String id) {
        return listVehiculosTransporteTropa.removeIf(v -> v.getId().equals(id));
    }

    // ================== CRUD Vehículos Blindados ==================
    public void crearVehiculoBlindado(VehiculoBlindado vehiculo) {
        listVehiculosBlindados.add(vehiculo);
    }

    public VehiculoBlindado leerVehiculoBlindado(String id) {
        for (VehiculoBlindado v : listVehiculosBlindados) {
            if (v.getId().equals(id)) return v;
        }return null;
    }

    public boolean actualizarVehiculoBlindado(String id, VehiculoBlindado nuevo) {
        for (int i = 0; i < listVehiculosBlindados.size(); i++) {
            if (listVehiculosBlindados.get(i).getId().equals(id)) {
                listVehiculosBlindados.set(i, nuevo);
                return true;
            }
        }return false;
    }

    public boolean eliminarVehiculoBlindado(String id) {
        return listVehiculosBlindados.removeIf(v -> v.getId().equals(id));
    }

    // ================== CRUD Vehículos de Apoyo ==================
    public void crearVehiculoApoyo(VehiculoApoyo vehiculo) {
        listVehiculosApoyo.add(vehiculo);
    }

    public VehiculoApoyo leerVehiculoApoyo(String id) {
        for (VehiculoApoyo v : listVehiculosApoyo) {
            if (v.getId().equals(id)) return v;
        }
        return null;
    }

    public boolean actualizarVehiculoApoyo(String id, VehiculoApoyo nuevo) {
        for (int i = 0; i < listVehiculosApoyo.size(); i++) {
            if (listVehiculosApoyo.get(i).getId().equals(id)) {
                listVehiculosApoyo.set(i, nuevo);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarVehiculoApoyo(String id) {
        return listVehiculosApoyo.removeIf(v -> v.getId().equals(id));
    }

    public void editarVehiculoDirecto(String id, String modelo, int anioFabricacion, double kilometraje, EstadoOperativo estadoOperativo) {
        for (Vehiculo v : getTodosLosVehiculos()) {
            if (v.getId().equals(id)) {
                v.setModelo(modelo);
                v.setAnioFabricacion(anioFabricacion);
                v.setKilometraje(kilometraje);
                v.setEstadoOperativo(estadoOperativo);

                // Campos específicos según tipo de vehículo
                if (v instanceof VehiculoTransporteTropa) {
                    VehiculoTransporteTropa vt = (VehiculoTransporteTropa) v;
                    vt.setCapacidadSoldados(50); // Puedes cambiar el valor o pedirlo como parámetro si lo prefieres
                } else if (v instanceof VehiculoBlindado) {
                    VehiculoBlindado vb = (VehiculoBlindado) v;
                    vb.setNivelBlindaje(5); // Cambia el valor si lo necesitas dinámico
                } else if (v instanceof VehiculoApoyo) {
                    VehiculoApoyo va = (VehiculoApoyo) v;
                    va.setTipoFuncion(TipoFuncion.LOGISTICA); // Cambia el valor según prueba
                }

                System.out.println("Vehículo editado exitosamente.");
                return;
            }
        }

        System.out.println("Vehículo con ID " + id + " no encontrado.");
    }

    public LinkedList<Vehiculo> getTodosLosVehiculos() {
        LinkedList<Vehiculo> todos = new LinkedList<>();

        todos.addAll(listVehiculosTransporteTropa);
        todos.addAll(listVehiculosBlindados);
        todos.addAll(listVehiculosApoyo);

        return todos;
    }

    public LinkedList<Soldado> getTodosLosSoldados() {
        return listSoldados;
    }



    public void registrarMision(String id, LocalDate fecha, String ubicacion, int cantidadPersonal, String vehiculoId, LinkedList<Soldado> listPersonal) {
        // Verificar que los parámetros no sean nulos
        if (id== null || fecha == null || ubicacion == null || vehiculoId == null || listPersonal == null) {
            System.out.println("Error: La fecha, ubicación, ID del vehículo o lista de personal no pueden ser nulos.");
            return;
        }

        // Verificar que la cantidad de personal no sea mayor que el tamaño de la lista de soldados
        if (cantidadPersonal > listPersonal.size()) {
            System.out.println("Error: La cantidad de personal no puede ser mayor que el número de soldados disponibles.");
            return;
        }

        // Crear una nueva misión
        LinkedList<Soldado> soldadosAsignados = new LinkedList<>();
        for (int i = 0; i < cantidadPersonal; i++) {
            soldadosAsignados.add(listPersonal.get(i));
        }

        Mision mision = new Mision(id + (listMisiones.size() + 1), fecha, ubicacion, cantidadPersonal, vehiculoId, soldadosAsignados);




        // Aumentar el contador de misiones completadas del vehículo utilizado
        if (!actualizarMisionesVehiculo(vehiculoId)) {
            System.out.println("Error: No se encontró el vehículo con ID " + vehiculoId);
        }

        listMisiones.add(mision);
    }

    private boolean actualizarMisionesVehiculo(String vehiculoId) {
        // Intentamos buscar y actualizar en los tres tipos de vehículos
        boolean vehiculoEncontrado = false;

        for (Vehiculo v : getTodosLosVehiculos()) {
            if (v.getId().equals(vehiculoId)) {
                v.setMisionesCompletadas(v.getMisionesCompletadas() + 1);
                vehiculoEncontrado = true;
                break;
            }
        }return vehiculoEncontrado;
    }


    // Metodo para mostrar información del batallón
    public void mostrarInformacion() {
        System.out.println("===== INFORMACIÓN DEL BATALLÓN =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);

        System.out.println("\n--- Vehículos de Transporte de Tropas ---");
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ",Misiones Completadas: " + v.getMisionesCompletadas()+", Estado: " + v.getEstadoOperativo() +
                    ", Capacidad: " + v.getCapacidadSoldados());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioTransporteTropa());

        System.out.println("\n--- Vehículos Blindados ---");
        for (VehiculoBlindado v : listVehiculosBlindados) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ",Misiones Completadas: " + v.getMisionesCompletadas()+", Estado: " + v.getEstadoOperativo() +
                    ", Nivel Blindaje: " + v.getNivelBlindaje());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioBlindados());

        System.out.println("\n--- Vehículos de Apoyo ---");
        for (VehiculoApoyo v : listVehiculosApoyo) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ",Misiones Completadas: " + v.getMisionesCompletadas()+", Estado: " + v.getEstadoOperativo() +
                    ", Tipo Función: " + v.getTipoFuncion());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioApoyo());

    }

    // Métodos para agregar vehículos
    public void agregarVehiculoTransporte(VehiculoTransporteTropa vehiculo) {
        listVehiculosTransporteTropa.add(vehiculo);
    }

    public void agregarVehiculoBlindado(VehiculoBlindado vehiculo) {
        listVehiculosBlindados.add(vehiculo);
    }

    public void agregarVehiculoApoyo(VehiculoApoyo vehiculo) {
        listVehiculosApoyo.add(vehiculo);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<VehiculoTransporteTropa> getListVehiculosTransporteTropa() {
        return listVehiculosTransporteTropa;
    }

    public void setVehiculosTransporteTropa(VehiculoTransporteTropa[] vehiculosTransporteTropa) {
        listVehiculosTransporteTropa.clear();
        for (VehiculoTransporteTropa v : vehiculosTransporteTropa) {
            listVehiculosTransporteTropa.add(v);
        }
    }


    public LinkedList<VehiculoBlindado> getListVehiculosBlindados() {
        return listVehiculosBlindados;
    }

    public void setListVehiculosBlindados(LinkedList<VehiculoBlindado> listVehiculosBlindados) {
        this.listVehiculosBlindados = listVehiculosBlindados;
    }

    public LinkedList<VehiculoApoyo> getListVehiculosApoyo() {
        return listVehiculosApoyo;
    }

    public void setListVehiculosApoyo(LinkedList<VehiculoApoyo> listVehiculosApoyo) {
        this.listVehiculosApoyo = listVehiculosApoyo;
    }

    public LinkedList<Mision> getListMisiones() {
        return listMisiones;
    }

    public void setListMisiones(LinkedList<Mision> listMisiones) {
        this.listMisiones = listMisiones;
    }

    public int getCantidadMisionesRegistradas() {
        return listMisiones.size();
    }

    // Métodos de cálculo de kilometraje promedio
    public double calcularKilometrajePromedioTransporteTropa() {
        if (listVehiculosTransporteTropa.isEmpty()) return 0;
        double suma = 0;
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            suma += v.getKilometraje();
        }
        return suma / listVehiculosTransporteTropa.size();
    }

    public double calcularKilometrajePromedioBlindados() {
        if (listVehiculosBlindados.isEmpty()) return 0;
        double suma = 0;
        for (VehiculoBlindado v : listVehiculosBlindados) {
            suma += v.getKilometraje();
        }
        return suma / listVehiculosBlindados.size();
    }

    public double calcularKilometrajePromedioApoyo() {
        if (listVehiculosApoyo.isEmpty()) return 0;
        double suma = 0;
        for (VehiculoApoyo v : listVehiculosApoyo) {
            suma += v.getKilometraje();
        }
        return suma / listVehiculosApoyo.size();
    }

    // Metodo para filtrar misiones por fecha y ubicacion
    public LinkedList<Mision> buscarMisionesPorUbicacionYFechas(String ubicacion, LocalDate fechaInicio, LocalDate fechaFin) {
        LinkedList<Mision> misionesFiltradas = new LinkedList<>();

        for (Mision m : listMisiones) {
            if (m.getUbicacion().equalsIgnoreCase(ubicacion) &&
                    (m.getFecha().isEqual(fechaInicio) || m.getFecha().isAfter(fechaInicio)) &&
                    (m.getFecha().isEqual(fechaFin) || m.getFecha().isBefore(fechaFin))) {

                misionesFiltradas.add(m);
            }
        }

        return misionesFiltradas;
    }

    public Vehiculo getVehiculoConMasMisiones() {
        Vehiculo vehiculoConMasMisiones = null;
        int maxMisiones = -1;

        // Buscar en vehículos de transporte de tropas
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            if (v.getMisionesCompletadas() > maxMisiones) {
                maxMisiones = v.getMisionesCompletadas();
                vehiculoConMasMisiones = v;
            }
        }

        // Buscar en vehículos blindados
        for (VehiculoBlindado v : listVehiculosBlindados) {
            if (v.getMisionesCompletadas() > maxMisiones) {
                maxMisiones = v.getMisionesCompletadas();
                vehiculoConMasMisiones = v;
            }
        }

        // Buscar en vehículos de apoyo
        for (VehiculoApoyo v : listVehiculosApoyo) {
            if (v.getMisionesCompletadas() > maxMisiones) {
                maxMisiones = v.getMisionesCompletadas();
                vehiculoConMasMisiones = v;
            }
        }

        return vehiculoConMasMisiones;
    }

    public LinkedList<VehiculoTransporteTropa> getVehiculosTransporte() {
        return listVehiculosTransporteTropa;
    }

    public void eliminarVehiculo(String id) {
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            if (v.getId().equalsIgnoreCase(id)) {
                listVehiculosTransporteTropa.remove(v);
                System.out.println("\nVehículo de transporte con ID " + id + " eliminado correctamente.");
                return;
            }
        }
        for (VehiculoBlindado v : listVehiculosBlindados) {
            if (v.getId().equalsIgnoreCase(id)) {
                listVehiculosBlindados.remove(v);
                System.out.println("\nVehículo blindado con ID " + id + " eliminado correctamente.");
                return;
            }
        }
        for (VehiculoApoyo v : listVehiculosApoyo) {
            if (v.getId().equalsIgnoreCase(id)) {
                listVehiculosApoyo.remove(v);
                System.out.println("\nVehículo de apoyo con ID " + id + " eliminado correctamente.");
                return;
            }
        }

        System.out.println("No se encontró ningún vehículo con el ID " + id + ".");
    }

    public LinkedList<VehiculoTransporteTropa> getVehiculosTransporteTropa() {
        return listVehiculosTransporteTropa;
    }

    public void setVehiculosTransporteTropa(Vehiculo[] vehiculosTransporteTropa) {
        listVehiculosTransporteTropa = new LinkedList<>();
        for (Vehiculo v : vehiculosTransporteTropa) {
            if (v instanceof VehiculoTransporteTropa) {
                listVehiculosTransporteTropa.add((VehiculoTransporteTropa) v);
            }
        }
    }

    public LinkedList<Vehiculo> getVehiculosPorAnioOrdenados(int anio) {
        LinkedList<Vehiculo> resultado = new LinkedList<>();

        // Filtrar cada lista
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            if (v.getAnioFabricacion() == anio) resultado.add(v);
        }
        for (VehiculoBlindado v : listVehiculosBlindados) {
            if (v.getAnioFabricacion() == anio) resultado.add(v);
        }
        for (VehiculoApoyo v : listVehiculosApoyo) {
            if (v.getAnioFabricacion() == anio) resultado.add(v);
        }

        // Ordenar por tipo de vehículo (nombre de clase) y modelo
        resultado.sort(Comparator
                .comparing((Vehiculo v) -> v.getClass().getSimpleName())
                .thenComparing(Vehiculo::getModelo));

        return resultado;

    }

    public void mostrarVehiculosOrdenadosPorMisiones() {
        LinkedList<Vehiculo> todosLosVehiculos = new LinkedList<>();

        todosLosVehiculos.addAll(listVehiculosTransporteTropa);
        todosLosVehiculos.addAll(listVehiculosBlindados);
        todosLosVehiculos.addAll(listVehiculosApoyo);

        todosLosVehiculos.sort((v1, v2) -> Integer.compare(v2.getMisionesCompletadas(), v1.getMisionesCompletadas()));

        for (Vehiculo v : todosLosVehiculos) {
            System.out.println("\nID: " + v.getId()
                    + " | Modelo: " + v.getModelo()
                    + " | Tipo: " + v.getClass().getSimpleName()
                    + " | Misiones completadas: " + v.getMisionesCompletadas());
        }
    }

    public void asignarSoldadoAMision(String id, Soldado soldado) {
        for (Mision mision : listMisiones) {
            if (mision.getId().equals(id)) {
                if (soldado.isDisponible()) {
                    mision.agregarSoldado(soldado);
                    soldado.setDisponible(false);
                    System.out.println("\nSoldado asignado a la misión " + getListMisiones());
                } else {
                    System.out.println("\nEl soldado no está disponible para ser asignado.");
                }
                return;
            }
        }
        System.out.println("\nMisión no encontrada con ID: " + getListMisiones());
    }


    public void liberarSoldadosDeMision(String idMision) {
        boolean misionEncontrada = false;

        for (Mision mision : listMisiones) {
            if (mision.getId().equals(idMision)) {
                misionEncontrada = true;

                System.out.println("✔ Liberando soldados de la misión: " + idMision);

                for (Soldado s : mision.getListPersonal()) {
                    s.setDisponible(true); // marcar como disponible
                    System.out.println("\n- Liberado: " + s.getNombre() + " (" + s.getRango() + ")");
                }

                break;
            }
        }

        if (!misionEncontrada) {
            System.out.println("❌ No se encontró la misión con ID " + idMision);
            return;
        }

        // Mostrar soldados disponibles (todos los que estén marcados como disponibles)
        System.out.println("\n🟢 Soldados disponibles:");
        for (Soldado s : getTodosLosSoldados()) {
            if (s.isDisponible()) {
                System.out.println("- " + s.getNombre() + " (" + s.getRango() + ")");
            }
        }

        // Mostrar soldados que aún no han sido liberados
        System.out.println("\n🔴 Soldados aún en misión:");
        for (Soldado s : getTodosLosSoldados()) {
            if (!s.isDisponible()) {
                System.out.println("- " + s.getNombre() + " (" + s.getRango() + ")");
            }
        }
    }



    public LinkedList<Soldado> obtenerSoldadosPorFuncion(Funcion funcion) {
        LinkedList<Soldado> soldadosFiltrados = new LinkedList<>();
        for (Soldado s : listSoldados) {
            if (s.getFuncion() == funcion) {
                soldadosFiltrados.add(s);
            }
        }
        return soldadosFiltrados;
    }

    public void mostrarSoldadosPorFuncion(Funcion funcion) {
        LinkedList<Soldado> soldados = obtenerSoldadosPorFuncion(funcion);
        System.out.println("\nSoldados con función: " + funcion);
        if (soldados.isEmpty()) {
            System.out.println("No hay soldados con esta función.");
        } else {
            for (Soldado s : soldados) {
                System.out.println("\nID: " + s.getId() + "\n, Nombre: " + s.getNombre() +
                        "\n, Rango: " + s.getRango() + "\n, Edad: " + s.getEdad());
            }
        }
    }



    public void asignarSoldadoAMision(Soldado soldado1) {
    }

    public void agregarSoldado(Soldado soldado) {
        listSoldados.add(soldado);
    }

    public void mostrarSoldadosPorFuncion() {

    }

    public void setListVehiculosTransporteTropa(LinkedList<VehiculoTransporteTropa> listVehiculosTransporteTropa) {
        this.listVehiculosTransporteTropa = listVehiculosTransporteTropa;
    }

    public LinkedList<Soldado> getListSoldados() {
        return listSoldados;
    }

    public void setListSoldados(LinkedList<Soldado> listSoldados) {
        this.listSoldados = listSoldados;
    }


    public LinkedList<Soldado> obtenerSoldadosPorRango(Rango rango) {
        LinkedList<Soldado> soldadosFiltrados = new LinkedList<>();
        for (Soldado s : listSoldados) {
            if (s.getRango() == rango) {
                soldadosFiltrados.add(s);
            }
        }
        return soldadosFiltrados;
    }

    public void mostrarSoldadosPorRango(Rango rango) {
        LinkedList<Soldado> soldados = obtenerSoldadosPorRango(rango);
        System.out.println("\nSoldados con Rango: " + rango);
        if (soldados.isEmpty()) {
            System.out.println("No hay soldados con este rango.");
        } else {
            for (Soldado s : soldados) {
                System.out.println("\nID: " + s.getId() + "\n, Nombre: " + s.getNombre() +
                        "\n, Rango: " + s.getRango() + "\n, Edad: " + s.getEdad());
            }
        }
    }
    public LinkedList<Mision> getListMisionesCompletadas() {
        return listMisionesCompletadas;
    }

    public void setListMisionesCompletadas(LinkedList<Mision> listMisionesCompletadas) {
        this.listMisionesCompletadas = listMisionesCompletadas;
    }


    public double calcularEdadPromedio() {
        if (listSoldados.isEmpty()) {
            return 0.0; // Para evitar división por cero
        }

        int sumaEdades = 0;

        for (Soldado s : listSoldados) {
            sumaEdades += s.getEdad();
        }

        return (double) sumaEdades / listSoldados.size();
    }

    public Soldado buscarSoldadoPorId(String id) {
        for (Soldado s : listSoldados) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null; // No se encontró
    }
}
















