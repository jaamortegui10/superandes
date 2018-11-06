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
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + " set idContenedor = null, idCarrito = ? WHERE id = ?");
		q.setParameters(idCarrito, id);
		return (long) q.executeUnique();
	}
	public long cambiardeIdCarritoAIdContenedor(PersistenceManager pm, long id, long idContenedor)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + " set idContenedor = ?, idCarrito = -1 WHERE id = ?");
		q.setParameters(idContenedor, id);
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
		return (List<ProductoFisico>) q.executeList();
	}
	
	public ProductoFisico darProductoFisicoPorId(PersistenceManager pm, long id)
	{
		System.out.println("En dar producto físico: id: " + id);
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE id = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(id);
		return (ProductoFisico) q.executeUnique();
	}
	
	
	public List<ProductoFisico> darProductosPorCarritoId(PersistenceManager pm, long carritoId)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE idCarrito = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(carritoId);
		return (List<ProductoFisico>) q.executeList();
	}
	
	public long cambiarProductoACarrito(PersistenceManager pm, long productoFisicoId, long carritoId)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + " SET idCarrito = ?, idContenedor = -1 WHERE id = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(carritoId, productoFisicoId);
		return (long) q.executeUnique();
	}
	
	public List<ProductoFisico> darProductosDeCarritosAbandonadosPorSucursalId(PersistenceManager pm, long idSucursal)
	{
		String sql = "select p.*"
				+" From Carrito c Inner Join ProductoFisico p"
				+" On c.id = p.idCarrito"
				+" Inner Join OfrecidoSucursal o"
				+" On p.idOfrecido = o.id"
				+" Inner Join Sucursal s"
				+" On o.idSucursal = s.id"
				+" Inner Join ProductoAbstracto a"
				+" On  o.idAbstracto = a.id"
				+" Where c.idUser = 2001 And s.id= ?"
				+" order by a.tipo;";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoFisico.class);
		q.setParameters(idSucursal);
		return (List<ProductoFisico>) q.executeList();
		
	}
	
	public List<ProductoFisico> darProductosDeCarritosDevueltos(PersistenceManager pm)
	{
		String sql = "Select pf.*" 
				+" From " + psa.darTablaProductoFisico() +" pf Inner Join Carrito c"
				+" On pf.idCarrito = c.id"
				+" Where c.idUser = null";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoFisico.class);
		return (List<ProductoFisico>) q.executeList();
	}
}
