package org.example.demo_huellitas.repo;

import org.example.demo_huellitas.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PacienteRepoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate; // InyectaMos JdbcTemplate para ejecutar queries

    // Metodo para obtener todos los pacientes
    public List<Paciente> findAll() {
        try{
            String sql = "SELECT * FROM paciente";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Paciente.class));
        }
        catch (Exception e) {
            System.err.println("Error al obtener todos los pacientes: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Metodo para buscar por ID
    public Paciente findById(Integer id) {
        String sql = "SELECT * FROM paciente WHERE idpaciente = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Paciente.class), id);
    }

    //Metodo para agregar un nuevo paciente
    public Paciente add(Paciente paciente) {
        String sql = "INSERT INTO paciente (idpaciente, idcliente, nombre, fechanacimiento, especie, genero, raza, color, peso, tamano, alergias, enfermedadescronicas, vacunas, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int nextId = getNextId();
        jdbcTemplate.update(sql,
                nextId,
                paciente.getIDCliente(),
                paciente.getNombre(),
                paciente.getFechaNacimiento(),
                paciente.getEspecie(),
                paciente.getGenero(),
                paciente.getRaza(),
                paciente.getColor(),
                paciente.getPeso(),
                paciente.getTamano(),
                paciente.getAlergias(),
                paciente.getEnfermedadesCronicas(),
                paciente.getVacunas(),
                paciente.getEstado()
        );
        paciente.setIdpaciente(nextId);
        return paciente;
    }

    //Metodo para actualizar un paciente
    public Paciente update(Integer id, Paciente paciente) {
        Paciente pacienteold = findById(id);
        if(paciente.getIDCliente() == null) {
            paciente.setIDCliente(pacienteold.getIDCliente());
        }
        if(paciente.getNombre() == null) {
            paciente.setNombre(pacienteold.getNombre());
        }
        if (paciente.getEspecie() == null) {
            paciente.setEspecie(pacienteold.getEspecie());
        }
        if (paciente.getFechaNacimiento() == null) {
            paciente.setFechaNacimiento(pacienteold.getFechaNacimiento());
        }
        if (paciente.getRaza() == null) {
            paciente.setRaza(pacienteold.getRaza());
        }
        if (paciente.getGenero() == null) {
            paciente.setGenero(pacienteold.getGenero());
        }
        if (paciente.getColor() == null) {
            paciente.setColor(pacienteold.getColor());
        }
        if (paciente.getPeso() == null) {
            paciente.setPeso(pacienteold.getPeso());
        }
        if (paciente.getTamano() == null) {
            paciente.setTamano(pacienteold.getTamano());
        }
        if (paciente.getAlergias() == null) {
            paciente.setAlergias(pacienteold.getAlergias());
        }
        if (paciente.getEnfermedadesCronicas() == null) {
            paciente.setEnfermedadesCronicas(pacienteold.getEnfermedadesCronicas());
        }
        if (paciente.getVacunas() == null) {
            paciente.setVacunas(pacienteold.getVacunas());
        }
        if (paciente.getEstado() == null) {
            paciente.setEstado(pacienteold.getEstado());
        }
        String sql = "UPDATE paciente SET idcliente = ?, nombre = ?, fechanacimiento = ?, especie = ?, genero = ?, raza = ?, color = ?, peso = ?, tamano = ?, alergias = ?, enfermedadescronicas = ?, vacunas = ?, estado = ? WHERE idpaciente = ?";        jdbcTemplate.update(sql,
                paciente.getIDCliente(),
                paciente.getNombre(),
                paciente.getFechaNacimiento(),
                paciente.getEspecie(),
                paciente.getGenero(),
                paciente.getRaza(),
                paciente.getColor(),
                paciente.getPeso(),
                paciente.getTamano(),
                paciente.getAlergias(),
                paciente.getEnfermedadesCronicas(),
                paciente.getVacunas(),
                paciente.getEstado(),
                id
        );
        paciente.setIdpaciente(id);
        return paciente;
    }

    // Metodo para eliminar un paciente por id
    public void deleteById(Integer id) {
        String sql = "DELETE FROM paciente WHERE idpaciente = ?";
        jdbcTemplate.update(sql, id);
    }

    // Metodo para obtener el siguiente ID
    private int getNextId() {
        String sql = "SELECT MAX(idpaciente)+ 1 FROM paciente";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
