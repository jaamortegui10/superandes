package uniandes.isis2304.superAndes.negocio;

public class OfrecidoSucursal implements VOOfrecidoSucursal{
	
	private long id;
	private long idAbstracto;
	private double precio;
	private long idSucursal;


	public OfrecidoSucursal() {
		
	}


	public OfrecidoSucursal(long id, long idAbstracto, long idSucursal, double precio) {
		super();
		this.id = id;
		this.idAbstracto = idAbstracto;
		this.precio = precio;
		this.idSucursal = idSucursal;
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdAbstracto() {
		return idAbstracto;
	}


	public void setIdAbstracto(long idAbstracto) {
		this.idAbstracto = idAbstracto;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public long getIdSucursal() {
		return idSucursal;
	}


	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	
	
	
	
}
