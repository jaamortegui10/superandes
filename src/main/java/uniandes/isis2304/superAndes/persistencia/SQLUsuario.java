package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Usuario;

public class SQLUsuario {
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
	public SQLUsuario(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega nueva tupla.
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
		System.out.println("---------------->>>> Se está agregando un usuario.");
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaUser() + "(id, password, nombre, correo, tipo) values (?, ?, ?, ?, ?)");
		q.setParameters(id, password, nombre, correo, tipo);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna todas las tuplas.
	 * @param pm
	 * @return
	 */
	public List<Usuario> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaUser());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	
}
