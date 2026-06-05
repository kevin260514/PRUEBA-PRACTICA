package dominio.modelo;

public class Cliente {

    private final int id;
    private final String nombre;

    public Cliente(int id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "El nombre del cliente es obligatorio y no puede estar vacio.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("Cliente[id=%d, nombre=%s]", id, nombre);
    }
}
