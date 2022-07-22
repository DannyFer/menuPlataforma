package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.TypedQuery;
import com.djimenez.menuPlataforma.modelo.dao.EmpleadoDao;
import com.djimenez.menuPlataforma.modelo.entidad.Empleado;

public class EmpleadoDaoImpl extends GenericaDaoImpl<Empleado> implements EmpleadoDao {

	@Override
	public void insertarEmpleado(Empleado nuevoEmpleado) {
		try {
			this.beginTransaction();
			this.create(nuevoEmpleado);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void actualizarEmpleado(Empleado actualizarEmpleado) {
		try {
			this.beginTransaction();
			this.update(actualizarEmpleado);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public void eliminarEmpleado(Empleado eliminarEmpleado) {
		try {
			this.beginTransaction();
			this.update(eliminarEmpleado);
			this.commit();
		} catch (Exception e) {

		}
	}

	@Override
	public List<Empleado> listarEmpleado() {
		TypedQuery<Empleado> query = this.entityManager
				.createQuery("SELECT c FROM Empleado c WHERE c.estado=true order by c.idEmpleado", Empleado.class);
		return query.getResultList();
	}

	public Empleado empleadoId(Integer idEmpleado) {
		TypedQuery<Empleado> tc = this.entityManager.createQuery(
				"SELECT c FROM Empleado c WHERE c.estadoEmpleado = true and c.idEmpleado=:idEmpleado order by c.idEmpleado",
				Empleado.class);
		tc.setParameter("idEmpleado", idEmpleado);
		return tc.getSingleResult();
	}

	@Override
	public Empleado consultaEmpleado(String cedula, String contrasenia, String admin) {
		TypedQuery<Empleado> cl = this.entityManager.createQuery(
				"Select cl from Empleado cl WHERE cl.admin='"+ admin +"' cl.cedula='" + cedula + "' and cl.contrasenia='" + contrasenia + "'",
				Empleado.class);
		return cl.getResultList().get(0);
	}
	
	@Override
	public Empleado consultaEmpleado(String cedula, String contrasenia) {
		TypedQuery<Empleado> query = this.entityManager.createQuery("Select e from Empleado e WHERE e.cedula=:cedula AND e.contrasenia=:contrasenia", Empleado.class);
		query.setParameter("cedula", cedula);
		query.setParameter("contrasenia", contrasenia);
		if (query.getResultList().size() > 0 ) {
			return query.getResultList().get(0);
		} else {
			return null;
		}
	}

}
