package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.MotoristaDao;
import com.djimenez.menuPlataforma.modelo.entidad.Motorista;

public class MotoristaDaoImpl extends GenericaDaoImpl<Motorista> implements MotoristaDao {

	@Override
	public void insertarMotorista(Motorista nuevoMotorista) {
		try {
			this.beginTransaction();
			this.create(nuevoMotorista);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarMotorista(Motorista actualizarMotorista) {
		try {
			this.beginTransaction();
			this.update(actualizarMotorista);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarMotorista(Motorista eliminarMotorista) {
		try {
			this.beginTransaction();
			this.update(eliminarMotorista);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Motorista> listarMotorista() {
		TypedQuery<Motorista> query = this.entityManager
				.createQuery("SELECT c FROM Motorista c WHERE c.estado=true order by c.idMotorista", Motorista.class);
		return query.getResultList();
	}

	public Motorista motoristaId(Integer idMotorista) {
		TypedQuery<Motorista> tc = this.entityManager.createQuery(
				"SELECT c FROM Motorista c WHERE c.estadoMotorista = true and c.idMotorista=:idMotorista order by c.idMotorista",
				Motorista.class);
		tc.setParameter("idMotorista", idMotorista);
		return tc.getSingleResult();
	}
}
