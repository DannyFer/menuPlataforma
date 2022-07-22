package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;

import com.djimenez.menuPlataforma.controlador.PlatilloControlador;
import com.djimenez.menuPlataforma.modelo.dao.PlatilloDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.PlatilloDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Platillo;

public class PlatilloControladorImpl implements PlatilloControlador {

	private PlatilloDao platilloDao;

	@Override
	public void insertarPlatillo(Platillo nuevoPlatillo) {
		platilloDao = new PlatilloDaoImpl();
		platilloDao.insertarPlatillo(nuevoPlatillo);

	}

	@Override
	public void actualizarPlatillo(Platillo actualizarPlatillo) {
		platilloDao = new PlatilloDaoImpl();
		platilloDao.actualizarPlatillo(actualizarPlatillo);

	}

	@Override
	public void eliminarPlatillo(Platillo eliminarPlatillo) {
		platilloDao = new PlatilloDaoImpl();
		platilloDao.eliminarPlatillo(eliminarPlatillo);

	}

	@Override
	public List<Platillo> listarPlatillo() {
		platilloDao = new PlatilloDaoImpl();
		return platilloDao.listarPlatillo();
	}

}

