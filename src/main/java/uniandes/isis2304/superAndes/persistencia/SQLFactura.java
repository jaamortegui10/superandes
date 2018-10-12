package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Factura;

public class SQLFactura {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param psa
	 */
	public SQLFactura(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo para agregar una factura.
	 * @param pm
	 * @param id
	 * @param idUser
	 * @param costoTotal
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idUser, double costoTotal)
	{
		
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaFactura() + "(id, idUser, costoTotal) values (?, ?, ?)");
		q.setParameters(id, idUser, costoTotal);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo para retornar la factura con el id dado por par�metro.
	 * @param pm
	 * @param id
	 * @return
	 */
	public Factura darFacturaPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura() + " WHERE id = ?");
		q.setResultClass(Factura.class);
		q.setParameters(id);
		return (Factura) q.executeUnique();
	}
	
	/**
	 * M�tod para retornar las facturas con el idUser dado por par�metro.
	 * @param pm
	 * @param idUser
	 * @return
	 */
	public List<Factura> darFacturasPorIdUser(PersistenceManager pm, long idUser)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaFactura() + " WHERE idUser = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idUser);
		return (List<Factura>) q.executeList();
	}
	
	/**
	 * M�todo para cambiar el costo total de una factura.
	 * @param pm
	 * @param costoTotal
	 * @param id
	 * @return
	 */
	public long cambiarCostoTotal(PersistenceManager pm, double costoTotal, long id)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaFactura() + " SET costoTotal = ? WHERE id = ?");
		q.setParameters(costoTotal, id);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo para eliminar una factura con el id dado por par�metro.
	 * @param pm
	 * @param id
	 * @return
	 */
	public long borrarFactura(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
}
