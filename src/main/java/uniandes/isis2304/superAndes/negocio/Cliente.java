package uniandes.isis2304.superAndes.negocio;

public class Cliente implements VOCliente{
	
	/* ****************************************************
	 *  Constantes
	 ******************************************************/
	public final static String PERSONA = "persona";
	public final static String EMPRESA = "empresa";
	
	/* ****************************************************
	 *  Atributos
	 ******************************************************/
	private int documento;
	private String nombre;
	private String password;
	private String dir;
	private String tipo;
	private int puntos;
	private String conectado;
	
	/* ****************************************************
	 *  Métodos
	 ******************************************************/
	public Cliente()
	{
		
	}
	
	public Cliente(int documento, String nombre, String password, String dir, String tipo, int puntos,
			String conectado) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.password = password;
		this.dir = dir;
		this.tipo = tipo;
		this.puntos = puntos;
		this.conectado = conectado;
	}

    

	public String getConectado() {
		return conectado;
	}

	public void setConectado(String conectado) {
		this.conectado = conectado;
	}

	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
}
