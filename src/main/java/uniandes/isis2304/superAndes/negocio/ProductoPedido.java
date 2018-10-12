package uniandes.isis2304.superAndes.negocio;

public class ProductoPedido implements VOProductoPedido{
	
	private long idPedido;
	private long idProductoOfrecido;
	private int cantidad;
	
	public ProductoPedido()
	{
		
	}

	public ProductoPedido(long idPedido, long idProductoOfrecido, int cantidad) {
		super();
		this.idPedido = idPedido;
		this.idProductoOfrecido = idProductoOfrecido;
		this.cantidad = cantidad;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
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
	
	
}
