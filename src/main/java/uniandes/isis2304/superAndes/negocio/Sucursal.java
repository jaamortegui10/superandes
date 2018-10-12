package uniandes.isis2304.superAndes.negocio;

public class Sucursal implements VOSucursal{
	
	private long id;
	private String nombre;
	private int tamanho;
	private String direccion;
	private long idCiudad;
	private int nivelReorden;
	private int nivelReAbastecimiento;
	
	
	
	public Sucursal(long id, String nombre, int tamanho, String direccion, long idCiudad, int nivelReorden,
			int nivelReAbastecimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tamanho = tamanho;
		this.direccion = direccion;
		this.idCiudad = idCiudad;
		this.nivelReorden = nivelReorden;
		this.nivelReAbastecimiento = nivelReAbastecimiento;
	}
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getTamanho() {
		return tamanho;
	}



	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}



	public long getIdCiudad() {
		return idCiudad;
	}



	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}



	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getNivelReorden() {
		return nivelReorden;
	}
	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}
	public int getNivelReAbastecimiento() {
		return nivelReAbastecimiento;
	}
	public void setNivelReAbastecimiento(int nivelReAbastecimiento) {
		this.nivelReAbastecimiento = nivelReAbastecimiento;
	}
	
	

}
