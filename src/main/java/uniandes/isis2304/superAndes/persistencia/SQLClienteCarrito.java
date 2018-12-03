package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ClienteCarrito;

public class SQLClienteCarrito {
	
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
	public SQLClienteCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	public long agregarTupla(PersistenceManager pm, long docCliente, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaClienteCarrito() + "(docCliente, idCarrito) values (?, ?)");
		q.setParameters(docCliente, idCarrito);
		return (long) q.executeUnique();
	}
	
	public long getIdClientePorIdCarrito(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaClienteCarrito() + " WHERE idCarrito = ?");
		q.setParameters(idCarrito);
		q.setResultClass(ClienteCarrito.class);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna el id del carrito dado el documento del cliente.
	 * @param pm
	 * @param docCliente
	 * @return
	 */
	public long getIdCarritoPorDocCliente(PersistenceManager pm, long docCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaClienteCarrito() + " WHERE docCliente = ?");
		q.setParameters(docCliente);
		q.setResultClass(ClienteCarrito.class);
		return (long) q.executeUnique();
	}
	
	
}
