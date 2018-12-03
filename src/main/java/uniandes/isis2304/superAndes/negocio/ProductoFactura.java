package uniandes.isis2304.superAndes.negocio;

public class ProductoFactura implements VOProductoFactura{
	private long idFactura;
	private long idProductoFisico;
	public ProductoFactura(long idFactura, long idProductoFisico) {
		super();
		this.idFactura = idFactura;
		this.idProductoFisico = idProductoFisico;
	}
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	public long getIdProductoFisico() {
		return idProductoFisico;
	}
	public void setIdProductoFisico(long idProductoFisico) {
		this.idProductoFisico = idProductoFisico;
	}
	
	
}
