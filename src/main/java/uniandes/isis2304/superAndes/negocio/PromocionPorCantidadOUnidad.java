package uniandes.isis2304.superAndes.negocio;

public class PromocionPorCantidadOUnidad implements VOPromocionPorCantidadOUnidades{
	private long idProductoOfrecido;
	private long idPromocion;
	private int cantidadOUnidadesPagadas;
	private int cantidadOUnidadesCompradas;
	
	
	public PromocionPorCantidadOUnidad()
	{
		
	}

	public PromocionPorCantidadOUnidad(long idProductoOfrecido, long idPromocion, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas) {
		super();
		this.idPromocion = idPromocion;
		this.cantidadOUnidadesPagadas = cantidadOUnidadesPagadas;
		this.cantidadOUnidadesCompradas = cantidadOUnidadesCompradas;
		this.idProductoOfrecido = idProductoOfrecido;
	}

	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	public int getCantidadOUnidadesPagadas() {
		return cantidadOUnidadesPagadas;
	}

	public void setCantidadOUnidadesPagadas(int cantidadOUnidadesPagadas) {
		this.cantidadOUnidadesPagadas = cantidadOUnidadesPagadas;
	}

	public int getCantidadOUnidadesCompradas() {
		return cantidadOUnidadesCompradas;
	}

	public void setCantidadOUnidadesCompradas(int cantidadOUnidadesCompradas) {
		this.cantidadOUnidadesCompradas = cantidadOUnidadesCompradas;
	}

	public long getIdProductoOfrecido() {
		return idProductoOfrecido;
	}

	public void setIdProductoOfrecido(long idProductoOfrecido) {
		this.idProductoOfrecido = idProductoOfrecido;
	}

	
	
}
