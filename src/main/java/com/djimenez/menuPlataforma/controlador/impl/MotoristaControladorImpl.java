package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.MotoristaControlador;
import com.djimenez.menuPlataforma.modelo.dao.MotoristaDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.MotoristaDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Motorista;

public class MotoristaControladorImpl implements MotoristaControlador {

	private MotoristaDao motoristaDao;

	@Override
	public void insertarMotorista(Motorista nuevoMotorista) {
		motoristaDao = new MotoristaDaoImpl();
		motoristaDao.insertarMotorista(nuevoMotorista);
	}

	@Override
	public void actualizarMotorista(Motorista actualizarMotorista) {
		motoristaDao = new MotoristaDaoImpl();
		motoristaDao.actualizarMotorista(actualizarMotorista);
	}

	@Override
	public void eliminarMotorista(Motorista eliminarMotorista) {
		motoristaDao = new MotoristaDaoImpl();
		motoristaDao.eliminarMotorista(eliminarMotorista);
	}

	@Override
	public List<Motorista> listarMotorista() {
		motoristaDao = new MotoristaDaoImpl();
		return motoristaDao.listarMotorista();
	}

}
