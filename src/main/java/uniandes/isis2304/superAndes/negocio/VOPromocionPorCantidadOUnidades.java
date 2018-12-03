package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPromocionPorCantidadOUnidades {
	public long getId();
	public int getCantidadOUnidadesPagadas();
	public int getCantidadOUnidadesCompradas();
	public long getIdProductoOfrecido();
	public long getIdSucursal();
	public Date getFecha_inicio();
	public Date getFecha_fin();
	public String getDescripcion();
}
