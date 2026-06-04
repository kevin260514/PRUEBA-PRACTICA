package modelo;

/**
 * Entidad del dominio que representa una transacción de venta.
 * Una venta vincula un cliente con un producto y registra
 * la cantidad vendida y el total cobrado.
 */
public class Venta {

    private int    id;
    private int    clienteId;
    private int    productoId;
    private int    cantidad;
    private double total;

    public Venta(int id, int clienteId, int productoId, int cantidad, double total) {
        this.id         = id;
        this.clienteId  = clienteId;
        this.productoId = productoId;
        this.cantidad   = cantidad;
        this.total      = total;
    }

    public int    getId()         { return id; }
    public int    getClienteId()  { return clienteId; }
    public int    getProductoId() { return productoId; }
    public int    getCantidad()   { return cantidad; }
    public double getTotal()      { return total; }

    @Override
    public String toString() {
        return String.format("Venta[id=%d, clienteId=%d, productoId=%d, cantidad=%d, total=%.2f]",
            id, clienteId, productoId, cantidad, total);
    }
}