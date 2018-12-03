package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public interface VOPedido {
	public long getId();
	public long getIdSucursal();
	public int getNitProveedor();
	public double getPrecio();
	public String getEstado();
	public Date getFechaEntrega();
	public String getCalidad();
	public int getCalificacion();
}
