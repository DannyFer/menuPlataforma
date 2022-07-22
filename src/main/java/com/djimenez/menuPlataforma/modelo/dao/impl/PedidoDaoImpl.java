package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.PedidoDao;
import com.djimenez.menuPlataforma.modelo.entidad.Iva;
import com.djimenez.menuPlataforma.modelo.entidad.Pedido;

public class PedidoDaoImpl extends GenericaDaoImpl<Pedido> implements PedidoDao {

	@Override
	public void insertarPedido(Pedido nuevoPedido) {
		try {
			this.beginTransaction();
			this.create(nuevoPedido);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarPedido(Pedido actualizarPedido) {
		try {
			this.beginTransaction();
			this.update(actualizarPedido);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarPedido(Pedido eliminarPedido) {
		try {
			this.beginTransaction();
			this.update(eliminarPedido);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Pedido> listarPedido() {
		TypedQuery<Pedido> query = this.entityManager.createQuery("SELECT c FROM Pedido c ", Pedido.class);
		return query.getResultList();
	}

	public Pedido pedidoById(Integer idPedido) {
		TypedQuery<Pedido> tc = this.entityManager.createQuery("SELECT c FROM Pedido c WHERE c.idPedido=:idPedido", Pedido.class);
		tc.setParameter("idPedido", idPedido);
		if (tc.getResultList().size() > 0)
			return (Pedido) tc.getResultList().get(0);
		else
			return null;
	}

	public Iva obtenerIva() {
		TypedQuery<Iva> tc = this.entityManager.createQuery("SELECT i FROM Iva i order by i.fecha desc", Iva.class);
		if (tc.getResultList().size() > 0)
			return (Iva) tc.getResultList().get(0);
		else
			return null;
	}
}
