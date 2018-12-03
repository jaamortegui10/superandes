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
	 * 			Métodos
	 *****************************************************************/
	
	/**
	 * Método constructor de la clase.
	 * @param psa
	 */
	public SQLCarrito(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que retorna todas las tuplas existentes en la relación Carrito.
	 * @param pm
	 * @return
	 */
	public List<Carrito> darCarritos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarrito() );
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}
	
	public Carrito darCarritoPorUsuarioId(PersistenceManager pm, long usuarioId)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarrito() + " WHERE idUser = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(usuarioId);
		
		System.out.println("-------------------------------->En buscar carrito por id User: idUser:" + usuarioId );
		return (Carrito) q.executeUnique();
	}
	
	/**
	 * Método que agrega una nueva tupla a la relación Carrito.
	 * @param pm
	 * @param id
	 * @param idUser
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, long idSucursal, String ocupado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCarrito() +"(id, idSucursal, ocupado) values (?, ?, ?)");
		q.setParameters(id, idSucursal, ocupado);
		return (long) q.executeUnique();
	}
	
	public List<Carrito> darCarritosSolitarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCarrito() +" WHERE ocupado = 'no'");
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}
	
	/**
	 * Método que elimina una tupla con el id dado por parámetro de la relación Carrito.
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
	
	public long devolver(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCarrito() + " SET ocupado = 'no' WHERE id = ?");
		q.setParameters(idCarrito);
		return (long) q.executeUnique();
	
	}
}
