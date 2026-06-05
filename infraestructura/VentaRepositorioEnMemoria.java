package infraestructura;

import dominio.modelo.Venta;
import dominio.repositorio.VentaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria del repositorio de ventas.
 * Intercambiable por cualquier otra implementación sin tocar el dominio.
 */
public class VentaRepositorioEnMemoria implements VentaRepositorio {

    private final List<Venta> ventas = new ArrayList<>();

    @Override
    public Venta guardar(Venta venta) {
        ventas.removeIf(v -> v.getId() == venta.getId());
        ventas.add(venta);
        return venta;
    }

    @Override
    public Optional<Venta> buscarPorId(int id) {
        return ventas.stream()
                     .filter(v -> v.getId() == id)
                     .findFirst();
    }

    @Override
    public List<Venta> obtenerTodas() {
        return new ArrayList<>(ventas);
    }
}
