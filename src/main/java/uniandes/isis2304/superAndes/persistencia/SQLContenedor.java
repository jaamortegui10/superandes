package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Contenedor;

public class SQLContenedor {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private static final String SQL = PersistenciaSuperAndes.SQL;
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Método constructor de la clase.
	 * @param psa
	 */
	public SQLContenedor(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla a la relación Contenedor.
	 * @param pm
	 * @param id
	 * @param sucursalId
	 * @param tipo
	 * @param capacidad
	 * @param capacidadOcupada
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idSucursal, String tipo, int capacidad, int capacidadOcupada, String categoria)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaContenedor() + "(id, idSucursal, tipo, capacidad, capacidadOcupada, categoria) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idSucursal, tipo, capacidad, capacidadOcupada, categoria);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna la tupla con el id dado por parámetro de relación Contenedor.
	 * @param pm
	 * @param id
	 * @return
	 */
	public Contenedor darContenedorPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaContenedor() + " WHERE id = ?");
		q.setResultClass(Contenedor.class);
		q.setParameters(id);
		return (Contenedor) q.executeUnique();
	}
	
	/**
	 * Método que retorna la tupla con la sucursalId dada por parámetro de relación Contenedor.
	 * @param pm
	 * @param sucursalId
	 * @return
	 */
	public List<Contenedor> darContenedoresPorSucursalId(PersistenceManager pm, long sucursalId)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaContenedor() + " WHERE idSucursal = ?");
		q.setResultClass(Contenedor.class);
		q.setParameters(sucursalId);
		return (List<Contenedor>) q.executeList();
	}
	
	public List<Contenedor> darContenedoresPorSucursalIdYCategoria(PersistenceManager pm, long sucursalId, String categoria)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaContenedor() + " WHERE idSucursal = ? AND categoria = ?");
		q.setResultClass(Contenedor.class);
		q.setParameters(sucursalId, categoria);
		return (List<Contenedor>) q.executeList();
		
	}
	
	/**
	 * Método para cambiar la capacidadOcupada de la tupla con el id dado por parámetro de la relación Contenedor.
	 * @param pm
	 * @param nuevaCantidad
	 * @param id
	 * @return
	 */
	public long cambiarCapacidadOcupada(PersistenceManager pm, int nuevaCantidad, long id)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaContenedor() + " SET capacidadOcupada = ? WHERE id = ?");
		q.setParameters(nuevaCantidad, id);
		return (long) q.executeUnique();
	}
	
}
