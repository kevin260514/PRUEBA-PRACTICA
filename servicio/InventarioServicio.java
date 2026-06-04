package servicio;

import modelo.Producto;
import repositorio.ProductoRepositorio;
import java.util.List;

/**
 * Contiene la lógica de negocio relacionada con el inventario.
 * Decide qué reglas deben cumplirse antes de registrar o consultar productos.
 */
public class InventarioServicio {

    private final ProductoRepositorio productoRepositorio;

    public InventarioServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public Producto registrarProducto(String nombre, double precio, int stock) {
        validarPrecio(precio);
        validarStock(stock);
        return productoRepositorio.guardar(nombre, precio, stock);
    }

    public List<Producto> verStock() {
        return productoRepositorio.obtenerTodos();
    }

    public Producto buscarProducto(int id) {
        return productoRepositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));
    }

    private void validarPrecio(double precio) {
        if (precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
    }

    private void validarStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
    }
}