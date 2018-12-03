package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOFactura {
	public long getId();
	public long getDocCliente();
	public long getIdSucursal();
	public double getCostoTotal();
	public Date getFecha();
}
