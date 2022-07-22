package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.IngredienteControlador;
import com.djimenez.menuPlataforma.modelo.dao.IngredienteDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.IngredienteDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Ingrediente;

public class IngredienteControladorImpl implements IngredienteControlador {

	private IngredienteDao ingredienteDao;

	@Override
	public void insertarIngrediente(Ingrediente nuevoIngrediente) {
		ingredienteDao = new IngredienteDaoImpl();
		ingredienteDao.insertarIngrediente(nuevoIngrediente);
	}

	@Override
	public void actualizarIngrediente(Ingrediente actualizarIngrediente) {
		ingredienteDao = new IngredienteDaoImpl();
		ingredienteDao.actualizarIngrediente(actualizarIngrediente);
	}

	@Override
	public void eliminarIngrediente(Ingrediente eliminarIngrediente) {
		ingredienteDao = new IngredienteDaoImpl();
		ingredienteDao.eliminarIngrediente(eliminarIngrediente);
	}

	@Override
	public List<Ingrediente> listarIngrediente() {
		ingredienteDao = new IngredienteDaoImpl();
		return ingredienteDao.listarIngrediente();
	}

}
