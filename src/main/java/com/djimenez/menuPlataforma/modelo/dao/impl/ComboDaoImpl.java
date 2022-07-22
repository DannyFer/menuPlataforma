package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.ComboDao;
import com.djimenez.menuPlataforma.modelo.entidad.Combo;

public class ComboDaoImpl extends GenericaDaoImpl<Combo> implements ComboDao {

	@Override
	public void insertarCombo(Combo nuevoCombo) {
		try {
			this.beginTransaction();
			this.create(nuevoCombo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarCombo(Combo actualizarCombo) {
		try {
			this.beginTransaction();
			this.update(actualizarCombo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarCombo(Combo eliminarCombo) {
		try {
			this.beginTransaction();
			this.update(eliminarCombo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Combo> listarCombo() {
		TypedQuery<Combo> query = this.entityManager
				.createQuery("SELECT c FROM Combo c WHERE c.estado=true order by c.idCombo", Combo.class);
		return query.getResultList();
	}

	public Combo comboId(Integer idCombo) {
		TypedQuery<Combo> tc = this.entityManager.createQuery(
				"SELECT c FROM Combo c WHERE c.estadoCombo = true and c.idCombo=:idCombo order by c.idCombo",
				Combo.class);
		tc.setParameter("idCombo", idCombo);
		return tc.getSingleResult();
	}
}
