package com.djimenez.menuPlataforma.vista;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.djimenez.menuPlataforma.controlador.EmpleadoControlador;
import com.djimenez.menuPlataforma.controlador.impl.EmpleadoControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Empleado;

@ManagedBean
@ViewScoped
public class EmpleadoVista implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cedula;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private String contrasenia;
	private boolean estado;
	private boolean admin;

	private Empleado nuevoEmpleado = new Empleado();
	private Empleado actualizarEmpleado, eliminarEmpleado;
	private List<Empleado> listarEmpleados;
	private EmpleadoControlador empleadoControlador;

	public EmpleadoVista() {

	}

	@PostConstruct
	public void init() {
		empleadoControlador = new EmpleadoControladorImpl();
		listarEmpleados = new ArrayList<Empleado>();
		listarEmpleados();
		limpiarDatos();

	}

	public void insertarEmpleado() {
		try {
			if (nuevoEmpleado.getIdEmpleado() == null) {
				nuevoEmpleado.setEstado(true);
				nuevoEmpleado.setContrasenia(claveEncriptadaSHA1(contrasenia));
				empleadoControlador.insertarEmpleado(nuevoEmpleado);
				limpiarDatos();
			} else {
				if (contrasenia != null) {
					nuevoEmpleado.setContrasenia(claveEncriptadaSHA1(contrasenia));
				}
				empleadoControlador.actualizarEmpleado(nuevoEmpleado);
			}
			listarEmpleados();
			limpiarDatos();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Registrado", ""));
		} catch (Exception e) {
			limpiarDatos();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));

		}

	}

	public void listarEmpleados() {
		listarEmpleados = empleadoControlador.listarEmpleado();
	}

	public void eliminarEmpleado() {
		eliminarEmpleado.setEstado(false);
		empleadoControlador.eliminarEmpleado(eliminarEmpleado);
		listarEmpleados();
		limpiarDatos();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado Eliminado", ""));
	}

	public void login() {
		FacesMessage message = null;
		boolean loggedIn = false;

		if (cedula != null && contrasenia != null) {
			try {
				String contraseniaSHA1 = claveEncriptadaSHA1(contrasenia);
				Empleado empleadoBase = empleadoControlador.consultaEmpleado(cedula, contraseniaSHA1);
				if (empleadoBase != null) {
					loggedIn = true;
					message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", cedula);
					String url = "";
					if (empleadoBase.isAdmin()) {
						url = "/combo.xhtml";
					} else {
						url = "/pedido.xhtml";
					}
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
					externalContext.redirect(externalContext.getRequestContextPath() + url);
				} else {
					message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario incorrecto",
							"Invalid credentials");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales incorrectas", "Invalid credentials");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
	}

	public void limpiarDatos() {
		cedula = "";
		nombre = "";
		apellido = "";
		correo = "";
		telefono = "";
		contrasenia = "";
		nuevoEmpleado = new Empleado();
	}
	
	public static String claveEncriptadaSHA1(String password) {
		try {
			byte[] buffer = password.getBytes();
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(buffer);
			String valorHash = "";
			for (byte aux : md.digest()) {
				int b = aux & 0xff;
				if (Integer.toHexString(b).length() == 1) {
					valorHash += "0";
				}
				valorHash += Integer.toHexString(b);
			}
			return valorHash;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Empleado getNuevoEmpleado() {
		return nuevoEmpleado;
	}

	public void setNuevoEmpleado(Empleado nuevoEmpleado) {
		this.nuevoEmpleado = nuevoEmpleado;
	}

	public Empleado getActualizarEmpleado() {
		return actualizarEmpleado;
	}

	public void setActualizarEmpleado(Empleado actualizarEmpleado) {
		this.actualizarEmpleado = actualizarEmpleado;
	}

	public Empleado getEliminarEmpleado() {
		return eliminarEmpleado;
	}

	public void setEliminarEmpleado(Empleado eliminarEmpleado) {
		this.eliminarEmpleado = eliminarEmpleado;
	}

	public List<Empleado> getListarEmpleados() {
		return listarEmpleados;
	}

	public void setListarEmpleados(List<Empleado> listarEmpleados) {
		this.listarEmpleados = listarEmpleados;
	}

	public EmpleadoControlador getEmpleadoControlador() {
		return empleadoControlador;
	}

	public void setEmpleadoControlador(EmpleadoControlador empleadoControlador) {
		this.empleadoControlador = empleadoControlador;
	}
}
