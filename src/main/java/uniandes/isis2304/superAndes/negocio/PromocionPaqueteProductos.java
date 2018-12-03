package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class PromocionPaqueteProductos implements VOPromocionPaqueteProductos{
	
	private long id;
	private double precio;
	private long idProductoOfrecido1;
	private long idProductoOfrecido2;
	private long idSucursal;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String descripcion;
	
	
	public PromocionPaqueteProductos()
	{
		
	}
	
	


	public PromocionPaqueteProductos(long id, double precio, long idProductoOfrecido1, long idProductoOfrecido2,
			long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion) {
		super();
		this.id = id;
		this.precio = precio;
		this.idProductoOfrecido1 = idProductoOfrecido1;
		this.idProductoOfrecido2 = idProductoOfrecido2;
		this.idSucursal = idSucursal;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.descripcion = descripcion;
	}




	public String getDescripcion() {
		return descripcion;
	}




	public void setDescripcion(String descripcion) {
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





	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public long getIdProductoOfrecido1() {
		return idProductoOfrecido1;
	}


	public void setIdProductoOfrecido1(long idProductoOfrecido1) {
		this.idProductoOfrecido1 = idProductoOfrecido1;
	}


	public long getIdProductoOfrecido2() {
		return idProductoOfrecido2;
	}


	public void setIdProductoOfrecido2(long idProductoOfrecido2) {
		this.idProductoOfrecido2 = idProductoOfrecido2;
	}



	
	
	
	
}
