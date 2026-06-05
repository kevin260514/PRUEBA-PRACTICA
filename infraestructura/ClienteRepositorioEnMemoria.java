package infraestructura;

import dominio.modelo.Cliente;
import dominio.repositorio.ClienteRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación en memoria del repositorio de clientes.
 * Intercambiable por cualquier otra implementación sin tocar el dominio.
 */
public class ClienteRepositorioEnMemoria implements ClienteRepositorio {

    private final List<Cliente> clientes = new ArrayList<>();

    @Override
    public Cliente guardar(Cliente cliente) {
        clientes.removeIf(c -> c.getId() == cliente.getId());
        clientes.add(cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream()
                       .filter(c -> c.getId() == id)
                       .findFirst();
    }
}
