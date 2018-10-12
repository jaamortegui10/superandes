package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPaqueteProductos;

public class SQLPromocionPaqueteProductos {
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
	public SQLPromocionPaqueteProductos(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param idPromocion
	 * @param precio
	 * @param idProductoOfrecido1
	 * @param idProductoOfrecido2
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idPromocion, double precio, long idProductoOfrecido1, long idProductoOfrecido2)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromocionPaqueteProductos() + "(idPromocion, precio, idProductoOfrecido1, idProductoOfrecido2) values (?, ?, ?, ?)");
		q.setParameters(idPromocion, precio, idProductoOfrecido1, idProductoOfrecido2);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Método que retorna el paquete de productos con el idPromocion dado por parámetro.
	 * @param pm
	 * @param idPromocion
	 * @return
	 */
	public PromocionPaqueteProductos darPromocionPorIdPromocion(PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromocionPaqueteProductos() + " WHERE idPromocion = ?");
		q.setResultClass(PromocionPaqueteProductos.class);
		q.setParameters(idPromocion);
		return (PromocionPaqueteProductos) q.executeUnique();
	}
}
