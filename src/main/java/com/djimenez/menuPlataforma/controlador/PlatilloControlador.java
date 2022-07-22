package com.djimenez.menuPlataforma.controlador;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Platillo;

public interface PlatilloControlador {

	public void insertarPlatillo(Platillo nuevoPlatillo);

	public void actualizarPlatillo(Platillo actualizarPlatillo);

	public void eliminarPlatillo(Platillo eliminarPlatillo);

	public List<Platillo> listarPlatillo();

}
