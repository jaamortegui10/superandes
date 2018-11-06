package uniandes.isis2304.superAndes.negocio;

public class Persona implements VOPersona{
	
	public static String TIPO_CLIENTE = "cliente";
	public static String TIPO_TRABAJADOR_SUCURSAL = "trabajador_sucursal";
	
	private int cedula;
	private long idUser;
	private int puntos;
	private long sucursalId;
	private String tipoPersona;
	
	public Persona()
	{
		
	}

	public Persona(int cedula, long idUser, int puntos, long sucursalId, String tipoPersona) {
		super();
		this.cedula = cedula;
		this.idUser = idUser;
		this.puntos = puntos;
		this.sucursalId = sucursalId;
		this.tipoPersona = tipoPersona;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public long getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(long sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	
	
}
