package repositorio;

import modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Responsable de almacenar y recuperar productos en memoria.
 * Simula lo que en un sistema real sería una base de datos.
 */
public class ProductoRepositorio {

    private final List<Producto> productos   = new ArrayList<>();
    private int                  siguienteId = 1;

    public Producto guardar(String nombre, double precio, int stock) {
        Producto nuevo = new Producto(siguienteId++, nombre, precio, stock);
        productos.add(nuevo);
        return nuevo;
    }

    public List<Producto> obtenerTodos() {
        return productos;
    }

    public Optional<Producto> buscarPorId(int id) {
        return productos.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst();
    }
}