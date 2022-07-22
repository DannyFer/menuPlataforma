package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.PlatilloDao;
import com.djimenez.menuPlataforma.modelo.entidad.Platillo;

public class PlatilloDaoImpl extends GenericaDaoImpl<Platillo> implements PlatilloDao {

	@Override
	public void insertarPlatillo(Platillo nuevoPlatillo) {
		try {
			this.beginTransaction();
			this.create(nuevoPlatillo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarPlatillo(Platillo actualizarPlatillo) {
		try {
			this.beginTransaction();
			this.update(actualizarPlatillo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarPlatillo(Platillo eliminarPlatillo) {
		try {
			this.beginTransaction();
			this.update(eliminarPlatillo);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Platillo> listarPlatillo() {
		TypedQuery<Platillo> query = this.entityManager
				.createQuery("SELECT c FROM Platillo c WHERE c.estado=true order by c.idPlatillo", Platillo.class);
		return query.getResultList();
	}

	public Platillo platilloId(Integer idPlatillo) {
		TypedQuery<Platillo> tc = this.entityManager.createQuery(
				"SELECT c FROM Platillo c WHERE c.estadoPlatillo = true and c.idPlatillo=:idPlatillo order by c.idPlatillo",
				Platillo.class);
		tc.setParameter("idPlatillo", idPlatillo);
		return tc.getSingleResult();
	}
}
