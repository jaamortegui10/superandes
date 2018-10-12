package uniandes.isis2304.superAndes.negocio;

public class PromocionPaqueteProductos implements VOPromocionPaqueteProductos{
	
	private long idPromocion;
	private double precio;
	private long idProductoOfrecido1;
	private long idProductoOfrecido2;
	
	
	public PromocionPaqueteProductos()
	{
		
	}


	public PromocionPaqueteProductos(long idPromocion, double precio, long idProductoOfrecido1,
			long idProductoOfrecido2) {
		super();
		this.idPromocion = idPromocion;
		this.precio = precio;
		this.idProductoOfrecido1 = idProductoOfrecido1;
		this.idProductoOfrecido2 = idProductoOfrecido2;
	}


	public long getIdPromocion() {
		return idPromocion;
	}


	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public long getIdProductoOfrecido1() {
		return idProductoOfrecido1;
	}


	public void setIdProductoOfrecido1(long idProductoOfrecido1) {
		this.idProductoOfrecido1 = idProductoOfrecido1;
	}


	public long getIdProductoOfrecido2() {
		return idProductoOfrecido2;
	}


	public void setIdProductoOfrecido2(long idProductoOfrecido2) {
		this.idProductoOfrecido2 = idProductoOfrecido2;
	}



	
	
	
	
}
