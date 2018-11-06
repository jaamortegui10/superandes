package uniandes.isis2304.superAndes.negocio;

public class Usuario implements VOUsuario{
	
	private long id;
	private String password;
	private String nombre;
	private String correo;
	private String tipo;
	
	public Usuario()
	{
		
	}

	public Usuario(long id, String password, String nombre, String correo, String tipo) {
		super();
		this.id = id;
		this.password = password;
		this.nombre = nombre;
		this.correo = correo;
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	
}
