package org.example.demo_huellitas.service;
import org.example.demo_huellitas.entity.Empleado;
import org.example.demo_huellitas.repo.EmpleadoRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepoImpl empleadoRepository;

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public void updateContrasena(Integer id, String nuevaContrasena) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        // Hashea la nueva contraseña
        String hashedPassword = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
        empleado.setContrasena(hashedPassword);

        empleadoRepository.save(empleado);
    }

    @Override
    public Empleado getEmpleadoById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado addEmpleado(Empleado empleado) {
        // Hashear la contraseña antes de guardar
        String hashedPassword = BCrypt.hashpw(empleado.getContrasena(), BCrypt.gensalt());
        empleado.setContrasena(hashedPassword);
        return empleadoRepository.add(empleado);
    }

    @Override
    public Empleado updateEmpleado(Integer id, Empleado empleado) {
        return empleadoRepository.update(id, empleado);
    }

    @Override
    public void deleteEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
