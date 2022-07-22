package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.ComboControlador;
import com.djimenez.menuPlataforma.controlador.impl.ComboControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Combo;

@ManagedBean
@ViewScoped
public class ComboVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private double precio;
	private String grafico;
	private boolean estado;
	private Combo nuevoCombo = new Combo();
	private Combo actualizarCombo, eliminarCombo, limpiar;
	private List<Combo> listarCombos;
	private ComboControlador comboControlador;

	public ComboVista() {

	}

	@PostConstruct
	public void init() {
		comboControlador = new ComboControladorImpl();
		listarCombos = new ArrayList<Combo>();
		listarCombos();
	}

	public void insertarCombo() {
		try {
			if (nuevoCombo.getIdCombo() == null) {
				nuevoCombo.setEstado(true);
				comboControlador.insertarCombo(nuevoCombo);
			} else {
				comboControlador.actualizarCombo(nuevoCombo);
			}
			listarCombos();
			limpiar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Combo Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarCombos() {
		listarCombos = comboControlador.listarCombo();
	}

	public void eliminarCombo() {
		eliminarCombo.setEstado(false);
		comboControlador.eliminarCombo(eliminarCombo);
		listarCombos();
		limpiar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Combo Eliminado", ""));
	}

	public void limpiar() {
		nombre = "";
		descripcion = "";
		precio = 0.00;
		nuevoCombo = new Combo();
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

	public Combo getNuevoCombo() {
		return nuevoCombo;
	}

	public void setNuevoCombo(Combo nuevoCombo) {
		this.nuevoCombo = nuevoCombo;
	}

	public Combo getActualizarCombo() {
		return actualizarCombo;
	}

	public void setActualizarCombo(Combo actualizarCombo) {
		this.actualizarCombo = actualizarCombo;
	}

	public Combo getEliminarCombo() {
		return eliminarCombo;
	}

	public void setEliminarCombo(Combo eliminarCombo) {
		this.eliminarCombo = eliminarCombo;
	}

	public Combo getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Combo limpiar) {
		this.limpiar = limpiar;
	}

	public List<Combo> getListarCombos() {
		return listarCombos;
	}

	public void setListarCombos(List<Combo> listarCombos) {
		this.listarCombos = listarCombos;
	}

	public ComboControlador getComboControlador() {
		return comboControlador;
	}

	public void setComboControlador(ComboControlador comboControlador) {
		this.comboControlador = comboControlador;
	}
}
