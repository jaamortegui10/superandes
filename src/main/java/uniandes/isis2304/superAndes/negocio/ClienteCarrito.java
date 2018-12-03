package uniandes.isis2304.superAndes.negocio;

public class ClienteCarrito implements VOClienteCarrito{
	
	private long docCliente;
	private long idCarrito;
	
	public ClienteCarrito()
	{
		
	}

	public ClienteCarrito(long docCliente, long idCarrito) {
		super();
		this.docCliente = docCliente;
		this.idCarrito = idCarrito;
	}

	public long getDocCliente() {
		return docCliente;
	}

	public void setDocCliente(long docCliente) {
		this.docCliente = docCliente;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}
	
	
}
