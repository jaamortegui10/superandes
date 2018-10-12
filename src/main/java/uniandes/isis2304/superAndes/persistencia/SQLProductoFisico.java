package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoFisico;

public class SQLProductoFisico {
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
	public SQLProductoFisico(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarProductoFisico(PersistenceManager pm, long id, long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoFisico() + "(id, idOfrecido, cantidadMedida, codigoBarras, idContenedor) values (?, ?, ?, ?, ?)");
		q.setParameters(id, idOfrecido, cantidadMedida, codigoBarras, idContenedor);
		return (long) q.executeUnique();
	}
	
	public long cambiarIdContenedor(PersistenceManager pm, long id, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + "set idContenedor = ? WHERE id = ?");
		q.setParameters(idContenedor, id);
		return (long) q.executeUnique();
	}
	
	public long cambiardeIdContenedorAIdCarrito(PersistenceManager pm, long id, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + "set idContenedor = null, idCarrito = ? WHERE id = ?");
		q.setParameters(idCarrito, id);
		return (long) q.executeUnique();
	}
	
	public List<ProductoFisico> darProductosFisicosPorIdContenedor(PersistenceManager pm, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE idContenedor = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(idContenedor);
		return (List<ProductoFisico>) q.executeUnique();
	}
}
