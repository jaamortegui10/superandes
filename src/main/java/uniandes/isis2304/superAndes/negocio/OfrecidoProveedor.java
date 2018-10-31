package uniandes.isis2304.superAndes.negocio;

public class OfrecidoProveedor implements VOOfrecidoProveedor{
	private long id;
	

	private long idAbstracto;
	private double precio;
	private int nitProveedor;
	
	public OfrecidoProveedor()
	{
		
	}

	public OfrecidoProveedor(long id, long idAbstracto, double precio, int idProveedor) {
		super();
		this.id = id;
		this.idAbstracto = idAbstracto;
		this.precio = precio;
		this.nitProveedor = idProveedor;
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

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}
	
	
	
}
