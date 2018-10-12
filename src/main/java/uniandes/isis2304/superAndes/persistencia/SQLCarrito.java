package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Carrito;

public class SQLCarrito {
	
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
	 * M�todo constructor de la clase.
	 * @param psa
	 */
	public SQLCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que retorna todas las tuplas existentes en la relaci�n Carrito.
	 * @param pm
	 * @return
	 */
	public List<Carrito> darCarritos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarrito() );
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}
	
	/**
	 * M�todo que agrega una nueva tupla a la relaci�n Carrito.
	 * @param pm
	 * @param id
	 * @param idUser
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idUser)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCarrito() +"(id, idUser) values (?, ?)");
		q.setParameters(id, idUser);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que elimina una tupla con el id dado por par�metro de la relaci�n Carrito.
	 * @param pm
	 * @param id
	 * @return
	 */
	public long eliminarCarrito(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCarrito() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
}
