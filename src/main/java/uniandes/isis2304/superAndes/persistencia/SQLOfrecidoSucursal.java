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
	 * 			M�todos
	 *****************************************************************/
	/**
	 * M�todo constructor de la clase.
	 * @param psa
	 */
	public SQLOfrecidoSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que agrega una nueva tupla.
	 * @param pm
	 * @param idAbstracto
	 * @param idSucursal
	 * @param precio
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idAbstracto, long idSucursal, double precio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO "+psa.darTablaOfrecidoSucursal() + "(id, idAbstracto, idSucursal, precio) values (?, ?, ?, ?)");
		q.setParameters(id, idAbstracto, idSucursal, precio);
		return (long) q.executeUnique();
		
	}
	
	/**
	 * M�todo que retorna las tuplas con el idSucursal dado por par�metro.
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
	 * M�todo que retorna el OfrecidoSucursal con el idAbstracto, idSucursal dados por par�metros.
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
	
	public OfrecidoSucursal darOfrecidoPorIdFisico(PersistenceManager pm, long idProductoFisico)
	{
		String sql = "Select os.*"
				+" From OfrecidoSucursal os Inner Join ProductoFisico pf"
				+" On os.id = pf.idOfrecido"
				+" Where pf.id = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(OfrecidoSucursal.class);
		q.setParameters(idProductoFisico);
		return (OfrecidoSucursal) q.executeUnique();
	}
	
	/**
	 * M�todo que cambia el precio de una tupla con el idAbstracto, idSucursal dados opr par�metros.
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
