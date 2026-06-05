package infraestructura;

import dominio.modelo.Producto;
import dominio.repositorio.ProductoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria del repositorio de productos.
 *
 * En DDD la infraestructura implementa los contratos del dominio.
 * Si mañana se cambia a una base de datos real, solo este archivo
 * cambia — el dominio y la aplicación no se tocan.
 */
public class ProductoRepositorioEnMemoria implements ProductoRepositorio {

    private final List<Producto> productos = new ArrayList<>();

    @Override
    public Producto guardar(Producto producto) {
        productos.removeIf(p -> p.getId() == producto.getId());
        productos.add(producto);
        return producto;
    }

    @Override
    public Optional<Producto> buscarPorId(int id) {
        return productos.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst();
    }

    @Override
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }
}
