package dominio.repositorio;

import dominio.modelo.Venta;
import java.util.List;
import java.util.Optional;

/**
 * Contrato que define qué necesita el dominio para persistir ventas.
 * La infraestructura es quien implementa este contrato.
 */
public interface VentaRepositorio {
    Venta           guardar(Venta venta);
    Optional<Venta> buscarPorId(int id);
    List<Venta>     obtenerTodas();
}