package com.djimenez.menuPlataforma.modelo.dao;

import com.djimenez.menuPlataforma.modelo.entidad.Iva;

public interface IvaDao {

	public void insertarIva(Iva nuevoIva);

	public void actualizarIva(Iva actualizarIva);

	public void eliminarIva(Iva eliminarIva);
	
	public Iva obtenerIva();
}
