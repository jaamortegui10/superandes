package uniandes.isis2304.superAndes.negocio;

public class Carrito implements VOCarrito{
	
	private long id;
	private long idUser;
	
	public Carrito()
	{
		
	}

	public Carrito(long id, long idUser) {
		super();
		this.id = id;
		this.idUser = idUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	
}
