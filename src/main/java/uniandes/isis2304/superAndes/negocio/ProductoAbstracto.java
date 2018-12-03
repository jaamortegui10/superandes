package uniandes.isis2304.superAndes.negocio;

public class ProductoAbstracto implements VOProductoAbstracto{
	private long id;
	private String nombre;
	private String unidadMedida;
	private int cantidadMedida;
	private long idTipo;
	
	public ProductoAbstracto()
	{
		
	}
	
	public ProductoAbstracto(long id, String nombre, String unidadMedida, int cantidadMedida, long idTipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.unidadMedida = unidadMedida;
		this.cantidadMedida = cantidadMedida;
		this.idTipo = idTipo;
	}
	
	public int getCantidadMedida() {
		return cantidadMedida;
	}



	public void setCantidadMedida(int cantidadMedida) {
		this.cantidadMedida = cantidadMedida;
	}



	public long getIdTipo() {
		return idTipo;
	}



	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	
	
}
