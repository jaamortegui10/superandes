package uniandes.isis2304.superAndes.negocio;

public class Factura implements VOFactura{
	
	private long id;
	private long idUser;
	private double costoTotal;
	
	public Factura()
	{
		
	}

	public Factura(long id, long idUser, double costoTotal) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.costoTotal = costoTotal;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	
	
	
	
}
