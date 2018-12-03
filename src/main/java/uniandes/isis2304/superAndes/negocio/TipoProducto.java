package uniandes.isis2304.superAndes.negocio;

public class TipoProducto implements VOTipoProducto{
	
	private long id;
	private String nombre;
	private String categoria;
	
	public TipoProducto()
	{
		
	}

	public TipoProducto(long id, String nombre, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
