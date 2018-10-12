package uniandes.isis2304.superAndes.negocio;

public class ProductoAbstracto implements VOProductoAbstracto{
	private long id;
	private String nombre;
	private String tipo;
	private String unidadMedida;
	private String categoria;
	
	public ProductoAbstracto()
	{
		
	}
	
	

	public ProductoAbstracto(long id, String nombre, String tipo, String unidadMedida, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.unidadMedida = unidadMedida;
		this.categoria = categoria;
	}
	
	
	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	
	
}
