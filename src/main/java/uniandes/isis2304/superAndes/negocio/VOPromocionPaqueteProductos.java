package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPromocionPaqueteProductos {
	public long getId();
	public double getPrecio();
	public long getIdProductoOfrecido1();
	public long getIdProductoOfrecido2();
	public long getIdSucursal();
	public Date getFecha_inicio();
	public Date getFecha_fin();
	public String getDescripcion();
}
