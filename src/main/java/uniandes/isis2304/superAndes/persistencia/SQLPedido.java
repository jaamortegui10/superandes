package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Pedido;

public class SQLPedido {
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
	public SQLPedido(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	/**
	 * Agrega una tupla.
	 * @param pm
	 * @param id
	 * @param idSucursal
	 * @param nitProveedor
	 * @param precio
	 * @param estado
	 * @param fechaEntrega
	 * @return
	 */
	public long agregarPedido(PersistenceManager pm, long id, long idSucursal, int nitProveedor, double precio, String estado, String fechaEntrega)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPedido() + "(id, idSucursal, NITProveedor, precio, estado, fechaEntrega) values (?, ?, ?, ?, ?, ?)"); 
		q.setParameters(id, idSucursal, nitProveedor, precio, estado, fechaEntrega);
		return (long) q.executeUnique();
		
	}
	/**
	 * Retorna la tupla con el id dado por parámetro
	 * @param pm
	 * @param id
	 * @return
	 */
	public Pedido darPedidoPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedido() + " WHERE id = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(id);
		return (Pedido) q.executeUnique();
	}
	
	/**
	 * Retorna las tuplas con el idSucural dado por parámetro.
	 * @param pm
	 * @param idSucursal
	 * @return
	 */
	public List<Pedido> darPedidosPorIdSucursal(PersistenceManager pm, Long idSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedido() + " WHERE idSucursal = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(idSucursal);
		return (List<Pedido>) q.executeList();
	}
	
	/**
	 * Retorna las tuplas con el nitProveedor dado por parámetro.
	 * @param pm
	 * @param nitProveedor
	 * @return
	 */
	public List<Pedido> darPedidosPorNitProveedor(PersistenceManager pm, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedido() + " WHERE NITProveedor = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(nitProveedor);
		return (List<Pedido>) q.executeList();
	}
	
	/**
	 * Retorna las tuplas con el idSucursal, nitProveedor dados por parámetro.
	 * @param pm
	 * @param idSucursal
	 * @param nitProveedor
	 * @return
	 */
	public List<Pedido> darPedidosPorIdSucursalNitProveedor(PersistenceManager pm, long idSucursal, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedido() + " WHERE idSucursal = ? AND NITProveedor = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(idSucursal, nitProveedor);
		return (List<Pedido>) q.executeList();
	}
	
	/**
	 * Retorna las tuplas con el idSucursal, nitProveedor, estado dados por parámetros.
	 * @param pm
	 * @param idSucursal
	 * @param nitProveedor
	 * @param estado
	 * @return
	 */
	public List<Pedido> darPedidosPorIdSucursalNitProveedorPorEstado(PersistenceManager pm, long idSucursal, int nitProveedor, String estado)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPedido() + " WHERE idSucursal = ? AND NITProveedor = ? AND estado = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(idSucursal, nitProveedor, estado);
		return (List<Pedido>) q.executeList();
	}
	
	/**
	 * Actualiza la tupla con el id dado por parámetro en el estado, calidad, calificacion dados por parámetro
	 * @param pm
	 * @param id
	 * @param estado
	 * @param calidad
	 * @param calificacion
	 * @return
	 */
	public long actualizarEstadoCalidadCalificacionPorId(PersistenceManager pm, long id, String estado, String calidad, int calificacion)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaPedido() + " SET estado = ?, calidad = ?, calificacion = ? WHERE id = ?");
		q.setResultClass(Pedido.class);
		q.setParameters(estado, calidad, calificacion, id);
		return (long) q.executeUnique();
	}
	
}
