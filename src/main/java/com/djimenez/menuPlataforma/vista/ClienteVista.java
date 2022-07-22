package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.ClienteControlador;
import com.djimenez.menuPlataforma.controlador.impl.ClienteControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Cliente;

@ManagedBean
@ViewScoped
public class ClienteVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellido;
	private String telefono;
	private String correo;
	private String contrasenia;
	private boolean estado, insertarCliente = true;
	private Cliente nuevoCliente = new Cliente();
	private Cliente actualizarCliente, eliminarCliente, limpiar;
	private List<Cliente> listarClientes;
	private ClienteControlador clienteControlador;

	public ClienteVista() {

	}

	@PostConstruct
	public void init() {
		clienteControlador = new ClienteControladorImpl();
		listarClientes = new ArrayList<Cliente>();
		listarClientes();
	}

	public void insertarCliente() {
		try {
			if (nuevoCliente.getIdCliente() == null) {
				nuevoCliente.setEstado(true);
				clienteControlador.insertarCliente(nuevoCliente);
			} else {
				clienteControlador.actualizarCliente(nuevoCliente);
			}
			listarClientes();
			limpiar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarClientes() {
		listarClientes = clienteControlador.listarCliente();
	}

	public void eliminarCliente() {
		eliminarCliente.setEstado(false);
		clienteControlador.eliminarCliente(eliminarCliente);
		listarClientes();
		limpiar();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Eliminado", ""));
	}

	public void limpiar() {
		nombre = "";
		apellido = "";
		correo = "";
		telefono = "";
		contrasenia = "";
	}
	
	public void buscarCliente() {
		Cliente clienteBase = clienteControlador.buscarPorCorreoCliente(nuevoCliente.getCorreo());
		if (clienteBase != null) {
			nuevoCliente.setNombre(clienteBase.getNombre());
			nuevoCliente.setApellido(clienteBase.getApellido());
			nuevoCliente.setTelefono(clienteBase.getTelefono());
			nuevoCliente.setContrasenia(clienteBase.getContrasenia());
			insertarCliente = false;
		} else {
			nuevoCliente.setNombre(null);
			nuevoCliente.setApellido(null);
			nuevoCliente.setTelefono(null);
			nuevoCliente.setContrasenia(null);
			insertarCliente = true;
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public boolean isInsertarCliente() {
		return insertarCliente;
	}

	public void setInsertarCliente(boolean insertarCliente) {
		this.insertarCliente = insertarCliente;
	}

	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(Cliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

	public Cliente getActualizarCliente() {
		return actualizarCliente;
	}

	public void setActualizarCliente(Cliente actualizarCliente) {
		this.actualizarCliente = actualizarCliente;
	}

	public Cliente getEliminarCliente() {
		return eliminarCliente;
	}

	public void setEliminarCliente(Cliente eliminarCliente) {
		this.eliminarCliente = eliminarCliente;
	}

	public Cliente getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Cliente limpiar) {
		this.limpiar = limpiar;
	}

	public List<Cliente> getListarClientes() {
		return listarClientes;
	}

	public void setListarClientes(List<Cliente> listarClientes) {
		this.listarClientes = listarClientes;
	}

	public ClienteControlador getClienteControlador() {
		return clienteControlador;
	}

	public void setClienteControlador(ClienteControlador clienteControlador) {
		this.clienteControlador = clienteControlador;
	}
}
