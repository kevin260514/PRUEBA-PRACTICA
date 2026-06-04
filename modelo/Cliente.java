package modelo;

/**
 * Entidad del dominio que representa un cliente del negocio.
 * Un cliente debe estar registrado para poder realizar una compra.
 */
public class Cliente {

    private int    id;
    private String nombre;

    public Cliente(int id, String nombre) {
        this.id     = id;
        this.nombre = nombre;
    }

    public int    getId()     { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return String.format("Cliente[id=%d, nombre=%s]", id, nombre);
    }
}