package servicio;

import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import repositorio.ClienteRepositorio;
import repositorio.VentaRepositorio;
import java.util.List;

/**
 * Contiene la lógica de negocio relacionada con clientes y ventas.
 * Coordina las reglas que involucran múltiples entidades del negocio.
 */
public class VentaServicio {

    private final ClienteRepositorio clienteRepositorio;
    private final VentaRepositorio   ventaRepositorio;
    private final InventarioServicio inventarioServicio;

    public VentaServicio(ClienteRepositorio clienteRepositorio,
                         VentaRepositorio ventaRepositorio,
                         InventarioServicio inventarioServicio) {
        this.clienteRepositorio = clienteRepositorio;
        this.ventaRepositorio   = ventaRepositorio;
        this.inventarioServicio = inventarioServicio;
    }

    public Cliente registrarCliente(String nombre) {
        return clienteRepositorio.guardar(nombre);
    }

    /**
     * Realiza una venta verificando todas las reglas del negocio:
     * - El cliente debe estar registrado.
     * - El producto debe existir en el catálogo.
     * - Debe haber stock suficiente para cubrir la cantidad solicitada.
     */
    public Venta realizarVenta(int clienteId, int productoId, int cantidad) {
        verificarExistenciaCliente(clienteId);
        Producto producto = inventarioServicio.buscarProducto(productoId);
        verificarStockSuficiente(producto, cantidad);

        double totalVenta = producto.getPrecio() * cantidad;
        producto.setStock(producto.getStock() - cantidad);

        return ventaRepositorio.guardar(clienteId, productoId, cantidad, totalVenta);
    }

    public List<Venta> reporteDeVentas() {
        return ventaRepositorio.obtenerTodas();
    }

    public Cliente buscarCliente(int id) {
        return clienteRepositorio.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
    }

    private void verificarExistenciaCliente(int clienteId) {
        clienteRepositorio.buscarPorId(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + clienteId));
    }

    private void verificarStockSuficiente(Producto producto, int cantidad) {
        if (producto.getStock() < cantidad)
            throw new IllegalArgumentException(
                "Stock insuficiente. Disponible: " + producto.getStock() + ", solicitado: " + cantidad);
    }
}