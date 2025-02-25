package org.example.demo_huellitas.repo;

import org.example.demo_huellitas.entity.Empleado;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmpleadoRepoImpl {
    public EmpleadoRepoImpl() {
    }

    @Value("${spring.datasource.url}")
    String JDBC_URL;

    @Value("${spring.datasource.username}")
    String USERNAME;

    public List<Empleado> findAll() {
        List<Empleado> empleados = new ArrayList<>();

        String query = "SELECT * FROM empleado";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("IDEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setNumTarjetaProfesional(rs.getString("numTarjetaProfesional"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                empleado.setEspecialidad(rs.getString("especialidad"));
                empleado.setContrasena(rs.getString("contrasena"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Optional<Empleado> findById(Integer id) {
        String query = "SELECT * FROM empleado Where IDEmpleado = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("IDEmpleado"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setNumTarjetaProfesional(rs.getString("numTarjetaProfesional"));
                empleado.setCorreo(rs.getString("correo"));
                empleado.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                empleado.setEspecialidad(rs.getString("especialidad"));
                empleado.setContrasena(rs.getString("contrasena"));
                return Optional.of(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Empleado add(Empleado empleado) {
        String query = "INSERT INTO empleado (IDEmpleado, nombre, apellido, telefono, cargo, numTarjetaProfesional, correo, fechaNacimiento, especialidad, contrasena) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            empleado.setId(getNextId());

            pstmt.setInt(1, empleado.getId());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getApellido());
            pstmt.setString(4, empleado.getTelefono());
            pstmt.setString(5, empleado.getCargo());
            pstmt.setString(6, empleado.getNumTarjetaProfesional());
            pstmt.setString(7, empleado.getCorreo());
            pstmt.setDate(8, Date.valueOf(empleado.getFechaNacimiento()));
            pstmt.setString(9, empleado.getEspecialidad());
            pstmt.setString(10, empleado.getContrasena());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Empleado update(Integer id, Empleado empleado) {
        Empleado empleadoold = findById(id).get();
        if (empleado.getNombre() == null) {
            empleado.setNombre(empleadoold.getNombre());
        }
        if (empleado.getApellido() == null) {
            empleado.setApellido(empleadoold.getApellido());
        }
        if (empleado.getTelefono() == null) {
            empleado.setTelefono(empleadoold.getTelefono());
        }
        if (empleado.getCargo() == null) {
            empleado.setCargo(empleadoold.getCargo());
        }
        if (empleado.getNumTarjetaProfesional() == null) {
            empleado.setNumTarjetaProfesional(empleadoold.getNumTarjetaProfesional());
        }
        if (empleado.getCorreo() == null) {
            empleado.setCorreo(empleadoold.getCorreo());
        }
        if (empleado.getFechaNacimiento() == null) {
            empleado.setFechaNacimiento(empleadoold.getFechaNacimiento());
        }
        if (empleado.getEspecialidad() == null) {
            empleado.setEspecialidad(empleadoold.getEspecialidad());
        }
        if (empleado.getContrasena() == null) {
            empleado.setContrasena(empleadoold.getContrasena());
        }

        String query = "UPDATE empleado SET nombre = ?, apellido = ?, telefono = ?, cargo = ?, numTarjetaProfesional = ?, correo = ?, fechaNacimiento = ?, especialidad = ?, contrasena = ? WHERE IDEmpleado = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setString(3, empleado.getTelefono());
            pstmt.setString(4, empleado.getCargo());
            pstmt.setString(5, empleado.getNumTarjetaProfesional());
            pstmt.setString(6, empleado.getCorreo());
            pstmt.setDate(7, Date.valueOf(empleado.getFechaNacimiento()));
            pstmt.setString(8, empleado.getEspecialidad());
            pstmt.setString(9, empleado.getContrasena());
            pstmt.setInt(10, id);
            pstmt.executeUpdate();
            return empleado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public void deleteById(Integer id) {
        String query = "DELETE FROM empleado WHERE IDEmpleado = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextId() {
        String query = "SELECT MAX(IDEmpleado) FROM empleado";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void save(Empleado empleado) {
        String sql = "UPDATE empleado SET contrasena = ? WHERE IDEmpleado = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, empleado.getContrasena());
            pstmt.setInt(2, empleado.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}