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
	
	/**
	 * Método para agregar tupla nueva
	 * @param pm
	 * @param id
	 * @param idOfrecido
	 * @param cantidadMedida
	 * @param codigoBarras
	 * @param idContenedor
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoFisico() + "(id, idOfrecido, cantidadMedida, codigoBarras, idContenedor) values (?, ?, ?, ?, ?)");
		q.setParameters(id, idOfrecido, cantidadMedida, codigoBarras, idContenedor);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para cambiar el idContenedor de una tupla.
	 * @param pm
	 * @param id
	 * @param idContenedor
	 * @return
	 */
	public long cambiarIdContenedor(PersistenceManager pm, long id, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + "set idContenedor = ? WHERE id = ?");
		q.setParameters(idContenedor, id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para transformar en una tupla su idContenedor = null y el idCarrito al dado por parámetro.
	 * @param pm
	 * @param id
	 * @param idCarrito
	 * @return
	 */
	public long cambiardeIdContenedorAIdCarrito(PersistenceManager pm, long id, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + "set idContenedor = null, idCarrito = ? WHERE id = ?");
		q.setParameters(idCarrito, id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Retorna todas las tuplas con el idContenedor dado por parámetro.
	 * @param pm
	 * @param idContenedor
	 * @return
	 */
	public List<ProductoFisico> darProductosFisicosPorIdContenedor(PersistenceManager pm, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE idContenedor = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(idContenedor);
		return (List<ProductoFisico>) q.executeUnique();
	}
	
	public ProductoFisico darProductoFisicoPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE id = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(id);
		return (ProductoFisico) q.executeUnique();
	}
	
}
