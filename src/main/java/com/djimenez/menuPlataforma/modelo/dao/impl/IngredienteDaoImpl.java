package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.IngredienteDao;
import com.djimenez.menuPlataforma.modelo.entidad.Ingrediente;

public class IngredienteDaoImpl extends GenericaDaoImpl<Ingrediente> implements IngredienteDao {

	@Override
	public void insertarIngrediente(Ingrediente nuevoIngrediente) {
		try {
			this.beginTransaction();
			this.create(nuevoIngrediente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarIngrediente(Ingrediente actualizarIngrediente) {
		try {
			this.beginTransaction();
			this.update(actualizarIngrediente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarIngrediente(Ingrediente eliminarIngrediente) {
		try {
			this.beginTransaction();
			this.update(eliminarIngrediente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Ingrediente> listarIngrediente() {
		TypedQuery<Ingrediente> query = this.entityManager
				.createQuery("SELECT c FROM Ingrediente c WHERE c.estado=true order by c.idIngrediente", Ingrediente.class);
		return query.getResultList();
	}

	public Ingrediente ingredienteId(Integer idIngrediente) {
		TypedQuery<Ingrediente> tc = this.entityManager.createQuery(
				"SELECT c FROM Ingrediente c WHERE c.estadoIngrediente = true and c.idIngrediente=:idIngrediente order by c.idIngrediente",
				Ingrediente.class);
		tc.setParameter("idIngrediente", idIngrediente);
		return tc.getSingleResult();
	}
}
