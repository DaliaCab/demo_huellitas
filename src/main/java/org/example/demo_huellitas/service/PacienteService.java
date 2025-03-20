package org.example.demo_huellitas.service;

import org.example.demo_huellitas.entity.Paciente;
import java.util.List;

public interface PacienteService {
    List<Paciente> getAllPacientes();
    Paciente getPacienteById(Integer id);
    Paciente addPaciente(Paciente paciente);
    Paciente updatePaciente(Integer id, Paciente paciente);
    void deletePaciente(Integer id);
}
