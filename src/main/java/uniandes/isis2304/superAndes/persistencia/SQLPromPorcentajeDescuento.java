package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromocionPorcentajeDescuento;

public class SQLPromPorcentajeDescuento {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * M�todo constructor de la clase.
	 * @param psa
	 */
	public SQLPromPorcentajeDescuento(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que agrega una nueva tupla.
	 * @param pm
	 * @param idProductoOfrecido
	 * @param idPromocion
	 * @param porcentajeDescuento
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idProductoOfrecido,  int porcentajeDescuento, long idSucursal, String fecha_inicio, String fecha_fin, String descripcion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPromPorcentajeDescuento() + "(id, idProductoOfrecido, porcentajeDescuento, idSucursal, fecha_inicio, fecha_fin, descripcion) values (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(id, idProductoOfrecido, porcentajeDescuento, idSucursal, fecha_inicio, fecha_fin, descripcion);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que retorna la promoci�n con el idPromocion dado por par�metro.
	 * @param pm
	 * @param idPromocion
	 * @return
	 */
	public PromocionPorcentajeDescuento darPromocionPorIdPromocion(PersistenceManager pm, long idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPromPorcentajeDescuento() + " WHERE idPRomocion = ?");
		q.setResultClass(PromocionPorcentajeDescuento.class);
		q.setParameters(idPromocion);
		return (PromocionPorcentajeDescuento) q.executeUnique();
	}
}
