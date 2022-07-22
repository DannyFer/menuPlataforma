package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.PlatilloControlador;
import com.djimenez.menuPlataforma.controlador.impl.PlatilloControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Platillo;

@ManagedBean
@ViewScoped
public class PlatilloVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private double precio;
	private String grafico;
	private boolean estado;
	private Platillo nuevoPlatillo = new Platillo();
	private Platillo actualizarPlatillo, eliminarPlatillo, limpiar;
	private List<Platillo> listarPlatillos;
	private PlatilloControlador platilloControlador;

	public PlatilloVista() {

	}

	@PostConstruct
	public void init() {
		platilloControlador = new PlatilloControladorImpl();
		listarPlatillos = new ArrayList<Platillo>();
		listarPlatillos();
	}

	public void insertarPlatillo() {
		try {
			if (nuevoPlatillo.getIdPlatillo() == null) {
				nuevoPlatillo.setEstado(true);
				platilloControlador.insertarPlatillo(nuevoPlatillo);
			} else {
				platilloControlador.actualizarPlatillo(nuevoPlatillo);
			}
			listarPlatillos();
			limpiar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Platillo Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarPlatillos() {
		listarPlatillos = platilloControlador.listarPlatillo();
	}

	public void eliminarPlatillo() {
		eliminarPlatillo.setEstado(false);
		platilloControlador.eliminarPlatillo(eliminarPlatillo);
		listarPlatillos();
		limpiar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Platillo Eliminado", ""));
	}

	public void limpiar() {
		nombre = "";
		descripcion = "";
		precio = 0.00;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getGrafico() {
		return grafico;
	}

	public void setGrafico(String grafico) {
		this.grafico = grafico;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Platillo getNuevoPlatillo() {
		return nuevoPlatillo;
	}

	public void setNuevoPlatillo(Platillo nuevoPlatillo) {
		this.nuevoPlatillo = nuevoPlatillo;
	}

	public Platillo getActualizarPlatillo() {
		return actualizarPlatillo;
	}

	public void setActualizarPlatillo(Platillo actualizarPlatillo) {
		this.actualizarPlatillo = actualizarPlatillo;
	}

	public Platillo getEliminarPlatillo() {
		return eliminarPlatillo;
	}

	public void setEliminarPlatillo(Platillo eliminarPlatillo) {
		this.eliminarPlatillo = eliminarPlatillo;
	}

	public Platillo getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Platillo limpiar) {
		this.limpiar = limpiar;
	}

	public List<Platillo> getListarPlatillos() {
		return listarPlatillos;
	}

	public void setListarPlatillos(List<Platillo> listarPlatillos) {
		this.listarPlatillos = listarPlatillos;
	}

	public PlatilloControlador getPlatilloControlador() {
		return platilloControlador;
	}

	public void setPlatilloControlador(PlatilloControlador platilloControlador) {
		this.platilloControlador = platilloControlador;
	}
}
