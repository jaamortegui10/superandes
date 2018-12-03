package uniandes.isis2304.superAndes.negocio;

public class Categoria implements VOCategoria{
	
	private String nombre;
	private String caracteristicas;
	private String manejo;
	
	/**
	 * Constructor por defecto.
	 */
	public Categoria()
	{
		this.nombre = "defaultName";
		this.caracteristicas = "default";
		this.manejo = "default";
	}
	
	/**
	 * Constructor con parámetros.
	 * @param nombre
	 * @param caracteristicas
	 * @param almacenamiento
	 * @param manejo
	 */
	public Categoria(String nombre, String caracteristicas, String manejo)
	{
		this.nombre = nombre;
		this.caracteristicas = caracteristicas;
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

	public void setManejo(String manejo) {
		this.manejo = manejo;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public String getManejo() {
		return manejo;
	}
	
	
}
