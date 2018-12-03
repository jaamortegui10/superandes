package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPaqueteProductos;

public class SQLPromPaqueteProductos {
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
	public SQLPromPaqueteProductos(PersistenciaSuperAndes psa)
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
	public long agregarTupla(PersistenceManager pm, long id, double precio, long idProductoOfrecido1, long idProductoOfrecido2, long idSucursal, String fecha_inicio, String fecha_fin, String descripcion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromPaqueteProductos() + "(id, precio, idProductoOfrecido1, idProductoOfrecido2, idSucursal, fecha_inicio, fecha_fin, descripcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(id, precio, idProductoOfrecido1, idProductoOfrecido2, idSucursal, fecha_inicio, fecha_fin, descripcion);
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
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromPaqueteProductos() + " WHERE idPromocion = ?");
		q.setResultClass(PromocionPaqueteProductos.class);
		q.setParameters(idPromocion);
		return (PromocionPaqueteProductos) q.executeUnique();
	}
}
