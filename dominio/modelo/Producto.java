package dominio.modelo;

public class Producto {

    private final int id;
    private final String nombre;
    private final double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int cantidadInicial) {
        validarPrecio(precio);
        validarStock(cantidadInicial);
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = cantidadInicial;
    }

    public void descontarStock(int unidadesVendidas) {
        if (unidadesVendidas > this.stock) {
            throw new IllegalArgumentException(
                String.format(
                    "Stock insuficiente para producto '%s'. Disponible: %d unidades, solicitado: %d unidades.",
                    this.nombre,
                    this.stock,
                    unidadesVendidas));
        }
        this.stock -= unidadesVendidas;
    }

    public double calcularSubtotal(int cantidadVendida) {
        return this.precio * cantidadVendida;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    private void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo.");
        }
    }

    private void validarStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo.");
        }
    }

    @Override
    public String toString() {
        return String.format(
            "Producto[id=%d, nombre=%s, precio=%.2f, stock=%d]",
            id,
            nombre,
            precio,
            stock);
    }
}
