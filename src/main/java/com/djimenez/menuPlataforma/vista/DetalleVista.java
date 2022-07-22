package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.djimenez.menuPlataforma.controlador.DetalleControlador;
import com.djimenez.menuPlataforma.controlador.impl.DetalleControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Detalle;

@ManagedBean
@ViewScoped
public class DetalleVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private Integer cantidad;
	private double precio;
	private Detalle nuevoDetalle;
	private List<Detalle> listarDetalles;
	private DetalleControlador detalleControlador;

	public DetalleVista() {

	}

	@PostConstruct
	public void init() {
		detalleControlador = new DetalleControladorImpl();
		listarDetalles = new ArrayList<Detalle>();

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Detalle getNuevoDetalle() {
		return nuevoDetalle;
	}

	public void setNuevoDetalle(Detalle nuevoDetalle) {
		this.nuevoDetalle = nuevoDetalle;
	}

	public List<Detalle> getListarDetalles() {
		return listarDetalles;
	}

	public void setListarDetalles(List<Detalle> listarDetalles) {
		this.listarDetalles = listarDetalles;
	}

	public DetalleControlador getDetalleControlador() {
		return detalleControlador;
	}

	public void setDetalleControlador(DetalleControlador detalleControlador) {
		this.detalleControlador = detalleControlador;
	}
}
