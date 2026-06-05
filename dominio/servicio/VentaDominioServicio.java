package dominio.servicio;

import dominio.modelo.Producto;
import dominio.modelo.Venta;
import dominio.repositorio.ClienteRepositorio;
import dominio.repositorio.ProductoRepositorio;
import dominio.repositorio.VentaRepositorio;

/**
 * Servicio de dominio que orquesta el proceso de realizar una venta.
 *
 * En DDD, un servicio de dominio coordina lógica que involucra
 * múltiples entidades. Ninguna entidad sola puede hacer todo esto:
 *   1. Verificar que el cliente existe.
 *   2. Pedirle al Producto que descuente su stock.
 *   3. Crear y persistir la Venta.
 */
public class VentaDominioServicio {

    private final ClienteRepositorio  clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final VentaRepositorio    ventaRepositorio;
    private int                       siguienteVentaId = 1;

    public VentaDominioServicio(ClienteRepositorio clienteRepositorio,
                                ProductoRepositorio productoRepositorio,
                                VentaRepositorio ventaRepositorio) {
        this.clienteRepositorio  = clienteRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.ventaRepositorio    = ventaRepositorio;
    }

    /**
     * Orquesta el proceso completo de una venta usando
     * el lenguaje del negocio: "realizar una venta para un cliente".
     */
    public Venta realizarVenta(int clienteId, int productoId, int cantidad) {
        verificarExistenciaCliente(clienteId);
        Producto producto = buscarProducto(productoId);

        // El propio Producto protege su regla de stock insuficiente
        producto.descontarStock(cantidad);
        productoRepositorio.guardar(producto);

        double totalVenta = producto.calcularSubtotal(cantidad);
        Venta  venta      = new Venta(siguienteVentaId++, clienteId, productoId, cantidad, totalVenta);

        return ventaRepositorio.guardar(venta);
    }

    private void verificarExistenciaCliente(int clienteId) {
        clienteRepositorio.buscarPorId(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));
    }

    private Producto buscarProducto(int productoId) {
        return productoRepositorio.buscarPorId(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productoId));
    }
}