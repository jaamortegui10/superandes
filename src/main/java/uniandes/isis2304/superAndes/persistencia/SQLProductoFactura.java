package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductoFactura;

public class SQLProductoFactura {
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
	public SQLProductoFactura(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarTupla(PersistenceManager pm, long idFactura, long idProductoFisico)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProductoFactura() + "(idFactura, idProductoFisico) values (?, ?)");
		q.setParameters(idFactura, idProductoFisico);
		
		return (long) q.executeUnique();
	}
	
	public List<ProductoFactura> darProductosPorIdFactura(PersistenceManager pm, long idFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProductoFactura() + "WHERE idFactura = ?");
		q.setResultClass(ProductoFactura.class);
		q.setParameters(idFactura);
		return (List<ProductoFactura>) q.executeList();
	}
	
	
}
