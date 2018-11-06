package uniandes.isis2304.superAndes.negocio;

public class Promocion implements VOPromocion{
	
	private long id;
	private long idSucursal;
	private String descripcion;
	private String tipo;
	public Promocion()
	{
		
	}
	public Promocion(long id, long idSucursal, String descripcion, String tipo) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.descripcion = descripcion;
		this.tipo = tipo;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
	
}
