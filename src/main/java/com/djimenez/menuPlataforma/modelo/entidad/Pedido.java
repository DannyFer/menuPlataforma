package com.djimenez.menuPlataforma.modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	private Date fecha;
	private Double subtotal;
	private Integer iva;
	private Double total;

	// relaciones
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkCliente")
	private Cliente fkCliente;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkEmpleado")
	private Empleado fkEmpleado;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkMotorista")
	private Motorista fkMotorista;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkIngrediente")
	private Ingrediente fkIngrediente;

	@OneToMany(mappedBy = "fkPedido", cascade = CascadeType.REFRESH)
	private List<Detalle> lstdetalles = new ArrayList<Detalle>();

	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkIva")
	private Iva fkIva;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cliente getFkCliente() {
		return fkCliente;
	}

	public void setFkCliente(Cliente fkCliente) {
		this.fkCliente = fkCliente;
	}

	public Empleado getFkEmpleado() {
		return fkEmpleado;
	}

	public void setFkEmpleado(Empleado fkEmpleado) {
		this.fkEmpleado = fkEmpleado;
	}

	public Motorista getFkMotorista() {
		return fkMotorista;
	}

	public void setFkMotorista(Motorista fkMotorista) {
		this.fkMotorista = fkMotorista;
	}

	public Ingrediente getFkIngrediente() {
		return fkIngrediente;
	}

	public void setFkIngrediente(Ingrediente fkIngrediente) {
		this.fkIngrediente = fkIngrediente;
	}

	public List<Detalle> getLstdetalles() {
		return lstdetalles;
	}

	public void setLstdetalles(List<Detalle> lstdetalles) {
		this.lstdetalles = lstdetalles;
	}

	public Iva getFkIva() {
		return fkIva;
	}

	public void setFkIva(Iva fkIva) {
		this.fkIva = fkIva;
	}
}
