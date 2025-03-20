package org.example.demo_huellitas.controller;

import org.example.demo_huellitas.entity.Cita;
import org.example.demo_huellitas.service.CitaService;
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
public class CitaController {

    @Autowired
    private CitaService citaService;

    // Obtener todas las citas
    @GetMapping("/citas")
    @ResponseBody
    public ResponseEntity<List<Cita>> getAllCitas() {
        try {
            List<Cita> citas = citaService.getAllCitasWithNames();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener cita por ID
    @GetMapping("/cita/{id}")
    @ResponseBody
    public ResponseEntity<?> getCitaById(@PathVariable Integer id) {
        try {
            Cita cita = citaService.getCitaById(id);
            if (cita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
            }
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }

    // Crear nueva cita (versión unificada)
    @PostMapping("/cita")
    @ResponseBody
    public ResponseEntity<?> addCita(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.addCita(cita);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agendar cita: " + e.getMessage());
        }
    }

    // Actualizar cita
    @PutMapping("/cita/{id}")
    @ResponseBody
    public ResponseEntity<?> updateCita(@PathVariable Integer id, @RequestBody Cita cita) {
        try {
            Cita updatedCita = citaService.updateCita(id, cita);
            if (updatedCita == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
            }
            return ResponseEntity.ok(updatedCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar cita: " + e.getMessage());
        }
    }

    // Eliminar cita
    @DeleteMapping("/cita/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCita(@PathVariable Integer id) {
        try {
            citaService.deleteCita(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar cita: " + e.getMessage());
        }
    }
}