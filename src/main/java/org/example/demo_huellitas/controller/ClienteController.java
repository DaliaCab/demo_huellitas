package org.example.demo_huellitas.controller;

import org.example.demo_huellitas.entity.Cliente;
import org.example.demo_huellitas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes desde React (que corre en localhost:3000)


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

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            Integer id = Integer.parseInt(credentials.get("id"));
            String contrasena = credentials.get("contrasena");

            Cliente empleado = clienteService.getClienteById(id);

            if (empleado != null && empleado.getContrasena().equals(contrasena)) {
                return ResponseEntity.ok().body(Map.of(
                        "mensaje", "Login exitoso",
                        "id", empleado.getId(),
                        "nombre", empleado.getNombre()
                ));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "mensaje", "Credenciales incorrectas"
                ));
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "mensaje", "Error interno del servidor"
            ));
        }
    }
}

