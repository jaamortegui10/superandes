package uniandes.isis2304.superAndes.negocio;

public class ProductoCarrito implements VOProductoCarrito{
	private long idCarrito;
	private long idProductoFisico;
	
	public ProductoCarrito()
	{
		
	}

	public ProductoCarrito(long idCarrito, long idProductoFisico) {
		super();
		this.idCarrito = idCarrito;
		this.idProductoFisico = idProductoFisico;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public long getIdProductoFisico() {
		return idProductoFisico;
	}

	public void setIdProductoFisico(long idProductoFisico) {
		this.idProductoFisico = idProductoFisico;
	}
	
	
}
