package uniandes.isis2304.superAndes.negocio;

public class Promocion implements VOPromocion{
	
	private long id;
	private long idSucursal;
	private String descripcion;
	public Promocion(long id, long idSucursal, String descripcion) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.descripcion = descripcion;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
