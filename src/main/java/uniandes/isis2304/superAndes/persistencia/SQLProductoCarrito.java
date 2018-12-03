package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLProductoCarrito {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private static final String SQL = PersistenciaSuperAndes.SQL;
	
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
	public SQLProductoCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que agrega una nueva tupla a la relaci�n Contenedor.
	 * @param pm
	 * @param id
	 * @param sucursalId
	 * @param tipo
	 * @param capacidad
	 * @param capacidadOcupada
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idCarrito, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoCarrito() + "(id, idSucursal, tipo, capacidad, capacidadOcupada, categoria) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idCarrito, idProductoFisico);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que elimina una tupla.
	 * @param pm
	 * @param idProductoFisico
	 * @return
	 */
	public  long eliminarTupla(PersistenceManager pm, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoCarrito() + " WHERE idProductoFisico = ?");
		q.setParameters(idProductoFisico);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que elimina una tupla seg�n el id del carrito dado.
	 * @param pm
	 * @param idCarrito
	 * @return
	 */
	public  long eliminarTuplaPorCarrito(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoCarrito() + " WHERE idCarrito = ?");
		q.setParameters(idCarrito);
		return (long) q.executeUnique();
	}
}
