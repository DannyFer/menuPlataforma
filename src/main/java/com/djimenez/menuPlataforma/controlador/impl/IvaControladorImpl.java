package com.djimenez.menuPlataforma.controlador.impl;

import com.djimenez.menuPlataforma.controlador.IvaControlador;
import com.djimenez.menuPlataforma.modelo.dao.IvaDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.IvaDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Iva;

public class IvaControladorImpl implements IvaControlador {

	private IvaDao ivaDao;

	@Override
	public void insertarIva(Iva nuevoIva) {
		ivaDao = new IvaDaoImpl();
		ivaDao.insertarIva(nuevoIva);
	}

	@Override
	public void actualizarIva(Iva actualizarIva) {
		ivaDao = new IvaDaoImpl();
		ivaDao.actualizarIva(actualizarIva);
	}

	@Override
	public void eliminarIva(Iva eliminarIva) {
		ivaDao = new IvaDaoImpl();
		ivaDao.eliminarIva(eliminarIva);
	}

	@Override
	public Iva obtenerIva() {
		ivaDao = new IvaDaoImpl();
		return ivaDao.obtenerIva();
	}
}
