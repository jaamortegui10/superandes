package uniandes.isis2304.superAndes.negocio;

import java.util.Date;

public class Factura implements VOFactura{
	
	private long id;
	private long docCliente;
	private long idSucursal;
	private double costoTotal;
	private Date fecha;
	
	public Factura()
	{
		
	}
	
	public Factura(long id, long docCliente, long idSucursal, double costoTotal, Date fecha) {
		super();
		this.id = id;
		this.docCliente = docCliente;
		this.idSucursal = idSucursal;
		this.costoTotal = costoTotal;
		this.fecha = fecha;
	}
	
	



	public long getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(long docCliente) {
		this.docCliente = docCliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public long getIdSucursal() {
		return idSucursal;
	}



	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	
	
	
	
	
}
