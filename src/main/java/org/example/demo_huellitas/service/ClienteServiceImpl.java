package org.example.demo_huellitas.service;

import org.example.demo_huellitas.entity.Cliente;
import org.example.demo_huellitas.repo.ClienteRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepoImpl clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.add(cliente);
    }

    @Override
    public Cliente updateCliente(Integer id, Cliente cliente) {
        return clienteRepository.update(id, cliente);
    }

    @Override
    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}