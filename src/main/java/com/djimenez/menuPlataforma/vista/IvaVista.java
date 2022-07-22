package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.IvaControlador;
import com.djimenez.menuPlataforma.controlador.impl.IvaControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Iva;

@ManagedBean
@ViewScoped
public class IvaVista implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer iva;
	private Date fecha;
	private Iva nuevoIva = new Iva();
	private IvaControlador ivaControlador;

	public IvaVista() {

	}

	@PostConstruct
	public void init() {
		ivaControlador = new IvaControladorImpl();
	}

	public void insertarIva() {
		try {
			if (nuevoIva.getIdIva() == null) {
				nuevoIva.setFecha(new Date());
				ivaControlador.insertarIva(nuevoIva);
			} else {
				ivaControlador.actualizarIva(nuevoIva);
			}
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Iva Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Iva getNuevoIva() {
		return nuevoIva;
	}

	public void setNuevoIva(Iva nuevoIva) {
		this.nuevoIva = nuevoIva;
	}

	public IvaControlador getIvaControlador() {
		return ivaControlador;
	}

	public void setIvaControlador(IvaControlador ivaControlador) {
		this.ivaControlador = ivaControlador;
	}
}
