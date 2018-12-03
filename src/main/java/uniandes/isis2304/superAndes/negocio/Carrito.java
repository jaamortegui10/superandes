package uniandes.isis2304.superAndes.negocio;

public class Carrito implements VOCarrito{
	
	private long id;
	private long idSucursal;
	private String ocupado;
	
	public Carrito()
	{
		
	}

	public Carrito(long id, long idSucursal, String ocupado) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.ocupado = ocupado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getOcupado() {
		return ocupado;
	}

	public void setOcupado(String ocupado) {
		this.ocupado = ocupado;
	}
	
	
	
}
