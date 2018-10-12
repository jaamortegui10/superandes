package uniandes.isis2304.superAndes.negocio;

public class Ciudad implements VOCiudad{
	
	private long id;
	private String nombre;
	
	public Ciudad()
	{
		
	}

	public Ciudad(long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	
	
}
