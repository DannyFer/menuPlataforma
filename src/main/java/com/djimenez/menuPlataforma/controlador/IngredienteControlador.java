package com.djimenez.menuPlataforma.controlador;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Ingrediente;

public interface IngredienteControlador {

	public void insertarIngrediente(Ingrediente nuevoIngrediente);

	public void actualizarIngrediente(Ingrediente actualizarIngrediente);

	public void eliminarIngrediente(Ingrediente eliminarIngrediente);

	public List<Ingrediente> listarIngrediente();

}
