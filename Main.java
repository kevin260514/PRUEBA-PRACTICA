import presentacion.Menu;
import repositorio.ClienteRepositorio;
import repositorio.ProductoRepositorio;
import repositorio.VentaRepositorio;
import servicio.InventarioServicio;
import servicio.VentaServicio;

/**
 * Punto de entrada del sistema.
 * Aquí se crean y conectan todas las capas:
 * Repositorio → Servicio → Presentación.
 */
public class Main {

    public static void main(String[] args) {
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        ClienteRepositorio  clienteRepositorio  = new ClienteRepositorio();
        VentaRepositorio    ventaRepositorio    = new VentaRepositorio();

        InventarioServicio inventarioServicio = new InventarioServicio(productoRepositorio);
        VentaServicio      ventaServicio      = new VentaServicio(clienteRepositorio, ventaRepositorio, inventarioServicio);

        Menu menu = new Menu(inventarioServicio, ventaServicio);
        menu.iniciar();
    }
}