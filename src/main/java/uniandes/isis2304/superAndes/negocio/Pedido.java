package uniandes.isis2304.superAndes.negocio;

public class Pedido implements VOPedido{
	private long id;
	private long idSucursal;
	private int nitProveedor;
	private double precio;
	private String estado;
	
	private String fechaEntrega;
	private String calidad;
	private int calificacion;
	
	public Pedido(int id, long idSucursal, int nitProveedor, double precio, String estado, String fechaEntrega, String calidad, int calificacion) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.nitProveedor = nitProveedor;
		this.precio = precio;
		this.estado = estado;
		this.fechaEntrega = fechaEntrega;
		this.calidad = calidad;
		this.calificacion = calificacion;
	}
	
	
	
	public long getIdSucursal() {
		return idSucursal;
	}



	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getCalidad() {
		return calidad;
	}
	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
	
}
