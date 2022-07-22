package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.ClienteDao;
import com.djimenez.menuPlataforma.modelo.entidad.Cliente;

public class ClienteDaoImpl extends GenericaDaoImpl<Cliente> implements ClienteDao {

	@Override
	public void insertarCliente(Cliente nuevoCliente) {
		try {
			this.beginTransaction();
			this.create(nuevoCliente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarCliente(Cliente actualizarCliente) {
		try {
			this.beginTransaction();
			this.update(actualizarCliente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarCliente(Cliente eliminarCliente) {
		try {
			this.beginTransaction();
			this.update(eliminarCliente);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Cliente> listarCliente() {
		TypedQuery<Cliente> query = this.entityManager
				.createQuery("SELECT c FROM Cliente c WHERE c.estado=true order by c.idCliente", Cliente.class);
		return query.getResultList();
	}

	public Cliente clienteId(Integer idCliente) {
		TypedQuery<Cliente> tc = this.entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.estadoCliente = true and c.idCliente=:idCliente order by c.idCliente",
				Cliente.class);
		tc.setParameter("idCliente", idCliente);
		return tc.getSingleResult();
	}
	
	public Cliente buscarPorCorreoCliente(String correo) {
		TypedQuery<Cliente> tc = this.entityManager.createQuery(
				"SELECT c FROM Cliente c WHERE c.estado = true and c.correo=:correo order by c.idCliente",
				Cliente.class);
		tc.setParameter("correo", correo);
		if (tc.getResultList().size() > 0) {
			return tc.getSingleResult();
		} else {
			return null;
		}
	}

}
