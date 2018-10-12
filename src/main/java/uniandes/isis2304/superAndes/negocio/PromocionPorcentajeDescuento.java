package uniandes.isis2304.superAndes.negocio;

public class PromocionPorcentajeDescuento implements VOPromocionPorcentajeDescuento{
	
	private long idProductoOfrecido;
	private long idPromocion;
	private int porcentajeDescuento;
	
	
	
	public PromocionPorcentajeDescuento()
	{
		
	}
	
	
	
	public PromocionPorcentajeDescuento(long idProductoOfrecido, long idPromocion, int porcentajeDescuento ) {
		super();
		this.idPromocion = idPromocion;
		this.porcentajeDescuento = porcentajeDescuento;
		this.idProductoOfrecido = idProductoOfrecido;
	}
	
	

	public long getIdPromocion() {
		return idPromocion;
	}



	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}



	public long getIdProductoOfrecido() {
		return idProductoOfrecido;
	}



	public void setIdProductoOfrecido(long idProductoOfrecido) {
		this.idProductoOfrecido = idProductoOfrecido;
	}



	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}



	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	
	
	
}
