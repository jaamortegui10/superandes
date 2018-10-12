package uniandes.isis2304.superAndes.negocio;

public class Empresa implements VOEmpresa{
	private int nit;
	private long idUser;
	private String direccion;
	private int puntosCompras;
	private String tipoEmpresa;
	
	
	public Empresa()
	{
		
	}


	public Empresa(int nit, long idUser, String direccion, int puntosCompras, String tipoEmpresa) {
		super();
		this.nit = nit;
		this.idUser = idUser;
		this.direccion = direccion;
		this.puntosCompras = puntosCompras;
		this.tipoEmpresa = tipoEmpresa;
	}


	public int getNit() {
		return nit;
	}


	public void setNit(int nit) {
		this.nit = nit;
	}


	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getPuntosCompras() {
		return puntosCompras;
	}


	public void setPuntosCompras(int puntosCompras) {
		this.puntosCompras = puntosCompras;
	}


	public String getTipoEmpresa() {
		return tipoEmpresa;
	}


	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	
	
}
