package org.example.demo_huellitas.controller;

import org.example.demo_huellitas.entity.Paciente;
import org.example.demo_huellitas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    // Obtener todos los pacientes
    @GetMapping("/pacientes")                 // Ruta para obtener todos los pacientes
    @ResponseBody                               // Para devolver la lista de pacientes
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        try {
            List<Paciente> pacientes = pacienteService.getAllPacientes();
            return ResponseEntity.ok(pacientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener paciente por ID
    @GetMapping("/paciente/{id}")      // Ruta para obtener un paciente por ID
    @ResponseBody                    // Para devolver el paciente
    public ResponseEntity<?> getPacienteById(@PathVariable Integer id) {
        try {
            Paciente paciente = pacienteService.getPacienteById(id);
            if (paciente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
            }
            return ResponseEntity.ok(paciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }

    // Crear nuevo paciente
    @PostMapping("/paciente")         // Ruta para crear un nuevo paciente
    @ResponseBody                    // Para devolver el paciente creado
    public ResponseEntity<?> addPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente nuevoPaciente = pacienteService.addPaciente(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear paciente: " + e.getMessage());
        }
    }

    // Actualizar paciente
    @PutMapping("/paciente/{id}")     // Ruta para actualizar un paciente por ID
    @ResponseBody                    // Para devolver el paciente actualizado
    public ResponseEntity<?> updatePaciente(@PathVariable Integer id, @RequestBody Paciente paciente) {
        try {
            Paciente pacienteActualizado = pacienteService.updatePaciente(id, paciente);
            return ResponseEntity.ok(pacienteActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar paciente: " + e.getMessage());
        }
    }

    // Eliminar paciente
    @DeleteMapping("/paciente/{id}")  // Ruta para eliminar un paciente por ID
    @ResponseBody                    // Para devolver el mensaje de éxito
    public ResponseEntity<?> deletePaciente(@PathVariable Integer id) {
        try {
            pacienteService.deletePaciente(id);
            return ResponseEntity.ok("Paciente eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar paciente: " + e.getMessage());
        }
    }
}
