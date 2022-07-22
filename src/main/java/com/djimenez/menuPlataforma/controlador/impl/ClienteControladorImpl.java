package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;

import com.djimenez.menuPlataforma.controlador.ClienteControlador;
import com.djimenez.menuPlataforma.modelo.dao.ClienteDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.ClienteDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Cliente;

public class ClienteControladorImpl implements ClienteControlador {

	private ClienteDao clienteDao;

	@Override
	public void insertarCliente(Cliente nuevoCliente) {
		clienteDao = new ClienteDaoImpl();
		clienteDao.insertarCliente(nuevoCliente);
	}

	@Override
	public void actualizarCliente(Cliente actualizarCliente) {
		clienteDao = new ClienteDaoImpl();
		clienteDao.actualizarCliente(actualizarCliente);
	}

	@Override
	public void eliminarCliente(Cliente eliminarCliente) {
		clienteDao = new ClienteDaoImpl();
		clienteDao.eliminarCliente(eliminarCliente);
	}

	@Override
	public List<Cliente> listarCliente() {
		clienteDao = new ClienteDaoImpl();
		return clienteDao.listarCliente();
	}
	
	public Cliente buscarPorCorreoCliente(String correo) {
		clienteDao = new ClienteDaoImpl();
		return clienteDao.buscarPorCorreoCliente(correo);
	}
	
}
