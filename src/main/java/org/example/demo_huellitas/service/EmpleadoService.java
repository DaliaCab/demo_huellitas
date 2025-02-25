package org.example.demo_huellitas.service;
import org.example.demo_huellitas.entity.Empleado;
import java.util.List;

public interface EmpleadoService {
    List<Empleado> getAllEmpleados();
    Empleado getEmpleadoById(Integer id);
    Empleado addEmpleado(Empleado empleado);
    Empleado updateEmpleado(Integer id, Empleado empleado);
    void updateContrasena(Integer id, String nuevaContrasena);
    void deleteEmpleado(Integer id);
}