package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import com.djimenez.menuPlataforma.modelo.dao.DetalleDao;
import com.djimenez.menuPlataforma.modelo.entidad.Detalle;

public class DetalleDaoImpl extends GenericaDaoImpl<Detalle> implements DetalleDao {

	@Override
	public void insertarDetalle(Detalle nuevoDetalle) {
		try {
			this.beginTransaction();
			this.create(nuevoDetalle);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarCliente(Detalle actualizarDetalle) {
		try {
			this.beginTransaction();
			this.update(actualizarDetalle);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarCliente(Detalle eliminarDetalle) {
		try {
			this.beginTransaction();
			this.update(eliminarDetalle);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Detalle> listarDetalle() {
		TypedQuery<Detalle> de = this.entityManager.createQuery("Select de from de", Detalle.class);
		return de.getResultList();
	}
	
	@Override
	public List<Detalle> listarDetalleByPedido(Integer idPedido) {
		TypedQuery<Detalle> query = this.entityManager.createQuery("Select d from Detalle d WHERE d.fkPedido.idPedido=:idPedido", Detalle.class);
		query.setParameter("idPedido", idPedido);
		if (query.getResultList().size() > 0)
			return query.getResultList();
		else
			return null;
	}

}
