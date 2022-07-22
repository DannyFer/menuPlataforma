package com.djimenez.menuPlataforma.modelo.dao.impl;

import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.IvaDao;
import com.djimenez.menuPlataforma.modelo.entidad.Iva;

public class IvaDaoImpl extends GenericaDaoImpl<Iva> implements IvaDao {

	@Override
	public void insertarIva(Iva nuevoIva) {
		try {
			this.beginTransaction();
			this.create(nuevoIva);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarIva(Iva actualizarIva) {
		try {
			this.beginTransaction();
			this.update(actualizarIva);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarIva(Iva eliminarIva) {
		try {
			this.beginTransaction();
			this.update(eliminarIva);
			this.commit();
		} catch (Exception e) {

		}
	}

	public Iva ivaId(Integer idIva) {
		TypedQuery<Iva> tc = this.entityManager
				.createQuery("SELECT c FROM Iva c WHERE c.idIva=:idIva order by c.fecha desc", Iva.class);
		tc.setParameter("idIva", idIva);
		return tc.getSingleResult();
	}

	public Iva obtenerIva() {
		TypedQuery<Iva> tc = this.entityManager.createQuery("SELECT i FROM Iva i order by i.fecha desc", Iva.class);
		if (tc.getResultList().size() > 0)
			return (Iva) tc.getResultList().get(0);
		else
			return null;
	}

}
