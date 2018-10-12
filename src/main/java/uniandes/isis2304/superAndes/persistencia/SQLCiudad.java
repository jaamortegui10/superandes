package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Ciudad;

public class SQLCiudad {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	public SQLCiudad(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método para agregar una tupla a relación Ciudad.
	 * @param pm
	 * @param id
	 * @param nombre
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, String nombre)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCiudad() + "(id, nombre) values (?, ?)");
		q.setParameters(id, nombre);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para retornar todas las tuplas de la relación Ciudad.
	 * @param pm
	 * @return
	 */
	public List<Ciudad> darCiudades(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCiudad());
		q.setResultClass(Ciudad.class);
		return (List<Ciudad>) q.executeList();
	}
	
	/**
	 * Método para retornar una tupla con el id dado por parámetro de la relación Ciudad.
	 * @param pm
	 * @param id
	 * @return
	 */
	public Ciudad darCiudadPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCiudad() + " WHERE id = ?");
		q.setResultClass(Ciudad.class);
		q.setParameters(id);
		return (Ciudad) q.executeUnique();
	}
	
	/**
	 * Método para eliminar la tupla con el id dado por parámetro, en la relación Ciudad.
	 * @param pm
	 * @param id
	 * @return
	 */
	public long borrarCiudad(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCiudad() + "WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
}
