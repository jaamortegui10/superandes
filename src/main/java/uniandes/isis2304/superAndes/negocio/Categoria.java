package uniandes.isis2304.superAndes.negocio;

public class Categoria implements VOCategoria{
	
	private String nombre;
	private String caracteristicas;
	private String almacenamiento;
	private String manejo;
	
	/**
	 * Constructor por defecto.
	 */
	public Categoria()
	{
		this.nombre = "defaultName";
		this.caracteristicas = "default";
		this.almacenamiento = "default";
		this.manejo = "default";
	}
	
	/**
	 * Constructor con parámetros.
	 * @param nombre
	 * @param caracteristicas
	 * @param almacenamiento
	 * @param manejo
	 */
	public Categoria(String nombre, String caracteristicas, String almacenamiento, String manejo)
	{
		this.nombre = nombre;
		this.caracteristicas = caracteristicas;
		this.almacenamiento = almacenamiento;
		this.manejo = manejo;
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public void setManejo(String manejo) {
		this.manejo = manejo;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public String getAlmacenamiento() {
		return almacenamiento;
	}

	public String getManejo() {
		return manejo;
	}
	
	
}
