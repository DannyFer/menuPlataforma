package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.CategoriaDao;
import com.djimenez.menuPlataforma.modelo.entidad.Categoria;

public class CategoriaDaoImpl extends GenericaDaoImpl<Categoria> implements CategoriaDao {

	@Override
	public void insertarCategoria(Categoria nuevoCategoria) {
		try {
			this.beginTransaction();
			this.create(nuevoCategoria);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarCategoria(Categoria actualizarCategoria) {
		try {
			this.beginTransaction();
			this.update(actualizarCategoria);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarCategoria(Categoria eliminarCategoria) {
		try {
			this.beginTransaction();
			this.update(eliminarCategoria);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Categoria> listarCategoria() {
		TypedQuery<Categoria> query = this.entityManager
				.createQuery("SELECT c FROM Categoria c WHERE c.estado=true order by c.idCategoria", Categoria.class);
		return query.getResultList();
	}

	public Categoria categoriaId(Integer idCategoria) {
		TypedQuery<Categoria> tc = this.entityManager.createQuery(
				"SELECT c FROM Categoria c WHERE c.estadoCategoria = true and c.idCategoria=:idCategoria order by c.idCategoria",
				Categoria.class);
		tc.setParameter("idCategoria", idCategoria);
		return tc.getSingleResult();
	}
}
