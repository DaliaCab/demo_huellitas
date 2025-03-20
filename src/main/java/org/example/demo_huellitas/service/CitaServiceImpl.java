package org.example.demo_huellitas.service;

import org.example.demo_huellitas.entity.Cita;
import org.example.demo_huellitas.repo.CitaRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepoImpl citaRepo; // Inyectar directamente el repo

    @Override
    public List<Cita> getAllCitasWithNames() {
        return citaRepo.findAllWithNames();
    }

    @Override
    public Cita getCitaById(Integer id) {
        return citaRepo.findById(id);
    }

    @Override
    public Cita addCita(Cita cita) {
        return citaRepo.add(cita);
    }

    @Override
    public Cita updateCita(Integer id, Cita cita) {
        return citaRepo.update(id, cita);
    }

    @Override
    public void deleteCita(Integer id) {
        citaRepo.deleteById(id);
    }
}