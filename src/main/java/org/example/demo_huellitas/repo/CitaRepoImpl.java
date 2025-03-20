package org.example.demo_huellitas.repo;

import org.example.demo_huellitas.entity.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CitaRepoImpl { // No implementa interfaz

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Metodo para obtener todas las citas con nombres
    public List<Cita> findAllWithNames() {
        String sql = "SELECT "
                + "c.idcita AS id, "
                + "c.idpaciente, " // Incluir ID del paciente
                + "c.idempleado, " // Incluir ID del empleado
                + "p.nombre AS nombrePaciente, "
                + "e.nombre AS nombreEmpleado, "
                + "c.fechahora "
                + "FROM cita c "
                + "JOIN paciente p ON c.idpaciente = p.idpaciente "
                + "JOIN empleado e ON c.idempleado = e.idempleado";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cita.class));
    }


    // Metodo para buscar por ID
    public Cita findById(Integer id) {
        String sql = "SELECT "
                + "c.idcita AS id, "
                + "c.idpaciente, "
                + "c.idempleado, "
                + "p.nombre AS nombrePaciente, "
                + "e.nombre AS nombreEmpleado, "
                + "c.fechahora "
                + "FROM cita c "
                + "JOIN paciente p ON c.idpaciente = p.idpaciente "
                + "JOIN empleado e ON c.idempleado = e.idempleado "
                + "WHERE c.idcita = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cita.class), id);
    }

    //Metodo para agregar una nueva cita
    public Cita add(Cita cita) {
        String sql = "INSERT INTO cita (idcita, idpaciente, idempleado, fechahora) VALUES (?, ?, ?, ?)";
        int nextId = getNextId();
        jdbcTemplate.update(sql,
                nextId,
                cita.getIdPaciente(),
                cita.getIdEmpleado(),
                cita.getFechaHora()
        );
        cita.setId(nextId);
        return cita;
    }

    // Metodo para actualizar una cita
    public Cita update(Integer id, Cita cita) {
        Cita citaold = findById(id);
        if (cita.getIdPaciente() == null) {
            cita.setIdPaciente(citaold.getIdPaciente());
        }
        if (cita.getIdEmpleado() == null) {
            cita.setIdEmpleado(citaold.getIdEmpleado());
        }
        if (cita.getFechaHora() == null) {
            cita.setFechaHora(citaold.getFechaHora());
        }

        String sql = "UPDATE cita SET idpaciente = ?, idempleado = ?, fechahora = ? WHERE idcita = ?";
        jdbcTemplate.update(sql,
                cita.getIdPaciente(),
                cita.getIdEmpleado(),
                cita.getFechaHora(),
                id);
        return cita;
    }

    // Metodo para eliminar por id
    public void deleteById(Integer id) {
        String sql = "DELETE FROM cita WHERE idcita = ?";
        jdbcTemplate.update(sql, id);
    }


    private int getNextId() {
        String sql = "SELECT MAX(idcita) + 1 FROM cita";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

}