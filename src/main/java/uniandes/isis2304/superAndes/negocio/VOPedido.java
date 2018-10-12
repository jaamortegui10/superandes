package uniandes.isis2304.superAndes.negocio;

public interface VOPedido {
	public long getId();
	public long getIdSucursal();
	public int getNitProveedor();
	public double getPrecio();
	public String getEstado();
	public String getFechaEntrega();
	public String getCalidad();
	public int getCalificacion();
}
