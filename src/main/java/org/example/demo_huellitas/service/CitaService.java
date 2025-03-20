package org.example.demo_huellitas.service;

import org.example.demo_huellitas.entity.Cita;
import java.util.List;

public interface CitaService {
    List<Cita> getAllCitasWithNames();
    Cita getCitaById(Integer id);
    Cita addCita(Cita cita);
    Cita updateCita(Integer id, Cita cita);
    void deleteCita(Integer id);
}