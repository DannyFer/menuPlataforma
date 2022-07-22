package com.djimenez.menuPlataforma.controlador;

import com.djimenez.menuPlataforma.modelo.entidad.Iva;

public interface IvaControlador {

	public void insertarIva(Iva nuevoIva);

	public void actualizarIva(Iva actualizarIva);

	public void eliminarIva(Iva eliminarIva);
	
	public Iva obtenerIva();
}
