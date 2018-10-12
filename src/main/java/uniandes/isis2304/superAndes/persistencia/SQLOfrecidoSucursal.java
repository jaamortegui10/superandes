package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OfrecidoSucursal;

public class SQLOfrecidoSucursal {
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
	public SQLOfrecidoSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param idAbstracto
	 * @param idSucursal
	 * @param precio
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idAbstracto, long idSucursal, double precio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO "+psa.darTablaOfrecidoSucursal() + "(idAbstracto, idSucursal, precio) values (?, ?, ?)");
		q.setParameters(idAbstracto, idSucursal, precio);
		return (long) q.executeUnique();
		
	}
	
	/**
	 * Método que retorna las tuplas con el idSucursal dado por parámetro.
	 * @param pm
	 * @param idSucursal
	 * @return
	 */
	public List<OfrecidoSucursal> darOfrecidosPorIdSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaOfrecidoSucursal() + " WHERE idSucursal = ?");
		q.setResultClass(OfrecidoSucursal.class);
		q.setParameters(idSucursal);
		return (List<OfrecidoSucursal>) q.executeList();
	}
	
	/**
	 * Método que retorna el OfrecidoSucursal con el idAbstracto, idSucursal dados por parámetros.
	 * @param pm
	 * @param idAbstracto
	 * @param idSucursal
	 * @return
	 */
	public OfrecidoSucursal darOfrecidoPorIdAbstractoIdSucursal(PersistenceManager pm, long idAbstracto, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaOfrecidoSucursal() + " WHERE idAbstracto = ? + idSucursal = ?");
		q.setResultClass(OfrecidoSucursal.class);
		q.setParameters(idAbstracto, idSucursal);
		return (OfrecidoSucursal) q.executeUnique();
	}
	
	/**
	 * Método que cambia el precio de una tupla con el idAbstracto, idSucursal dados opr parámetros.
	 * @param pm
	 * @param idAbstracto
	 * @param idSucursal
	 * @param nuevoPrecio
	 * @return
	 */
	public long cambiarPrecio(PersistenceManager pm, long idAbstracto, long idSucursal, double nuevoPrecio)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaOfrecidoSucursal() + " SET precio = ? WHERE idAbstracto = ? AND idSucursal = ?");
		q.setParameters(nuevoPrecio, idAbstracto, idSucursal);
		return (long) q.executeUnique();
	}
}
