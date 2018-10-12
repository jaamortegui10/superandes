package uniandes.isis2304.superAndes.negocio;

public class User implements VOUser{
	
	private long id;
	private String login;
	private String password;
	private String nombre;
	private String correo;
	private String tipo;
	
	public User()
	{
		
	}

	public User(long id, String login, String password, String nombre, String correo, String tipo) {
		super();
		this.id = id;
		this.login = login;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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
