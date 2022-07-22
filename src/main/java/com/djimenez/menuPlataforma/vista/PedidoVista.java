package com.djimenez.menuPlataforma.vista;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.djimenez.menuPlataforma.controlador.impl.ClienteControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.ComboControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.DetalleControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.IvaControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.PedidoControladorImpl;
import com.djimenez.menuPlataforma.controlador.impl.PlatilloControladorImpl;
import com.djimenez.menuPlataforma.modelo.entidad.Cliente;
import com.djimenez.menuPlataforma.modelo.entidad.Combo;
import com.djimenez.menuPlataforma.modelo.entidad.Detalle;
import com.djimenez.menuPlataforma.modelo.entidad.Iva;
import com.djimenez.menuPlataforma.modelo.entidad.Pedido;
import com.djimenez.menuPlataforma.modelo.entidad.Platillo;

@ManagedBean
@ViewScoped
public class PedidoVista implements Serializable {

	private static final long serialVersionUID = 1L;

	// FACADE
	private ClienteControladorImpl clienteControlador = new ClienteControladorImpl();
	private PlatilloControladorImpl platilloControlador = new PlatilloControladorImpl();
	private IvaControladorImpl ivaControlador = new IvaControladorImpl();
	private DetalleControladorImpl detalleControlador = new DetalleControladorImpl();
	private PedidoControladorImpl pedidoControlador = new PedidoControladorImpl();
	private ComboControladorImpl comboControlador = new ComboControladorImpl();

	// LIST
	private List<Pedido> listarPedidos = new ArrayList<Pedido>();
	private List<Detalle> listarDetalles = new ArrayList<Detalle>();
	private List<Detalle> listarPedidoDetalles = new ArrayList<Detalle>();
	private List<Platillo> listarPlatillos = new ArrayList<Platillo>();
	private List<Platillo> listarPlatillosSeleccionados = new ArrayList<Platillo>();
	private List<Combo> listarCombos = new ArrayList<Combo>();
	private List<Combo> listarComboosSeleccionados = new ArrayList<Combo>();

	// OBJECT
	private Iva ivaSelect = new Iva();
	private Pedido nuevoPedido = new Pedido();
	private Pedido pedido = new Pedido();
	private Pedido actualizarPedido, eliminarPedido, limpiar;
	private Cliente cliente;

	// VARIABLES
	private Date fecha;
	private Double subtotal = 0.00;
	private Integer iva; 
	private Integer idPedido = null;
	private String contrasenia = "";
	private Double total;
	private boolean registrarCliente = true;
	private boolean encontrarPedido = false;

	public PedidoVista() {

	}

	@PostConstruct
	public void init() {
		pedidoControlador = new PedidoControladorImpl();
		comboControlador = new ComboControladorImpl();
		listarPedidos = new ArrayList<Pedido>();
		cliente = new Cliente();
		listarPlatillos = platilloControlador.listarPlatillo();
		listarCombos = comboControlador.listarCombo();
		listarPedidos();
		setListarDetalles(new ArrayList<Detalle>());
		ivaSelect = ivaControlador.obtenerIva();
		iva = (ivaSelect == null) ? 12 : ivaSelect.getIva();
	}

