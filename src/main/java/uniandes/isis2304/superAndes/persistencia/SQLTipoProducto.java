package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLTipoProducto {
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
	/**
	 * Método constructor de la clase.
	 * @param psa
	 */
	public SQLTipoProducto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param id
	 * @param idSucursal
	 * @param descripcion
	 * @param tipo
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, String nombre, String categoria)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaTipoProducto() + "(id, nombre, categoria) values (?, ?, ?)");
		q.setParameters(id, nombre, categoria);
		return (long) q.executeUnique();
	}
}
