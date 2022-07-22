package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.CategoriaControlador;
import com.djimenez.menuPlataforma.modelo.dao.CategoriaDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.CategoriaDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Categoria;

public class CategoriaControladorImpl implements CategoriaControlador {

	private CategoriaDao categoriaDao;

	@Override
	public void insertarCategoria(Categoria nuevoCategoria) {
		categoriaDao = new CategoriaDaoImpl();
		categoriaDao.insertarCategoria(nuevoCategoria);
	}

	@Override
	public void actualizarCategoria(Categoria actualizarCategoria) {
		categoriaDao = new CategoriaDaoImpl();
		categoriaDao.actualizarCategoria(actualizarCategoria);
	}

	@Override
	public void eliminarCategoria(Categoria eliminarCategoria) {
		categoriaDao = new CategoriaDaoImpl();
		categoriaDao.eliminarCategoria(eliminarCategoria);
	}

	@Override
	public List<Categoria> listarCategoria() {
		categoriaDao = new CategoriaDaoImpl();
		return categoriaDao.listarCategoria();
	}

}
