package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.ComboControlador;
import com.djimenez.menuPlataforma.modelo.dao.ComboDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.ComboDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Combo;

public class ComboControladorImpl implements ComboControlador {

	private ComboDao comboDao;

	@Override
	public void insertarCombo(Combo nuevoCombo) {
		comboDao = new ComboDaoImpl();
		comboDao.insertarCombo(nuevoCombo);
	}

	@Override
	public void actualizarCombo(Combo actualizarCombo) {
		comboDao = new ComboDaoImpl();
		comboDao.actualizarCombo(actualizarCombo);
	}

	@Override
	public void eliminarCombo(Combo eliminarCombo) {
		comboDao = new ComboDaoImpl();
		comboDao.eliminarCombo(eliminarCombo);
	}

	@Override
	public List<Combo> listarCombo() {
		comboDao = new ComboDaoImpl();
		return comboDao.listarCombo();
	}

}

