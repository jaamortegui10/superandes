package uniandes.isis2304.superAndes.negocio;

/**
 * @author Jhonatan
 *
 */
public class ProductoFisico implements VOProductoFisico{
	
	private long id;
	private String codigoBarras;
	private String estado;
	private long idTipo;
	private long idAbstracto;
	private double precio;
	
	public ProductoFisico() {
		// TODO Auto-generated constructor stub
	}

	

	public ProductoFisico(long id, String codigoBarras, String estado, long idTipo, long idAbstracto, double precio) {
		super();
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.estado = estado;
		this.idTipo = idTipo;
		this.idAbstracto = idAbstracto;
		this.precio = precio;
	}
	
	

	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}

	public long getIdAbstracto() {
		return idAbstracto;
	}

	public void setIdAbstracto(long idAbstracto) {
		this.idAbstracto = idAbstracto;
	}
	
	
}
