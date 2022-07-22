package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.DetalleControlador;
import com.djimenez.menuPlataforma.modelo.dao.DetalleDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.DetalleDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Detalle;

public class DetalleControladorImpl implements DetalleControlador {

	private DetalleDao detalleDao;

	@Override
	public void insertarDetalle(Detalle nuevoDetalle) {
		detalleDao = new DetalleDaoImpl();
		detalleDao.insertarDetalle(nuevoDetalle);

	}

	@Override
	public void actualizarDetalle(Detalle actualizarDetalle) {
		detalleDao = new DetalleDaoImpl();
		detalleDao.actualizarCliente(actualizarDetalle);

	}

	@Override
	public void eliminarDetalle(Detalle eliminarDetalle) {
		detalleDao = new DetalleDaoImpl();
		detalleDao.eliminarCliente(eliminarDetalle);

	}

	@Override
	public List<Detalle> listarDetalle() {
		detalleDao = new DetalleDaoImpl();
		return detalleDao.listarDetalle();
	}
	
	@Override
	public List<Detalle> listarDetalleByPedido(Integer idPedido) {
		detalleDao = new DetalleDaoImpl();
		return detalleDao.listarDetalleByPedido(idPedido);
	}

}
