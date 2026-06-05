import java.util.ArrayList;
import java.util.Scanner;

/**
 * RAMA: espagueti
 *
 * Patrón: Código Espagueti
 *
 * Todo el sistema vive en un único método main.
 * No existe separación de responsabilidades: los datos,
 * la lógica de negocio y la interfaz de usuario están mezclados.
 *
 * Esto dificulta el mantenimiento y la escalabilidad del sistema.
 * Esta rama existe para ilustrar el problema que resuelven
 * las arquitecturas por capas y DDD.
 */
public class SistemaVentas {

    public static void main(String[] args) {

        // ---------------------------------------------------------
        // ALMACÉN DE DATOS EN MEMORIA
        // Usamos listas paralelas: el índice 0 de productoIds
        // corresponde al índice 0 de productoNombres, etc.
        // En una arquitectura real esto sería una base de datos.
        // ---------------------------------------------------------

        // Catálogo de productos
        ArrayList<Integer> productoIds     = new ArrayList<>();
        ArrayList<String>  productoNombres = new ArrayList<>();
        ArrayList<Double>  productoPrecios = new ArrayList<>();
        ArrayList<Integer> productoStocks  = new ArrayList<>();
        int siguienteProductoId = 1;

        // Registro de clientes
        ArrayList<Integer> clienteIds     = new ArrayList<>();
        ArrayList<String>  clienteNombres = new ArrayList<>();
        int siguienteClienteId = 1;

        // Historial de ventas
        ArrayList<Integer> ventaIds         = new ArrayList<>();
        ArrayList<Integer> ventaClienteIds  = new ArrayList<>();
        ArrayList<Integer> ventaProductoIds = new ArrayList<>();
        ArrayList<Integer> ventaCantidades  = new ArrayList<>();
        ArrayList<Double>  ventaTotales     = new ArrayList<>();
        int siguienteVentaId = 1;

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n============================");
            System.out.println("  SISTEMA DE VENTAS");
            System.out.println("============================");
            System.out.println("1. Registrar producto");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Realizar venta");
            System.out.println("4. Ver stock");
            System.out.println("5. Reporte de ventas");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            // ---------------------------------------------------------
            // CASO DE USO 1: Registrar un producto en el catálogo
            // El vendedor necesita definir nombre, precio y stock inicial
            // antes de poder realizar ventas de ese producto.
            // ---------------------------------------------------------
            if (opcion == 1) {
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine();

                System.out.print("Precio unitario: ");
                double precio = scanner.nextDouble();

                System.out.print("Stock inicial: ");
                int stockInicial = scanner.nextInt();
                scanner.nextLine();

                // Regla de negocio: precio y stock no pueden ser negativos
                if (precio < 0 || stockInicial < 0) {
                    System.out.println("El precio y el stock deben ser mayores a cero.");
                } else {
                    productoIds.add(siguienteProductoId);
                    productoNombres.add(nombre);
                    productoPrecios.add(precio);
                    productoStocks.add(stockInicial);
                    System.out.println("Producto registrado con ID: " + siguienteProductoId);
                    siguienteProductoId++;
                }
            }

            // ---------------------------------------------------------
            // CASO DE USO 2: Registrar un cliente
            // Un cliente debe existir en el sistema antes de poder
            // realizarle una venta.
            // ---------------------------------------------------------
            else if (opcion == 2) {
                System.out.print("Nombre del cliente: ");
                String nombre = scanner.nextLine();

                clienteIds.add(siguienteClienteId);
                clienteNombres.add(nombre);
                System.out.println("Cliente registrado con ID: " + siguienteClienteId);
                siguienteClienteId++;
            }

            // ---------------------------------------------------------
            // CASO DE USO 3: Realizar una venta
            // Una venta vincula un cliente con un producto.
            // Reglas de negocio:
            //   - El cliente debe estar registrado.
            //   - El producto debe existir en el catálogo.
            //   - Debe haber stock suficiente para cubrir la cantidad.
            //   - Al concretarse la venta, el stock se descuenta.
            // ---------------------------------------------------------
            else if (opcion == 3) {
                System.out.print("ID del cliente: ");
                int clienteId = scanner.nextInt();

                System.out.print("ID del producto: ");
                int productoId = scanner.nextInt();

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                boolean clienteExiste  = clienteIds.contains(clienteId);
                int     indiceProducto = productoIds.indexOf(productoId);
                boolean productoExiste = indiceProducto != -1;

                if (!clienteExiste) {
                    System.out.println("Cliente no encontrado.");
                } else if (!productoExiste) {
                    System.out.println("Producto no encontrado.");
                } else if (productoStocks.get(indiceProducto) < cantidad) {
                    // Regla de negocio: no se puede vender lo que no hay
                    System.out.println("Stock insuficiente. Disponible: "
                        + productoStocks.get(indiceProducto));
                } else {
                    // El total se calcula multiplicando precio por cantidad
                    double totalVenta = productoPrecios.get(indiceProducto) * cantidad;

                    // Descontar el stock vendido del inventario
                    int stockActual      = productoStocks.get(indiceProducto);
                    int stockActualizado = stockActual - cantidad;
                    productoStocks.set(indiceProducto, stockActualizado);

                    // Registrar la venta en el historial
                    ventaIds.add(siguienteVentaId);
                    ventaClienteIds.add(clienteId);
                    ventaProductoIds.add(productoId);
                    ventaCantidades.add(cantidad);
                    ventaTotales.add(totalVenta);

                    System.out.println("Venta realizada. ID: " + siguienteVentaId
                        + " | Total: $" + totalVenta);
                    siguienteVentaId++;
                }
            }

            // ---------------------------------------------------------
            // CASO DE USO 4: Ver stock disponible
            // Permite al vendedor conocer el estado actual del inventario
            // antes de comprometerse con una venta.
            // ---------------------------------------------------------
            else if (opcion == 4) {
                if (productoIds.isEmpty()) {
                    System.out.println("No hay productos registrados.");
                } else {
                    System.out.println("\n--- STOCK DISPONIBLE ---");
                    System.out.printf("%-5s %-20s %-10s %-8s%n",
                        "ID", "Nombre", "Precio", "Stock");
                    System.out.println("------------------------------------------");
                    for (int i = 0; i < productoIds.size(); i++) {
                        System.out.printf("%-5d %-20s $%-9.2f %-8d%n",
                            productoIds.get(i),
                            productoNombres.get(i),
                            productoPrecios.get(i),
                            productoStocks.get(i));
                    }
                }
            }

            // ---------------------------------------------------------
            // CASO DE USO 5: Reporte de ventas
            // Muestra todas las transacciones realizadas y el gran total,
            // útil para que el administrador evalúe el rendimiento.
            // ---------------------------------------------------------
            else if (opcion == 5) {
                if (ventaIds.isEmpty()) {
                    System.out.println("No hay ventas registradas.");
                } else {
                    System.out.println("\n--- REPORTE DE VENTAS ---");
                    double granTotal = 0;

                    for (int i = 0; i < ventaIds.size(); i++) {
                        int indiceCliente  = clienteIds.indexOf(ventaClienteIds.get(i));
                        int indiceProducto = productoIds.indexOf(ventaProductoIds.get(i));

                        System.out.println("Venta #"     + ventaIds.get(i)
                            + " | Cliente: "  + clienteNombres.get(indiceCliente)
                            + " | Producto: " + productoNombres.get(indiceProducto)
                            + " | Cantidad: " + ventaCantidades.get(i)
                            + " | Total: $"   + ventaTotales.get(i));

                        granTotal += ventaTotales.get(i);
                    }
                    System.out.println("------------------------------------------");
                    System.out.println("GRAN TOTAL: $" + granTotal);
                }
            }

            else if (opcion != 0) {
                System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        System.out.println("Hasta luego.");
        scanner.close();
    }
}
