package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.User;

public class SQLUser {
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
	public SQLUser(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que agrega nueva tupla.
	 * @param pm
	 * @param id
	 * @param password
	 * @param nombre
	 * @param correo
	 * @param tipo
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, String password, String nombre, String correo, String tipo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaUser() + "(id, password, nombre, correo, tipo) values (?, ?, ?, ?, ?)");
		q.setParameters(id, password, nombre, correo, tipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que retorna todas las tuplas.
	 * @param pm
	 * @return
	 */
	public List<User> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaUser());
		q.setResultClass(User.class);
		return (List<User>) q.executeList();
	}
	
	
}
