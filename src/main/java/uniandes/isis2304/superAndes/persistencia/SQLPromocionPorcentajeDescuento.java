package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPorcentajeDescuento;

public class SQLPromocionPorcentajeDescuento {
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
	public SQLPromocionPorcentajeDescuento(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param idProductoOfrecido
	 * @param idPromocion
	 * @param porcentajeDescuento
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idProductoOfrecido, long idPromocion, int porcentajeDescuento)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromocionPorcentajeDescuento() + "(idProductoOfrecido, idPromocion, porcentajeDescuento) values (?, ?, ?)");
		q.setParameters(idProductoOfrecido, idPromocion, porcentajeDescuento);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna la promoción con el idPromocion dado por parámetro.
	 * @param pm
	 * @param idPromocion
	 * @return
	 */
	public PromocionPorcentajeDescuento darPromocionPorIdPromocion(PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocionPorcentajeDescuento() + " WHERE idPRomocion = ?");
		q.setResultClass(PromocionPorcentajeDescuento.class);
		q.setParameters(idPromocion);
		return (PromocionPorcentajeDescuento) q.executeUnique();
	}
}
