package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPorCantidadOUnidades;

public class SQLPromocionPorCantidadOUnidad {
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
	public SQLPromocionPorCantidadOUnidad(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega nueva tupla.
	 * @param pm
	 * @param idOfrecido
	 * @param idPromocion
	 * @param cantidadOUnidadesPagadas
	 * @param cantidadOUnidadesCompradas
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idOfrecido, long idPromocion, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromocionPorCantidadOUnidad() + "(idOfrecido, idPromocion, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas) values (?, ?, ?, ?)");
		q.setParameters(idOfrecido, idPromocion, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna la tupla con el idPromocion dado por parámetro.
	 * @param pm
	 * @param idPromocion
	 * @return
	 */
	public PromocionPorCantidadOUnidades darPromocionPorIdPromocion(PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocionPorCantidadOUnidad() + " WHERE idPromocion = ?");
		q.setResultClass(PromocionPorCantidadOUnidades.class);
		q.setParameters(idPromocion);
		return (PromocionPorCantidadOUnidades) q.executeUnique();
	}
}
