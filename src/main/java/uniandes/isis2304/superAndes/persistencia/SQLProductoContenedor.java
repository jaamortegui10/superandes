package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoContenedor;

public class SQLProductoContenedor {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private static final String SQL = PersistenciaSuperAndes.SQL;
	
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
	public SQLProductoContenedor(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una tupla
	 * @param pm
	 * @param idContenedor
	 * @param idProductoFisico
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idContenedor, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoContenedor() + "(idContenedor, idProductoFisico) values (?, ?)");
		q.setParameters(idContenedor, idProductoFisico);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Método que elimina una tupla.
	 * @param pm
	 * @param idContenedor
	 * @param idProductoFisico
	 * @return
	 */
	public long eliminarTupla(PersistenceManager pm, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoContenedor() + " WHERE idProductoFisico = ?");
		q.setParameters( idProductoFisico);
		return (long) q.executeUnique();
	}
	
	public long darIdContenedorPorIdProducto(PersistenceManager pm, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "SELECT idContenedor FROM " + psa.darTablaProductoContenedor() + " WHERE idProductoFisico = ?");
		q.setParameters( idProductoFisico);
		return (long) q.executeUnique();
	}
	
	public List<ProductoContenedor> darProductosPorIdContenedor(PersistenceManager pm, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoContenedor() + " WHERE idContenedor = ?");
		q.setResultClass(ProductoContenedor.class);
		q.setParameters( idContenedor);
		return (List<ProductoContenedor>) q.executeList();
	}
}
