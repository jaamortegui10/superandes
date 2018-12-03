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
	
	public final static String ESTANTE = "estante";
	public final static String BODEGA = "bodega";
	public final static String CARRITO = "carrito";
	public final static String FACTURA = "factura";
	
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
	public long agregarTupla(PersistenceManager pm, long id, String codigoBarras, String estado, long idTipo, long idAbstracto, double precio)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoFisico() + "(id, codigoBarras, estado, idTipo, idAbstracto, precio) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(id, codigoBarras, estado, idTipo, idAbstracto, precio);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para cambiar el idContenedor de una tupla.
	 * @param pm
	 * @param id
	 * @param idContenedor
	 * @return
	 */
	public long cambiarEstado(PersistenceManager pm, long id, String estado)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaProductoFisico() + " SET estado = ? WHERE id = ?");
		q.setParameters(estado, id);
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
	
	/**
	 * Método que retorna todos los productos pertenecientes a un carrito.
	 * @param pm
	 * @param carritoId
	 * @return
	 */
	public List<ProductoFisico> darProductosPorCarritoId(PersistenceManager pm, long carritoId)
	{
		String sql = "SELECT * FROM " + psa.darTablaProductoFisico() + " pf"
				+ " INNER JOIN " + psa.darTablaProductoCarrito() + " pc"
				+ " ON pf.id = pc.idProductoFisico"
				+ " WHERE pc.idCarrito = ?";
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFisico() + " WHERE idCarrito = ?");
		q.setResultClass(ProductoFisico.class);
		q.setParameters(carritoId);
		return (List<ProductoFisico>) q.executeList();
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
