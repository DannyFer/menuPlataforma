package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.CategoriaControlador;
import com.djimenez.menuPlataforma.controlador.impl.CategoriaControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Categoria;

@ManagedBean
@ViewScoped
public class CategoriaVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private boolean estado;
	private Categoria nuevoCategoria = new Categoria();
	private Categoria actualizarCategoria, eliminarCategoria, limpiar;
	private List<Categoria> listarCategorias;
	private CategoriaControlador categoriaControlador;

	public CategoriaVista() {

	}

	@PostConstruct
	public void init() {
		categoriaControlador = new CategoriaControladorImpl();
		listarCategorias = new ArrayList<Categoria>();
		listarCategorias();
	}

	public void insertarCategoria() {
		try {
			if (nuevoCategoria.getIdCategoria() == null) {
				nuevoCategoria.setEstado(true);
				categoriaControlador.insertarCategoria(nuevoCategoria);
			} else {
				categoriaControlador.actualizarCategoria(nuevoCategoria);
			}
			listarCategorias();
			limpiar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarCategorias() {
		listarCategorias = categoriaControlador.listarCategoria();
	}

	public void eliminarCategoria() {
		eliminarCategoria.setEstado(false);
		categoriaControlador.eliminarCategoria(eliminarCategoria);
		listarCategorias();
		limpiar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Categoria Eliminado", ""));
	}

	public void limpiar() {
		nombre = "";
		descripcion = "";
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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Categoria getNuevoCategoria() {
		return nuevoCategoria;
	}

	public void setNuevoCategoria(Categoria nuevoCategoria) {
		this.nuevoCategoria = nuevoCategoria;
	}

	public Categoria getActualizarCategoria() {
		return actualizarCategoria;
	}

	public void setActualizarCategoria(Categoria actualizarCategoria) {
		this.actualizarCategoria = actualizarCategoria;
	}

	public Categoria getEliminarCategoria() {
		return eliminarCategoria;
	}

	public void setEliminarCategoria(Categoria eliminarCategoria) {
		this.eliminarCategoria = eliminarCategoria;
	}

	public Categoria getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Categoria limpiar) {
		this.limpiar = limpiar;
	}

	public List<Categoria> getListarCategorias() {
		return listarCategorias;
	}

	public void setListarCategorias(List<Categoria> listarCategorias) {
		this.listarCategorias = listarCategorias;
	}

	public CategoriaControlador getCategoriaControlador() {
		return categoriaControlador;
	}

	public void setCategoriaControlador(CategoriaControlador categoriaControlador) {
		this.categoriaControlador = categoriaControlador;
	}
}
