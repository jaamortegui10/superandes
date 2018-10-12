package uniandes.isis2304.superAndes.negocio;

public class Contenedor implements VOContenedor{
	
	private long id;
	private long sucursalId;
	private String tipo;
	private int capacidad;
	private int capacidadOcupada;
	
	public Contenedor()
	{
		
	}

	

	public Contenedor(long id, long sucursalId, String tipo, int capacidad, int capacidadOcupada) {
		super();
		this.id = id;
		this.sucursalId = sucursalId;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.capacidadOcupada = capacidadOcupada;
	}
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getSucursalId() {
		return sucursalId;
	}



	public void setSucursalId(long sucursalId) {
		this.sucursalId = sucursalId;
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
