package org.example;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;

public class Batallon {
    private String nombre;
    private String id;
    private LinkedList<VehiculoTransporteTropa> listVehiculosTransporteTropa;
    private LinkedList<VehiculoBlindado> listVehiculosBlindados;
    private LinkedList<VehiculoApoyo> listVehiculosApoyo;
    private LinkedList<Mision> listMisiones;
    private LinkedList<Soldado> listSoldados;

    public Batallon(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
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
        }
        return null;
    }

    public boolean actualizarVehiculoTransporte(String id, VehiculoTransporteTropa nuevo) {
        for (int i = 0; i < listVehiculosTransporteTropa.size(); i++) {
            if (listVehiculosTransporteTropa.get(i).getId().equals(id)) {
                listVehiculosTransporteTropa.set(i, nuevo);
                return true;
            }
        }
        return false;
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
        }
        return null;
    }

    public boolean actualizarVehiculoBlindado(String id, VehiculoBlindado nuevo) {
        for (int i = 0; i < listVehiculosBlindados.size(); i++) {
            if (listVehiculosBlindados.get(i).getId().equals(id)) {
                listVehiculosBlindados.set(i, nuevo);
                return true;
            }
        }
        return false;
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












    // Metodo para registrar una misión
    public void registrarMision(LocalDate fecha, String ubicacion, int cantidadPersonal, String vehiculoId) {
        Mision mision = new Mision("M" + (listMisiones.size() + 1), fecha, ubicacion, cantidadPersonal, vehiculoId);
        listMisiones.add(mision);

        // Aumentar el contador de misiones completadas del vehículo utilizado
        for (VehiculoTransporteTropa transporte : listVehiculosTransporteTropa) {
            if (transporte.getId().equals(vehiculoId)) {
                transporte.setMisionesCompletadas(transporte.getMisionesCompletadas() + 1);
                return;
            }
        }
        for (VehiculoBlindado blindado : listVehiculosBlindados) {
            if (blindado.getId().equals(vehiculoId)) {
                blindado.setMisionesCompletadas(blindado.getMisionesCompletadas() + 1);
                return;
            }
        }
        for (VehiculoApoyo apoyo : listVehiculosApoyo) {
            if (apoyo.getId().equals(vehiculoId)) {
                apoyo.setMisionesCompletadas(apoyo.getMisionesCompletadas() + 1);
                return;
            }
        }
    }

    // Métodos para mostrar misiones
    public void mostrarMisionesRegistradas() {
        System.out.println("Misiones Registradas:");
        for (Mision m : listMisiones) {
            System.out.println("ID: " + m.getId() + ", Fecha: " + m.getFecha() +
                    ", Ubicación: " + m.getUbicacion() +
                    ", Personal: " + m.getCantidadPersonal() +
                    ", Vehículo Asignado: " + m.getVehiculoId());
        }
    }

    // Metodo para mostrar información del batallón
    public void mostrarInformacion() {
        System.out.println("===== INFORMACIÓN DEL BATALLÓN =====");
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);

        System.out.println("\n--- Vehículos de Transporte de Tropas ---");
        for (VehiculoTransporteTropa v : listVehiculosTransporteTropa) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ", Estado: " + v.getEstadoOperativo() +
                    ", Capacidad: " + v.getCapacidadSoldados());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioTransporteTropa());

        System.out.println("\n--- Vehículos Blindados ---");
        for (VehiculoBlindado v : listVehiculosBlindados) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ", Estado: " + v.getEstadoOperativo() +
                    ", Nivel Blindaje: " + v.getNivelBlindaje());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioBlindados());

        System.out.println("\n--- Vehículos de Apoyo ---");
        for (VehiculoApoyo v : listVehiculosApoyo) {
            System.out.println("ID: " + v.getId() + ", Modelo: " + v.getModelo() + ", Año: " + v.getAnioFabricacion() +
                    ", Kilometraje: " + v.getKilometraje() + ", Estado: " + v.getEstadoOperativo() +
                    ", Tipo Función: " + v.getTipoFuncion());
        }
        System.out.printf("Kilometraje promedio: %.2f km\n", calcularKilometrajePromedioApoyo());

        System.out.println("\n--- Misiones Registradas ---");
        mostrarMisionesRegistradas();
        System.out.println("=======================================");
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

    public void setListVehiculosTransporteTropa(LinkedList<VehiculoTransporteTropa> listVehiculosTransporteTropa) {
        this.listVehiculosTransporteTropa = listVehiculosTransporteTropa;
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
                System.out.println("Vehículo de transporte con ID " + id + " eliminado correctamente.");
                return;
            }
        }
        for (VehiculoBlindado v : listVehiculosBlindados) {
            if (v.getId().equalsIgnoreCase(id)) {
                listVehiculosBlindados.remove(v);
                System.out.println("Vehículo blindado con ID " + id + " eliminado correctamente.");
                return;
            }
        }
        for (VehiculoApoyo v : listVehiculosApoyo) {
            if (v.getId().equalsIgnoreCase(id)) {
                listVehiculosApoyo.remove(v);
                System.out.println("Vehículo de apoyo con ID " + id + " eliminado correctamente.");
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
            System.out.println("ID: " + v.getId()
                    + " | Modelo: " + v.getModelo()
                    + " | Tipo: " + v.getClass().getSimpleName()
                    + " | Misiones completadas: " + v.getMisionesCompletadas());
        }
    }



    public void asignarSoldadoAMision(String idMision, Soldado soldado) {
        for (Mision mision : listMisiones) {
            if (mision.getId().equals(idMision)) {
                if (soldado.isDisponible()) {
                    mision.agregarSoldado(soldado);
                    soldado.setDisponible(false);
                    System.out.println("Soldado asignado a la misión " + idMision);
                } else {
                    System.out.println("El soldado no está disponible para ser asignado.");
                }
                return;
            }
        }
        System.out.println("Misión no encontrada con ID: " + idMision);
    }


    public void registrarMision(Mision mision) {
        this.listMisiones.add(mision);
    }
}




