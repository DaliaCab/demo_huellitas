package org.example.demo_huellitas.repo;

import org.example.demo_huellitas.entity.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepoImpl {
    public ClienteRepoImpl() {
    }

    @Value("${spring.datasource.url}")
    String JDBC_URL;

    @Value("${spring.datasource.username}")
    String USERNAME;

    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();

        String query = "SELECT * FROM cliente";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("IDCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFechaAfiliacion(rs.getDate("fechaAfiliacion").toLocalDate());
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Optional<Cliente> findById(Integer id) {
        String query = "SELECT * FROM cliente Where IDCliente = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(query)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("IDCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFechaAfiliacion(rs.getDate("fechaAfiliacion").toLocalDate());
                return Optional.of(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Cliente add(Cliente cliente) {
        String insertQuery = "INSERT INTO cliente (IDCliente, nombre, apellido, contrasena, fechaNacimiento, correo, telefono, fechaAfiliacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
            cliente.setId(getNextId());

            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, cliente.getNombre());
            pstmt.setString(3, cliente.getApellido());
            pstmt.setString(4, cliente.getContrasena());
            pstmt.setDate(5, Date.valueOf(cliente.getFechaNacimiento()));
            pstmt.setString(6, cliente.getCorreo());
            pstmt.setString(7, cliente.getTelefono());
            pstmt.setDate(8, Date.valueOf(cliente.getFechaAfiliacion()));
            pstmt.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente update(Integer id, Cliente cliente){
        Cliente clienteold = findById(id).get();
        if(cliente.getNombre() == null) {
            cliente.setNombre(clienteold.getNombre());
        }
        if(cliente.getApellido() == null) {
            cliente.setApellido(clienteold.getApellido());
        }
        if(cliente.getContrasena() == null) {
            cliente.setContrasena(clienteold.getContrasena());
        }
        if(cliente.getFechaNacimiento() == null) {
            cliente.setFechaNacimiento(clienteold.getFechaNacimiento());
        }
        if(cliente.getCorreo() == null) {
            cliente.setCorreo(clienteold.getCorreo());
        }
        if(cliente.getTelefono() == null) {
            cliente.setTelefono(clienteold.getTelefono());
        }
        if(cliente.getFechaAfiliacion() == null) {
            cliente.setFechaAfiliacion(clienteold.getFechaAfiliacion());
        }
        String updateQuery = "UPDATE cliente SET nombre = ?, apellido = ?, contrasena = ?, fechaNacimiento = ?, correo = ?, telefono = ?, fechaAfiliacion = ? WHERE IDCliente = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(updateQuery)){
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getApellido());
            pstmt.setString(3, cliente.getContrasena());
            pstmt.setDate(4, Date.valueOf(cliente.getFechaNacimiento()));
            pstmt.setString(5, cliente.getCorreo());
            pstmt.setString(6, cliente.getTelefono());
            pstmt.setDate(7, Date.valueOf(cliente.getFechaAfiliacion()));
            pstmt.setInt(8, id);
            pstmt.executeUpdate();
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public void deleteById(Integer id) {
        String deleteQuery = "DELETE FROM cliente WHERE IDCliente = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNextId() {
        String query = "SELECT MAX(IDCliente) as id FROM cliente";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, "");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt("id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
