package com.djimenez.menuPlataforma.controlador;

import java.util.List;

import com.djimenez.menuPlataforma.modelo.entidad.Pedido;

public interface PedidoControlador {

	public void insertarPedido(Pedido nuevoPedido);

	public void actualizarPedido(Pedido actualizarPedido);

	public void eliminarPedido(Pedido eliminarPedido);
	
	public Pedido pedidoById(Integer idPedido);

	public List<Pedido> listarPedido();

}
