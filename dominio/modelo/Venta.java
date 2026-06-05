package dominio.modelo;

public class Venta {

    private final int id;
    private final int clienteId;
    private final int productoId;
    private final int cantidad;
    private final double total;

    public Venta(int id, int clienteId, int productoId, int cantidad, double total) {
        this.id = id;
        this.clienteId = clienteId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format(
            "Venta[id=%d, cliente=%d, producto=%d, cantidad=%d, total=%.2f]",
            id,
            clienteId,
            productoId,
            cantidad,
            total);
    }
}
