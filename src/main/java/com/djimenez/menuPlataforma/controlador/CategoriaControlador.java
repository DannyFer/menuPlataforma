package com.djimenez.menuPlataforma.controlador;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Categoria;

public interface CategoriaControlador {

	public void insertarCategoria(Categoria nuevoCategoria);

	public void actualizarCategoria(Categoria actualizarCategoria);

	public void eliminarCategoria(Categoria eliminarCategoria);

	public List<Categoria> listarCategoria();

}