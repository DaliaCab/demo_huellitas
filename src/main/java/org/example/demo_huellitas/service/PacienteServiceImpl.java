package org.example.demo_huellitas.service;

import org.example.demo_huellitas.entity.Paciente;
import org.example.demo_huellitas.repo.PacienteRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepoImpl pacienteRepo; // Inyectar directamente el repo

    @Override
    public List<Paciente> getAllPacientes() {
        return pacienteRepo.findAll();
    }

    @Override
    public Paciente getPacienteById(Integer id) {
        return pacienteRepo.findById(id);
    }

    @Override
    public Paciente addPaciente(Paciente paciente) {
        return pacienteRepo.add(paciente);
    }

    @Override
    public Paciente updatePaciente(Integer id, Paciente paciente) {
        return pacienteRepo.update(id, paciente);
    }

    @Override
    public void deletePaciente(Integer id) {
        pacienteRepo.deleteById(id);
    }
}
