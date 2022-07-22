package com.djimenez.menuPlataforma.modelo.dao;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Ingrediente;

public interface IngredienteDao {

	public void insertarIngrediente(Ingrediente nuevoIngrediente);

	public void actualizarIngrediente(Ingrediente actualizarIngrediente);

	public void eliminarIngrediente(Ingrediente eliminarIngrediente);

	public List<Ingrediente> listarIngrediente();
}
