package com.djimenez.menuPlataforma.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Motorista implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMotorista;
	private String cedula;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private String contrasenia;
	private boolean estado;

	// relaciones
	@OneToMany(mappedBy = "fkMotorista", cascade = CascadeType.REFRESH)
	private List<Pedido> lstpedidos = new ArrayList<Pedido>();

	public Integer getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Integer idMotorista) {
		this.idMotorista = idMotorista;
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

	public List<Pedido> getLstpedidos() {
		return lstpedidos;
	}

	public void setLstpedidos(List<Pedido> lstpedidos) {
		this.lstpedidos = lstpedidos;
	}
}
