package uniandes.isis2304.superAndes.negocio;

/**
 * @author Jhonatan
 *
 */
public class ProductoFisico implements VOProductoFisico{
	
	private long id;
	private long idOfrecido;
	private int cantidadMedida;
	private String codigoBarras;
	private long idContenedor;
	private long idCarrito;
	
	public ProductoFisico() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ProductoFisico(long id, long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor,
			long idCarrito) {
		super();
		this.id = id;
		this.idOfrecido = idOfrecido;
		this.cantidadMedida = cantidadMedida;
		this.codigoBarras = codigoBarras;
		this.idContenedor = idContenedor;
		this.idCarrito = idCarrito;
	}
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getIdOfrecido() {
		return idOfrecido;
	}



	public void setIdOfrecido(long idOfrecido) {
		this.idOfrecido = idOfrecido;
	}



	public long getIdContenedor() {
		return idContenedor;
	}



	public void setIdContenedor(long idContenedor) {
		this.idContenedor = idContenedor;
	}



	public long getIdCarrito() {
		return idCarrito;
	}



	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}



	public int getCantidadMedida() {
		return cantidadMedida;
	}
	public void setCantidadMedida(int cantidadMedida) {
		this.cantidadMedida = cantidadMedida;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	
	
	
	
}
