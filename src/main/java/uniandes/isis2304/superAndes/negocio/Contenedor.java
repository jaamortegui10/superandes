package uniandes.isis2304.superAndes.negocio;

public class Contenedor implements VOContenedor{
	
	private long id;
	private long idSucursal;
	private String tipo;
	private int capacidad;
	private int capacidadOcupada;
	private String categoria;
	
	public Contenedor()
	{
		
	}
	
	

	public Contenedor(long id, long idSucursal, String tipo, int capacidad, int capacidadOcupada, String categoria) {
		super();
		this.id = id;
		this.idSucursal = idSucursal;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.capacidadOcupada = capacidadOcupada;
		this.categoria = categoria;
	}
	
	

	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
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
	
	public int getCapacidadOcupada() {
		return capacidadOcupada;
	}



	public void setCapacidadOcupada(int capacidadOcupada) {
		this.capacidadOcupada = capacidadOcupada;
	}



	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	
	
}
