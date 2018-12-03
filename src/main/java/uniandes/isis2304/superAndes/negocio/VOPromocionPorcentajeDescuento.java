package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPromocionPorcentajeDescuento {
	public long getId();
	public double getPorcentajeDescuento();
	public long getIdProductoOfrecido();
	public long getIdSucursal();
	public Date getFecha_inicio();
	public Date getFecha_fin();
	public String getDescripcion();
}
