package uniandes.isis2304.superAndes.negocio;

public class PromocionPorcentajeDescuento implements VOPromocionPorcentajeDescuento{
	
	private long idPromocion;
	private int porcentajeDescuento;
	private long idProductoOfrecido;
	
	
	public PromocionPorcentajeDescuento()
	{
		
	}
	
	
	
	public PromocionPorcentajeDescuento(long idPromocion, int porcentajeDescuento, long idProductoOfrecido) {
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
