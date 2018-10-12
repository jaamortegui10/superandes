package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

public class SQLPromocion {
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
	public SQLPromocion(PersistenciaSuperAndes psa)
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
	public long agregarTupla(PersistenceManager pm, long id, long idSucursal, String descripcion, String tipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromocion() + "(id, idSucursal, descripcion, tipo) values (?, ?, ?, ?)");
		q.setParameters(id, idSucursal, descripcion, tipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna todas las tuplas.
	 * @param pm
	 * @return
	 */
	public List<Promocion> darPromociones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocion() );
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * Método que retorna todas las tuplas con el idSucursal dado por paráemtro.
	 * @param pm
	 * @param idSucursal
	 * @return
	 */
	public List<Promocion> darPromocionesPorIdSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocion() + " WHERE idSucursal = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(idSucursal);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * Método que retorna la tupla con el id dado por parámetro.
	 * @param pm
	 * @param id
	 * @return
	 */
	public Promocion darPromocionPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocion() + " WHERE id = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(id);
		return (Promocion) q.executeUnique();
	}
}
