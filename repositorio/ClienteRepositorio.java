package repositorio;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositorio {

    private final List<Cliente> clientes    = new ArrayList<>();
    private int                 siguienteId = 1;

    public Cliente guardar(String nombre) {
        Cliente nuevo = new Cliente(siguienteId++, nombre);
        clientes.add(nuevo);
        return nuevo;
    }

    public List<Cliente> obtenerTodos() {
        return clientes;
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream()
                       .filter(c -> c.getId() == id)
                       .findFirst();
    }
}