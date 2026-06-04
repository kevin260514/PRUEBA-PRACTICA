package presentacion;

import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import servicio.InventarioServicio;
import servicio.VentaServicio;
import java.util.List;
import java.util.Scanner;

/**
 * Responsable únicamente de interactuar con el usuario.
 * No contiene lógica de negocio — delega todo a los servicios.
 * Si en el futuro se cambia la interfaz a web o móvil,
 * solo esta clase necesita modificarse.
 */
public class Menu {

    private final InventarioServicio inventarioServicio;
    private final VentaServicio      ventaServicio;
    private final Scanner            scanner;

    public Menu(InventarioServicio inventarioServicio, VentaServicio ventaServicio) {
        this.inventarioServicio = inventarioServicio;
        this.ventaServicio      = ventaServicio;
        this.scanner            = new Scanner(System.in);
    }

    public void iniciar() {
        mostrarBienvenida();
        int opcion;
        do {
            mostrarOpciones();
            opcion = leerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
        scanner.close();
    }

    private void mostrarBienvenida() {
        System.out.println("==============================================");
        System.out.println("   SISTEMA DE VENTAS E INVENTARIO");
        System.out.println("   Arquitectura: Monolítico por Capas");
        System.out.println("==============================================");
    }

    private void mostrarOpciones() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Registrar producto");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Realizar venta");
        System.out.println("4. Ver stock");
        System.out.println("5. Reporte de ventas");
        System.out.println("0. Salir");
        System.out.print("Opcion: ");
    }

    private int leerOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return opcion;
    }

    private void ejecutarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1 -> registrarProducto();
                case 2 -> registrarCliente();
                case 3 -> realizarVenta();
                case 4 -> verStock();
                case 5 -> mostrarReporteDeVentas();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opcion invalida.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void registrarProducto() {
        System.out.print("Nombre del producto: "); String nombre = scanner.nextLine();
        System.out.print("Precio unitario: ");     double precio = scanner.nextDouble();
        System.out.print("Stock inicial: ");       int stock     = scanner.nextInt();
        scanner.nextLine();

        Producto producto = inventarioServicio.registrarProducto(nombre, precio, stock);
        System.out.println("Producto registrado: " + producto);
    }

    private void registrarCliente() {
        System.out.print("Nombre del cliente: "); String nombre = scanner.nextLine();

        Cliente cliente = ventaServicio.registrarCliente(nombre);
        System.out.println("Cliente registrado: " + cliente);
    }

    private void realizarVenta() {
        System.out.print("ID del cliente: ");  int clienteId  = scanner.nextInt();
        System.out.print("ID del producto: "); int productoId = scanner.nextInt();
        System.out.print("Cantidad: ");        int cantidad   = scanner.nextInt();
        scanner.nextLine();

        Venta venta = ventaServicio.realizarVenta(clienteId, productoId, cantidad);
        System.out.println("Venta realizada. ID: " + venta.getId() + " | Total: $" + venta.getTotal());
    }

    private void verStock() {
        List<Producto> productos = inventarioServicio.verStock();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("\n--- STOCK DISPONIBLE ---");
        System.out.printf("%-5s %-20s %-10s %-8s%n", "ID", "Nombre", "Precio", "Stock");
        System.out.println("------------------------------------------");
        productos.forEach(p -> System.out.printf("%-5d %-20s $%-9.2f %-8d%n",
                p.getId(), p.getNombre(), p.getPrecio(), p.getStock()));
    }

    private void mostrarReporteDeVentas() {
        List<Venta> ventas = ventaServicio.reporteDeVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        System.out.println("\n--- REPORTE DE VENTAS ---");
        double granTotal = 0;
        for (Venta venta : ventas) {
            Cliente  cliente  = ventaServicio.buscarCliente(venta.getClienteId());
            Producto producto = inventarioServicio.buscarProducto(venta.getProductoId());
            System.out.println("Venta #"     + venta.getId()
                + " | Cliente: "  + cliente.getNombre()
                + " | Producto: " + producto.getNombre()
                + " | Cantidad: " + venta.getCantidad()
                + " | Total: $"   + venta.getTotal());
            granTotal += venta.getTotal();
        }
        System.out.println("------------------------------------------");
        System.out.println("GRAN TOTAL: $" + granTotal);
    }
}