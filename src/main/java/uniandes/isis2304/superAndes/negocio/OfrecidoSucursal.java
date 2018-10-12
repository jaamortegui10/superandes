package uniandes.isis2304.superAndes.negocio;

public class OfrecidoSucursal implements VOOfrecidoSucursal{
	
	private long idAbstracto;
	private double precio;
	private long idSucursal;


	public OfrecidoSucursal() {
		
	}


	public OfrecidoSucursal(long idAbstracto, long idSucursal, double precio) {
		super();
		this.idAbstracto = idAbstracto;
		this.precio = precio;
		this.idSucursal = idSucursal;
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
