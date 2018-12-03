package uniandes.isis2304.superAndes.negocio;

public class ProveedorSucursal implements VOProveedorSucursal{
	private long idSucursal;
	private int nitProveedor;
	
	public ProveedorSucursal()
	{
		
	}

	public ProveedorSucursal(long idSucursal, int nitProveedor) {
		super();
		this.idSucursal = idSucursal;
		this.nitProveedor = nitProveedor;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	
}
