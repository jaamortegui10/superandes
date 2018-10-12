package uniandes.isis2304.superAndes.negocio;

public class ItemFactura implements VOItemFactura{
	
	private long idFactura;
	private long idProductoOfrecido;
	private int cantidad;
	private double costo;
	
	public ItemFactura()
	{
		
	}

	public ItemFactura(long idFactura, long idProductoOfrecido, int cantidad, double costo) {
		super();
		this.idFactura = idFactura;
		this.idProductoOfrecido = idProductoOfrecido;
		this.cantidad = cantidad;
		this.costo = costo;
	}

	public long getIdFactura() {
		return idFactura;
	}

	protected void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public long getIdProductoOfrecido() {
		return idProductoOfrecido;
	}

	public void setIdProductoOfrecido(long idProductoOfrecido) {
		this.idProductoOfrecido = idProductoOfrecido;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getCosto() {
		return costo;
	}

	protected void setCosto(double costo) {
		this.costo = costo;
	}
	
	
}
