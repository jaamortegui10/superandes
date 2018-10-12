package uniandes.isis2304.superAndes.negocio;

public class Persona implements VOPersona{
	
	private int cedula;
	private long idUser;
	private int puntosCompras;
	private long sucursalId;
	private String tipoPersona;
	
	public Persona()
	{
		
	}

	public Persona(int cedula, long idUser, int puntosCompras, long sucursalId, String tipoPersona) {
		super();
		this.cedula = cedula;
		this.idUser = idUser;
		this.puntosCompras = puntosCompras;
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

	public int getPuntosCompras() {
		return puntosCompras;
	}

	public void setPuntosCompras(int puntosCompras) {
		this.puntosCompras = puntosCompras;
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
