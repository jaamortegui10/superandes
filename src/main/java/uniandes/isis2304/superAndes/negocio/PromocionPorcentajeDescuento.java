package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromocionPorcentajeDescuento implements VOPromocionPorcentajeDescuento{
	
	private long idProductoOfrecido;
	private long id;
	private double porcentajeDescuento;
	private long idSucursal;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String descripcion;
	
	
	public PromocionPorcentajeDescuento()
	{
		
	}
	
	
	
	



	public PromocionPorcentajeDescuento(long idProductoOfrecido, long id, double porcentajeDescuento, long idSucursal,
			Date fecha_inicio, Date fecha_fin, String descripcion) {
		super();
		this.idProductoOfrecido = idProductoOfrecido;
		this.id = id;
		this.porcentajeDescuento = porcentajeDescuento;
		this.idSucursal = idSucursal;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.descripcion = descripcion;
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







	public Date getFecha_inicio() {
		return fecha_inicio;
	}







	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}







	public Date getFecha_fin() {
		return fecha_fin;
	}







	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}







	public String getDescripcion() {
		return descripcion;
	}







	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}







	public long getIdProductoOfrecido() {
		return idProductoOfrecido;
	}



	public void setIdProductoOfrecido(long idProductoOfrecido) {
		this.idProductoOfrecido = idProductoOfrecido;
	}



	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}



	public void setPorcentajeDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	
	
}
