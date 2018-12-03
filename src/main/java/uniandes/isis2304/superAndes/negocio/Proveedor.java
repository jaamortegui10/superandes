package uniandes.isis2304.superAndes.negocio;

public class Proveedor implements VOProveedor{
	
	public static String TIPO_CLIENTE = "cliente";
	public static String TIPO_TRABAJADOR_SUCURSAL = "trabajador_sucursal";
	
	private int nit;
	private String nombre;
	private String password;
	private String dir;
	private String conectado;
	
	public Proveedor()
	{
		
	}

	

	public Proveedor(int nit, String nombre, String password, String dir, String conectado) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.password = password;
		this.dir = dir;
		this.conectado = conectado;
	}
	
	public String getConectado() {
		return conectado;
	}



	public void setConectado(String conectado) {
		this.conectado = conectado;
	}



	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
	
	
}
