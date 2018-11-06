package uniandes.isis2304.superAndes.negocio;

public class Empresa implements VOEmpresa{
	
	/* ****************************************************
	 *  Constantes
	 ******************************************************/
	public static final String PROVEEDOR = "proveedor";
	public static final String CLIENTE = "cliente";
	
	/* ****************************************************
	 *  Atributos
	 ******************************************************/
	private int nit;
	private long idUser;
	private String dir;
	private int puntos;
	

	private String tipoEmpresa;
	
	/* ****************************************************
	 *  Métodos
	 ******************************************************/
	public Empresa()
	{
		
	}


	public Empresa(int nit, long idUser, String dir, int puntos, String tipoEmpresa) {
		super();
		this.nit = nit;
		this.idUser = idUser;
		this.dir = dir;
		this.puntos = puntos;
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
	
	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public String getTipoEmpresa() {
		return tipoEmpresa;
	}


	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}
	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}

	
}
