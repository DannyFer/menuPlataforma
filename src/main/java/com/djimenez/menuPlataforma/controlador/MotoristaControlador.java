package com.djimenez.menuPlataforma.controlador;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Motorista;

public interface MotoristaControlador {

	public void insertarMotorista(Motorista nuevoMotorista);

	public void actualizarMotorista(Motorista actualizarMotorista);

	public void eliminarMotorista(Motorista eliminarMotorista);

	public List<Motorista> listarMotorista();

}
