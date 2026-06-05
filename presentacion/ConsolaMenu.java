package presentacion;

import aplicacion.SistemaVentasAplicacion;
import dominio.modelo.Cliente;
import dominio.modelo.Producto;
import dominio.modelo.Venta;
import java.util.List;
import java.util.Scanner;

public class ConsolaMenu {

    private final SistemaVentasAplicacion sistemaVentas;
    private final Scanner scanner;

    public ConsolaMenu(SistemaVentasAplicacion sistemaVentas) {
        this.sistemaVentas = sistemaVentas;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opcion: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println(" SISTEMA DE VENTAS E INVENTARIO");
        System.out.println("========================================");
        System.out.println("1. Registrar producto");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Realizar venta");
        System.out.println("4. Consultar inventario");
        System.out.println("5. Generar reporte de ventas");
        System.out.println("0. Salir");
        System.out.println("----------------------------------------");
    }

    private void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    realizarVenta();
                    break;
                case 4:
                    consultarInventario();
                    break;
                case 5:
                    generarReporteVentas();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private void registrarProducto() {
        System.out.println();
        System.out.println("Registrar producto");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDecimal("Precio: ");
        int cantidadInicial = leerEntero("Stock inicial: ");

        Producto producto = sistemaVentas.registrarProducto(nombre, precio, cantidadInicial);
        System.out.println("Producto registrado: " + producto);
    }

    private void registrarCliente() {
        System.out.println();
        System.out.println("Registrar cliente");
        String nombre = leerTexto("Nombre: ");

        Cliente cliente = sistemaVentas.registrarCliente(nombre);
        System.out.println("Cliente registrado: " + cliente);
    }

    private void realizarVenta() {
        System.out.println();
        System.out.println("Realizar venta");
        int idCliente = leerEntero("ID cliente: ");
        int idProducto = leerEntero("ID producto: ");
        int cantidad = leerEntero("Cantidad: ");

        Venta venta = sistemaVentas.realizarVenta(idCliente, idProducto, cantidad);
        System.out.println("Venta registrada: " + venta);
    }

    private void consultarInventario() {
        System.out.println();
        System.out.println("Inventario");
        List<Producto> productos = sistemaVentas.consultarStock();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private void generarReporteVentas() {
        System.out.println();
        System.out.println("Reporte de ventas");
        List<Venta> ventas = sistemaVentas.generarReporteDeVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.println("Ingresa un numero entero valido.");
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException error) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }
}
