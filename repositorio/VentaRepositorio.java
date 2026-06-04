package repositorio;

import modelo.Venta;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Responsable de almacenar y recuperar ventas en memoria.
 * Simula lo que en un sistema real sería una base de datos.
 */
public class VentaRepositorio {

    private final List<Venta> ventas      = new ArrayList<>();
    private int               siguienteId = 1;

    public Venta guardar(int clienteId, int productoId, int cantidad, double total) {
        Venta nueva = new Venta(siguienteId++, clienteId, productoId, cantidad, total);
        ventas.add(nueva);
        return nueva;
    }

    public List<Venta> obtenerTodas() {
        return ventas;
    }

    public Optional<Venta> buscarPorId(int id) {
        return ventas.stream()
                     .filter(v -> v.getId() == id)
                     .findFirst();
    }
}