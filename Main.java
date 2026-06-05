import aplicacion.SistemaVentasAplicacion;
import dominio.repositorio.ClienteRepositorio;
import dominio.repositorio.ProductoRepositorio;
import dominio.repositorio.VentaRepositorio;
import infraestructura.ClienteRepositorioEnMemoria;
import infraestructura.ProductoRepositorioEnMemoria;
import infraestructura.VentaRepositorioEnMemoria;
import presentacion.ConsolaMenu;

public class Main {

    public static void main(String[] args) {
        ProductoRepositorio repositorioProductos = new ProductoRepositorioEnMemoria();
        ClienteRepositorio repositorioClientes = new ClienteRepositorioEnMemoria();
        VentaRepositorio repositorioVentas = new VentaRepositorioEnMemoria();

        SistemaVentasAplicacion sistemaVentas = new SistemaVentasAplicacion(
            repositorioProductos,
            repositorioClientes,
            repositorioVentas);

        ConsolaMenu menu = new ConsolaMenu(sistemaVentas);
        menu.iniciar();
    }
}
