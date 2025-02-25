package org.example.demo_huellitas.controller;

import org.example.demo_huellitas.entity.Empleado;
import org.example.demo_huellitas.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes desde React (que corre en localhost:3000)


//@RequestMapping("/empleados")
@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("empleados")
    @ResponseBody
    public List<Empleado> getAllEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("empleado/{id}")
    @ResponseBody
    public Empleado getEmpleadoById(@PathVariable Integer id) {
        return empleadoService.getEmpleadoById(id);
    }

    @PostMapping("empleado")
    @ResponseBody
    public Empleado addEmpleado(@RequestBody Empleado empleado) {
        empleado = empleadoService.addEmpleado(empleado);
        return getEmpleadoById(empleado.getId());
    }

    // para actualizar el cliente con put
    @PutMapping("/empleado/{id}")
    @ResponseBody
    public ResponseEntity<?> updateEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            Empleado updatedEmpleado = empleadoService.updateEmpleado(id, empleado);
            return ResponseEntity.ok(updatedEmpleado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar");
        }
    }

    // Para actualizar la contraseña del empleado
    @PutMapping("/empleado/{id}/contrasena")
    @ResponseBody
    public ResponseEntity<?> updateContrasena(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        try {
            empleadoService.updateContrasena(id, body.get("contrasena"));
            return ResponseEntity.ok("Contraseña actualizada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cambiar contraseña");
        }
    }

    @DeleteMapping("empleado/{id}")
    @ResponseBody
    public void deleteEmpleado(@PathVariable Integer id) {
        empleadoService.deleteEmpleado(id);
    }

    @PostMapping("/login-empleado")
    @ResponseBody
    public ResponseEntity<?> loginEmpleado(@RequestBody Map<String, String> credentials) {
        try {
            Integer id = Integer.parseInt(credentials.get("id"));
            String contrasena = credentials.get("contrasena");

            Empleado empleado = empleadoService.getEmpleadoById(id);

            if (empleado != null && BCrypt.checkpw(contrasena, empleado.getContrasena())) {
                return ResponseEntity.ok().body(Map.of(
                        "mensaje", "Login exitoso",
                        "id", empleado.getId(),
                        "nombre", empleado.getNombre()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }
}











