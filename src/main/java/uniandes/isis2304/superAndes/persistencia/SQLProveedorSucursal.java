package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProveedorSucursal;

public class SQLProveedorSucursal {
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
	public SQLProveedorSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param idSucursal
	 * @param nitProveedor
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idSucursal, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedorSucursal() + "(idSucursal, NITProveedor) values (?, ?)");
		q.setParameters(idSucursal, nitProveedor);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna las tuplas con el idSucursal dado por parámetro.
	 * @param pm
	 * @param idSucursal
	 * @return
	 */
	public List<ProveedorSucursal> darProveedoresSucursalesPorIdSucursal(PersistenceManager pm, long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedorSucursal() + " WHERE idSucursal = ?");
		q.setResultClass(ProveedorSucursal.class);
		q.setParameters(idSucursal);
		return (List<ProveedorSucursal>) q.executeList();
	}
}