	public void insertarPedido() {
		try {
			if (nuevoPedido.getIdPedido() == null) {
				nuevoPedido.setFecha(new Date());
				pedidoControlador.insertarPedido(nuevoPedido);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido Registrado", ""));
				listarPedidos();
			} else {
				pedidoControlador.actualizarPedido(nuevoPedido);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Pedido Actualizado", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void listarPedidos() {
		listarPedidos = pedidoControlador.listarPedido();
	}

	public void insertarCliente() {
		try {
			if (cliente.getIdCliente() == null) {
				cliente.setEstado(true);
				cliente.setContrasenia(claveEncriptadaSHA1(contrasenia));
				clienteControlador.insertarCliente(cliente);
			} else {
				if (contrasenia != null) {
					cliente.setContrasenia(claveEncriptadaSHA1(contrasenia));
				}
				clienteControlador.actualizarCliente(cliente);
			}
			nuevoPedido.setFkCliente(cliente);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Registrado", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

	}

	public void buscarCliente() {
		Cliente clienteBase = this.clienteControlador.buscarPorCorreoCliente(cliente.getCorreo());
		try {
			if (clienteBase != null) {
				cliente = clienteBase;
				registrarCliente = false;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Encontrado", ""));
				
			} else {
				cliente.setNombre(null);
				cliente.setApellido(null);
				cliente.setTelefono(null);
				cliente.setContrasenia(null);
				registrarCliente = true;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ingresar correo electronico", ""));
				// FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Registrado", ""));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo Registrar", ""));
		}

		nuevoPedido.setFkCliente(cliente);
	}

	public void agregarPlatillos(Platillo platillo) {
		Detalle detalle = new Detalle();
		detalle.setFkPlatillo(platillo);
		detalle.setCantidad(1);
		detalle.setPrecio(platillo.getPrecio());
		detalle.setNombre(platillo.getNombre());

		if (!listarDetalles.isEmpty()) {
			Boolean estado = false;
			for (Detalle detalleIngresado : listarDetalles) {
				if (detalleIngresado.getFkPlatillo() == platillo) {
					estado = true;
				}
			}
			if (!estado) {
				listarDetalles.add(detalle);
			}
		} else {
			listarDetalles.add(detalle);
		}
		calcularTotal();
	}

	public void agregarCombos(Combo combo) {
		Detalle detalle = new Detalle();
		detalle.setFkCombo(combo);
		detalle.setCantidad(1);
		detalle.setPrecio(combo.getPrecio());
		detalle.setNombre(combo.getNombre());

		if (!listarDetalles.isEmpty()) {
			Boolean estado = false;
			for (Detalle detalleIngresado : listarDetalles) {
				if (detalleIngresado.getFkCombo() == combo) {
					estado = true;
				}
			}
			if (!estado) {
				listarDetalles.add(detalle);
			}
		} else {
			listarDetalles.add(detalle);
		}
		calcularTotal();
	}

	public void calcularPrecio(Detalle detalle) {
		Integer cantidad = detalle.getCantidad();
		Double precio = 0.00;
		if (detalle.getFkPlatillo() != null) {
			precio = detalle.getFkPlatillo().getPrecio();
		}
		if (detalle.getFkCombo() != null) {
			precio = detalle.getFkCombo().getPrecio();
		}
		double resultado = 0.00;
		for (Detalle detalle2 : listarDetalles) {
			if (detalle2.equals(detalle)) {
				resultado = Double.valueOf(cantidad) * precio;
				detalle2.setPrecio(resultado);
			}
		}
		calcularTotal();
	}

	private void calcularTotal() {
		subtotal = 0.00;
		for (Detalle detalle2 : listarDetalles) {
			Double precio = detalle2.getPrecio();
			subtotal += precio;
		}
		total = subtotal + (subtotal * iva / 100);
		nuevoPedido.setIva(iva);
		nuevoPedido.setFkIva(ivaSelect);
		nuevoPedido.setSubtotal(subtotal);
		nuevoPedido.setTotal(total);
	}
	
	public void buscarPedido() {
		pedido = this.pedidoControlador.pedidoById(idPedido);
		if (pedido != null) {
			encontrarPedido = true;
			cliente = pedido.getFkCliente();
			listarPedidoDetalles = detalleControlador.listarDetalleByPedido(idPedido);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido Encontrado", ""));
		} else {
			encontrarPedido = false;
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verificar el número de pedido", ""));
		}
	}
	
	public void limpiarBusqueda() {
		encontrarPedido = false;
		pedido = new Pedido();
		cliente = new Cliente();
		listarPedidoDetalles = new ArrayList<Detalle>();
	}
	
	public void limpiarPedido() {
		cliente = new Cliente();
		nuevoPedido = new Pedido();
		iva = (ivaSelect == null) ? 12 : ivaSelect.getIva();
		subtotal = 0.00;
		total = 0.00;
		listarDetalles = new ArrayList<Detalle>();
	}

	public void guardar() {
		if (nuevoPedido.getIdPedido() == null) {
			nuevoPedido.setFecha(new Date());
			pedidoControlador.insertarPedido(nuevoPedido);
		} else {
			pedidoControlador.actualizarPedido(nuevoPedido);
		}
		for (Detalle detalle : listarDetalles) {
			detalle.setFkPedido(nuevoPedido);
			if (detalle.getIdDetalle() == null) {
				detalleControlador.insertarDetalle(detalle);
			} else {
				detalleControlador.actualizarDetalle(detalle);
			}
		}
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

	public ClienteControladorImpl getClienteControlador() {
		return clienteControlador;
	}

	public PlatilloControladorImpl getPlatilloControlador() {
		return platilloControlador;
	}

	public void setPlatilloControlador(PlatilloControladorImpl platilloControlador) {
		this.platilloControlador = platilloControlador;
	}

	public IvaControladorImpl getIvaControlador() {
		return ivaControlador;
	}

	public void setIvaControlador(IvaControladorImpl ivaControlador) {
		this.ivaControlador = ivaControlador;
	}

	public DetalleControladorImpl getDetalleControlador() {
		return detalleControlador;
	}

	public void setDetalleControlador(DetalleControladorImpl detalleControlador) {
		this.detalleControlador = detalleControlador;
	}

	public PedidoControladorImpl getPedidoControlador() {
		return pedidoControlador;
	}

	public void setPedidoControlador(PedidoControladorImpl pedidoControlador) {
		this.pedidoControlador = pedidoControlador;
	}

	public ComboControladorImpl getComboControlador() {
		return comboControlador;
	}

	public void setComboControlador(ComboControladorImpl comboControlador) {
		this.comboControlador = comboControlador;
	}

	public List<Pedido> getListarPedidos() {
		return listarPedidos;
	}

	public void setListarPedidos(List<Pedido> listarPedidos) {
		this.listarPedidos = listarPedidos;
	}

	public List<Detalle> getListarDetalles() {
		return listarDetalles;
	}

	public void setListarDetalles(List<Detalle> listarDetalles) {
		this.listarDetalles = listarDetalles;
	}

	public List<Detalle> getListarPedidoDetalles() {
		return listarPedidoDetalles;
	}

	public void setListarPedidoDetalles(List<Detalle> listarPedidoDetalles) {
		this.listarPedidoDetalles = listarPedidoDetalles;
	}

	public List<Platillo> getListarPlatillos() {
		return listarPlatillos;
	}

	public void setListarPlatillos(List<Platillo> listarPlatillos) {
		this.listarPlatillos = listarPlatillos;
	}

	public List<Platillo> getListarPlatillosSeleccionados() {
		return listarPlatillosSeleccionados;
	}

	public void setListarPlatillosSeleccionados(List<Platillo> listarPlatillosSeleccionados) {
		this.listarPlatillosSeleccionados = listarPlatillosSeleccionados;
	}

	public List<Combo> getListarCombos() {
		return listarCombos;
	}

	public void setListarCombos(List<Combo> listarCombos) {
		this.listarCombos = listarCombos;
	}

	public List<Combo> getListarComboosSeleccionados() {
		return listarComboosSeleccionados;
	}

	public void setListarComboosSeleccionados(List<Combo> listarComboosSeleccionados) {
		this.listarComboosSeleccionados = listarComboosSeleccionados;
	}

	public Iva getIvaSelect() {
		return ivaSelect;
	}

	public void setIvaSelect(Iva ivaSelect) {
		this.ivaSelect = ivaSelect;
	}

	public Pedido getNuevoPedido() {
		return nuevoPedido;
	}

	public void setNuevoPedido(Pedido nuevoPedido) {
		this.nuevoPedido = nuevoPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getActualizarPedido() {
		return actualizarPedido;
	}

	public void setActualizarPedido(Pedido actualizarPedido) {
		this.actualizarPedido = actualizarPedido;
	}

	public Pedido getEliminarPedido() {
		return eliminarPedido;
	}

	public void setEliminarPedido(Pedido eliminarPedido) {
		this.eliminarPedido = eliminarPedido;
	}

	public Pedido getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(Pedido limpiar) {
		this.limpiar = limpiar;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public boolean isRegistrarCliente() {
		return registrarCliente;
	}

	public void setRegistrarCliente(boolean registrarCliente) {
		this.registrarCliente = registrarCliente;
	}

	public boolean isEncontrarPedido() {
		return encontrarPedido;
	}

	public void setEncontrarPedido(boolean encontrarPedido) {
		this.encontrarPedido = encontrarPedido;
	}

	public void setClienteControlador(ClienteControladorImpl clienteControlador) {
		this.clienteControlador = clienteControlador;
	}
}
