package uniandes.isis2304.superAndes.negocio;

public class ProveedorSucursal implements VOProveedorSucursal{
	private long idSucursal;
	private long idProveedor;
	
	public ProveedorSucursal()
	{
		
	}

	public ProveedorSucursal(long idSucursal, long idProveedor) {
		super();
		this.idSucursal = idSucursal;
		this.idProveedor = idProveedor;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
	
	
}
