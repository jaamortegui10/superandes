package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OfrecidoProveedor;

public class SQLOfrecidoProveedor {
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
	public SQLOfrecidoProveedor(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que agrega una nueva tupla a la relaci�n OfrecidoProveedor
	 * @param pm
	 * @param idAbstracto
	 * @param precio
	 * @param nitProveedor
	 * @return
	 */
	public long agregarOfrecidoProveedor(PersistenceManager pm, long idAbstracto, double precio, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaOfrecidoProveedor() + "(idAbstracto, precio, NITProveedor) values (?, ?, ?)");
		q.setParameters(idAbstracto, precio, nitProveedor);
		return (long) q.executeUnique();
	}
	
	/**
	 * Retorna las tuplas con el nitProveedor dado por par�metro.
	 * @param pm
	 * @param nitProveedor
	 * @return
	 */
	public List<OfrecidoProveedor> darOfrecidosPorNitProveedor(PersistenceManager pm, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaOfrecidoProveedor() + "WHERE NITProveedor = ?");
		q.setResultClass(OfrecidoProveedor.class);
		q.setParameters(nitProveedor);
		return (List<OfrecidoProveedor>) q.executeList();
	}
	
	/**
	 * Retorna la tupla con el idAbstracto, nitProveedor dados por par�metros.
	 * @param pm
	 * @param idAbstracto
	 * @param nitProveedor
	 * @return
	 */
	public OfrecidoProveedor darOfrecidoPorIdAbstractoNitProveedor(PersistenceManager pm, long idAbstracto, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * " + psa.darTablaOfrecidoProveedor() + " WHERE idAbstracto = ? AND NITProveedor = ?");
		q.setResultClass(OfrecidoProveedor.class);
		q.setParameters(idAbstracto, nitProveedor);
		return (OfrecidoProveedor) q.executeUnique();
	}
	
	/**
	 * Cambia el costo de la tupla con el idAbstracto, nitProveedor dados por par�metros, por el nuevoPrecio dado por par�metro.
	 * @param pm
	 * @param idAbstracto
	 * @param nitProveedor
	 * @param nuevoPrecio
	 * @return
	 */
	public long cambiarCostoOfrecidoProveedor(PersistenceManager pm, long idAbstracto, int nitProveedor, double nuevoPrecio)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaOfrecidoProveedor() + " SET precio = ? WHERE idAbstracto = ? AND NITProveedor = ?");
		q.setParameters(nuevoPrecio, idAbstracto, nitProveedor);
		return (long) q.executeUnique();
	}
	
}
