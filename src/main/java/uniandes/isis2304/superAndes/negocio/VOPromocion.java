package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPromocion {
	public long getId();
	public long getIdSucursal();
	public String getDescripcion();
	public String getTipo();
	public Date getFecha_inicio();
	public Date getFecha_fin();	
}
