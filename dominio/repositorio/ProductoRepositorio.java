package dominio.repositorio;

import dominio.modelo.Producto;
import java.util.List;
import java.util.Optional;

/**
 * Contrato que define qué necesita el dominio para persistir productos.
 *
 * En DDD el dominio define QUÉ necesita mediante interfaces.
 * La infraestructura decide CÓMO implementarlo.
 * Esto permite cambiar de memoria a base de datos real
 * sin tocar nada del dominio.
 */
public interface ProductoRepositorio {
    Producto           guardar(Producto producto);
    Optional<Producto> buscarPorId(int id);
    List<Producto>     obtenerTodos();
}