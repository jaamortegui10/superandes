package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoPedido;

public class SQLProductoPedido {
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
	public SQLProductoPedido(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método para agregar nueva tupla.
	 * @param pm
	 * @param idPedido
	 * @param idProductoOfrecido
	 * @param cantidad
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long idPedido, long idProductoOfrecido, int cantidad)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoPedido() + "(idPedido, idProductoOfrecido, cantidad) values (?, ?, ?)");
		q.setParameters(idPedido, idProductoOfrecido, cantidad);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna todas las tuplas con el idPedido dado por parámetro.
	 * @param pm
	 * @param idPedido
	 * @return
	 */
	public List<ProductoPedido> darProductosPorPedidoId(PersistenceManager pm, long idPedido)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoPedido() + "WHERE idPedido = ?");
		q.setResultClass(ProductoPedido.class);
		q.setParameters(idPedido);
		return (List<ProductoPedido>) q.executeList();
	}
}
