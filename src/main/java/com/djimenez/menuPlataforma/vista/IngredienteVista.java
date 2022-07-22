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
import com.djimenez.menuPlataforma.controlador.IngredienteControlador;
import com.djimenez.menuPlataforma.controlador.impl.CategoriaControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.IngredienteControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Categoria;
import com.djimenez.menuPlataforma.modelo.entidad.Ingrediente;

@ManagedBean
@ViewScoped
public class IngredienteVista implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private double precio;
	private String grafico;
	private boolean estado;
	private Ingrediente nuevoIngrediente = new Ingrediente();
	private Ingrediente actualizarIngrediente, eliminarIngrediente, limpiar;
	private List<Ingrediente> listarIngredientes;
	private List<Categoria> listarCategorias;
	private IngredienteControlador ingredienteControlador;
	private CategoriaControlador categoriaControlador;
	private int categoriaseleccionada;

	public IngredienteVista() {

	}

	@PostConstruct
	public void init() {
		ingredienteControlador = new IngredienteControladorImpl();
		listarIngredientes = new ArrayList<Ingrediente>();
		categoriaControlador = new CategoriaControladorImpl();
		listarCategorias = new ArrayList<Categoria>();
		listarIngredientes();
		listarCategorias();
		limpiar();
	}

	public void insertarIngrediente() {
		try {
			if (nuevoIngrediente.getIdIngrediente() == null) {
				nuevoIngrediente.setEstado(true);
				Categoria select = new Categoria();
				select.setIdCategoria(categoriaseleccionada);
				nuevoIngrediente.setFkCategoria(select);
				ingredienteControlador.insertarIngrediente(nuevoIngrediente);
			} else {
				Categoria select = new Categoria();
				select.setIdCategoria(categoriaseleccionada);
				nuevoIngrediente.setFkCategoria(select);
				ingredienteControlador.actualizarIngrediente(nuevoIngrediente);
			}
			listarIngredientes();
			limpiar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrediente Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarIngredientes() {
		listarIngredientes = ingredienteControlador.listarIngrediente();
		limpiar();
	}

	public void listarCategorias() {
		listarCategorias = categoriaControlador.listarCategoria();
	}

	public void eliminarIngrediente() {
		eliminarIngrediente.setEstado(false);
		ingredienteControlador.eliminarIngrediente(eliminarIngrediente);
		listarIngredientes();
		limpiar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingrediente Eliminado", ""));
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

	public Ingrediente getNuevoIngrediente() {
		return nuevoIngrediente;
	}

	public void setNuevoIngrediente(Ingrediente nuevoIngrediente) {
		this.nuevoIngrediente = nuevoIngrediente;
	}

	public Ingrediente getActualizarIngrediente() {
		return actualizarIngrediente;
	}

	public void setActualizarIngrediente(Ingrediente actualizarIngrediente) {
		this.actualizarIngrediente = actualizarIngrediente;
	}

	public Ingrediente getEliminarIngrediente() {
		return eliminarIngrediente;
	}

	public void setEliminarIngrediente(Ingrediente eliminarIngrediente) {
		this.eliminarIngrediente = eliminarIngrediente;
	}

	public Ingrediente getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Ingrediente limpiar) {
		this.limpiar = limpiar;
	}

	public List<Ingrediente> getListarIngredientes() {
		return listarIngredientes;
	}

	public void setListarIngredientes(List<Ingrediente> listarIngredientes) {
		this.listarIngredientes = listarIngredientes;
	}

	public List<Categoria> getListarCategorias() {
		return listarCategorias;
	}

	public void setListarCategorias(List<Categoria> listarCategorias) {
		this.listarCategorias = listarCategorias;
	}

	public IngredienteControlador getIngredienteControlador() {
		return ingredienteControlador;
	}

	public void setIngredienteControlador(IngredienteControlador ingredienteControlador) {
		this.ingredienteControlador = ingredienteControlador;
	}

	public CategoriaControlador getCategoriaControlador() {
		return categoriaControlador;
	}

	public void setCategoriaControlador(CategoriaControlador categoriaControlador) {
		this.categoriaControlador = categoriaControlador;
	}

	public int getCategoriaseleccionada() {
		return categoriaseleccionada;
	}

	public void setCategoriaseleccionada(int categoriaseleccionada) {
		this.categoriaseleccionada = categoriaseleccionada;
	}
}
