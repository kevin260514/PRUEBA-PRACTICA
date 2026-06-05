package aplicacion;

import dominio.modelo.Cliente;
import dominio.modelo.Producto;
import dominio.modelo.Venta;
import dominio.repositorio.ClienteRepositorio;
import dominio.repositorio.ProductoRepositorio;
import dominio.repositorio.VentaRepositorio;
import dominio.servicio.VentaDominioServicio;
import java.util.List;

public class SistemaVentasAplicacion {

    private final ProductoRepositorio repositorioProductos;
    private final ClienteRepositorio repositorioClientes;
    private final VentaRepositorio repositorioVentas;
    private final VentaDominioServicio servicioVentas;
    private int proximoIdProducto = 1;
    private int proximoIdCliente = 1;

    public SistemaVentasAplicacion(ProductoRepositorio repositorioProductos,
                                   ClienteRepositorio repositorioClientes,
                                   VentaRepositorio repositorioVentas) {
        this.repositorioProductos = repositorioProductos;
        this.repositorioClientes = repositorioClientes;
        this.repositorioVentas = repositorioVentas;
        this.servicioVentas = new VentaDominioServicio(
            repositorioClientes,
            repositorioProductos,
            repositorioVentas);
    }

    public Producto registrarProducto(String nombre, double precio, int cantidadInicial) {
        Producto nuevoProducto = new Producto(proximoIdProducto++, nombre, precio, cantidadInicial);
        return repositorioProductos.guardar(nuevoProducto);
    }

    public Cliente registrarCliente(String nombre) {
        Cliente nuevoCliente = new Cliente(proximoIdCliente++, nombre);
        return repositorioClientes.guardar(nuevoCliente);
    }

    public Venta realizarVenta(int idCliente, int idProducto, int cantidadSolicitada) {
        return servicioVentas.realizarVenta(idCliente, idProducto, cantidadSolicitada);
    }

    public List<Producto> consultarStock() {
        return repositorioProductos.obtenerTodos();
    }

    public List<Venta> generarReporteDeVentas() {
        return repositorioVentas.obtenerTodas();
    }

    public Cliente buscarCliente(int idCliente) {
        return repositorioClientes.buscarPorId(idCliente)
                .orElseThrow(() -> new IllegalArgumentException(
                    String.format("Cliente no encontrado (ID: %d)", idCliente)));
    }

    public Producto buscarProducto(int idProducto) {
        return repositorioProductos.buscarPorId(idProducto)
                .orElseThrow(() -> new IllegalArgumentException(
                    String.format("Producto no encontrado (ID: %d)", idProducto)));
    }
}
