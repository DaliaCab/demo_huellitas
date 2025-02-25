package org.example.demo_huellitas.service;
import org.example.demo_huellitas.entity.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> getAllClientes();
    Cliente getClienteById(Integer id);
    Cliente addCliente(Cliente cliente);
    Cliente updateCliente(Integer id, Cliente cliente);
    void deleteCliente(Integer id);
}
