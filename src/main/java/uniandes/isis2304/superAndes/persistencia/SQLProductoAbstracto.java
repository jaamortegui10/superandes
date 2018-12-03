package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoAbstracto;

public class SQLProductoAbstracto {
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
	public SQLProductoAbstracto(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Agrega una nueva tupla.
	 * @param pm
	 * @param id
	 * @param nombre
	 * @param tipo
	 * @param unidadMedida
	 * @param categoria
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, String nombre, String unidadMedida, int cantidadMedida, long idTipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoAbstracto() + "(id, nombre, unidadMedida, cantidadMedida, idTipo) values (?, ?, ?, ?, ?)");
		q.setParameters(id, nombre, unidadMedida, cantidadMedida, idTipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Retorna todas las tuplas.
	 * @param pm
	 * @return
	 */
	public List<ProductoAbstracto> darProductosAbstractos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoAbstracto() );
		q.setResultClass(ProductoAbstracto.class);
		return (List<ProductoAbstracto>) q.executeList();
	}
	
	/**
	 * Retorna la tupla con el id dado por parámetro.
	 * @param pm
	 * @param id
	 * @return
	 */
	public ProductoAbstracto darProductoPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoAbstracto() + " WHERE id = ?");
		q.setResultClass(ProductoAbstracto.class);
		q.setParameters(id);
		return (ProductoAbstracto) q.executeUnique();
	}
	
	/**
	public ProductoAbstracto darProductoPorIdProductoFisico(PersistenceManager pm, long productoFisicoId)
	{
		String sql = "Select pa.*"  
				+ " From ProductoAbstracto pa Inner Join OfrecidoSucursal os"
				+ " On pa.id = os.idAbstracto"
				+ " Inner Join ProductoFisico pf"
				+ " On os.id = pf.idOfrecido"
				+ " Where pf.id = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoAbstracto.class);
		q.setParameters(productoFisicoId);
		return (ProductoAbstracto) q.executeUnique();
	}
	*/
	
	/**
	 * Elimina una tupla.
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarProducto(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoAbstracto() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
}
