package org.example.demo_huellitas.controller;

import org.example.demo_huellitas.entity.Cliente;
import org.example.demo_huellitas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RequestMapping("/clientes")
@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("clientes")
    @ResponseBody
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("cliente/{id}")
    @ResponseBody
    public Cliente getClienteById(@PathVariable Integer id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping("cliente")
    @ResponseBody
    public Cliente addCliente(@RequestBody Cliente cliente) {
        cliente = clienteService.addCliente(cliente);
        return getClienteById(cliente.getId());
    }


    @PutMapping("cliente/{id}")
    @ResponseBody
    public Cliente updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        cliente = clienteService.updateCliente(id, cliente);
        return getClienteById(cliente.getId());
    }

    @DeleteMapping("cliente/{id}")
    @ResponseBody
    public void deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
    }
}
