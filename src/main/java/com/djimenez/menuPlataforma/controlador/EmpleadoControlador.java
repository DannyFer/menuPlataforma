package com.djimenez.menuPlataforma.controlador;

import java.util.List;
import com.djimenez.menuPlataforma.modelo.entidad.Empleado;

public interface EmpleadoControlador {

	public void insertarEmpleado(Empleado nuevoEmpleado);

	public void actualizarEmpleado(Empleado actualizarEmpleado);

	public void eliminarEmpleado(Empleado eliminarEmpleado);

	public List<Empleado> listarEmpleado();
	
	public Empleado consultaEmpleado(String cedula, String contrasenia);

	public Empleado consultaEmpleado(String cedula, String contrasenia, String admin);

}