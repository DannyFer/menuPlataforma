package com.djimenez.menuPlataforma.controlador.impl;

import java.util.List;
import com.djimenez.menuPlataforma.controlador.PedidoControlador;
import com.djimenez.menuPlataforma.modelo.dao.PedidoDao;
import com.djimenez.menuPlataforma.modelo.dao.impl.PedidoDaoImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Pedido;

public class PedidoControladorImpl implements PedidoControlador {

	private PedidoDao pedidoDao;

	@Override
	public void insertarPedido(Pedido nuevoPedido) {
		pedidoDao = new PedidoDaoImpl();
		pedidoDao.insertarPedido(nuevoPedido);
	}

	@Override
	public void actualizarPedido(Pedido actualizarPedido) {
		pedidoDao = new PedidoDaoImpl();
		pedidoDao.actualizarPedido(actualizarPedido);
	}

	@Override
	public void eliminarPedido(Pedido eliminarPedido) {
		pedidoDao = new PedidoDaoImpl();
		pedidoDao.eliminarPedido(eliminarPedido);
	}

	@Override
	public List<Pedido> listarPedido() {
		pedidoDao = new PedidoDaoImpl();
		return pedidoDao.listarPedido();
	}
	
	@Override
	public Pedido pedidoById(Integer idPedido) {
		pedidoDao = new PedidoDaoImpl();
		return pedidoDao.pedidoById(idPedido);
	}

}
