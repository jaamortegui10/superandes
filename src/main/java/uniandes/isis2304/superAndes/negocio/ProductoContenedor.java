package uniandes.isis2304.superAndes.negocio;

public class ProductoContenedor implements VOProductoContenedor{
	private long idContenedor;
	private long idProductoFisico;
	public ProductoContenedor(long idContenedor, long idProductoFisico) {
		super();
		this.idContenedor = idContenedor;
		this.idProductoFisico = idProductoFisico;
	}
	public long getIdContenedor() {
		return idContenedor;
	}
	public void setIdContenedor(long idContenedor) {
		this.idContenedor = idContenedor;
	}
	public long getIdProductoFisico() {
		return idProductoFisico;
	}
	public void setIdProductoFisico(long idProductoFisico) {
		this.idProductoFisico = idProductoFisico;
	}
	
	
}
