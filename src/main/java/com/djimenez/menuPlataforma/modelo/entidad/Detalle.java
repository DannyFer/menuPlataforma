package com.djimenez.menuPlataforma.modelo.entidad;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Detalle implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalle;
	private String nombre;
	private Integer cantidad;
	private double precio;

	// relaciones
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkPedido")
	private Pedido fkPedido;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkCombo")
	private Combo fkCombo;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkPlatillo")
	private Platillo fkPlatillo;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "fkIngrediente")
	private Ingrediente fkIngrediente;

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
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

	public Pedido getFkPedido() {
		return fkPedido;
	}

	public void setFkPedido(Pedido fkPedido) {
		this.fkPedido = fkPedido;
	}

	public Combo getFkCombo() {
		return fkCombo;
	}

	public void setFkCombo(Combo fkCombo) {
		this.fkCombo = fkCombo;
	}

	public Platillo getFkPlatillo() {
		return fkPlatillo;
	}

	public void setFkPlatillo(Platillo fkPlatillo) {
		this.fkPlatillo = fkPlatillo;
	}

	public Ingrediente getFkIngrediente() {
		return fkIngrediente;
	}

	public void setFkIngrediente(Ingrediente fkIngrediente) {
		this.fkIngrediente = fkIngrediente;
	}
}
