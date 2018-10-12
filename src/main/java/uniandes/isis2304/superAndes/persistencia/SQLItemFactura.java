package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ItemFactura;

public class SQLItemFactura {
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
	public SQLItemFactura(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarTupla(PersistenceManager pm, long idFactura, long idProductoOfrecido, int cantidad, double costo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaItemFactura() + "(idFactura, idProductoOfrecido, cantidad, costo) values (?, ?, ?, ?)");
		q.setParameters(idFactura, idProductoOfrecido, cantidad, costo);
		
		return (long) q.executeUnique();
	}
	
	public List<ItemFactura> darItemsFacturaPorIdFactura(PersistenceManager pm, long idFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaItemFactura() + "WHERE idFactura = ?");
		q.setResultClass(ItemFactura.class);
		q.setParameters(idFactura);
		return (List<ItemFactura>) q.executeList();
	}
	
	
}
