package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPorCantidadOUnidad;

public class SQLPromPorCantidadOUnidad {
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
	public SQLPromPorCantidadOUnidad(PersistenciaSuperAndes psa)
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
	public long agregarTupla(PersistenceManager pm, long id, long idOfrecido, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas, long idSucursal, String fecha_inicio, String fecha_fin, String descripcion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromPorCantidadOUnidad() + "(id, idOfrecido, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas, idSucursal, fecha_inicio, fecha_fin, descripcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idOfrecido, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas, idSucursal, fecha_inicio, fecha_fin, descripcion );
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna la tupla con el idPromocion dado por parámetro.
	 * @param pm
	 * @param idPromocion
	 * @return
	 */
	public PromocionPorCantidadOUnidad darPromocionPorIdPromocion(PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromPorCantidadOUnidad() + " WHERE idPromocion = ?");
		q.setResultClass(PromocionPorCantidadOUnidad.class);
		q.setParameters(idPromocion);
		return (PromocionPorCantidadOUnidad) q.executeUnique();
	}
}
