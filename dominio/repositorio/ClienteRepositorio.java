package dominio.repositorio;

import dominio.modelo.Cliente;
import java.util.Optional;

/**
 * Contrato que define qué necesita el dominio para persistir clientes.
 * La infraestructura es quien implementa este contrato.
 */
public interface ClienteRepositorio {
    Cliente           guardar(Cliente cliente);
    Optional<Cliente> buscarPorId(int id);
}
