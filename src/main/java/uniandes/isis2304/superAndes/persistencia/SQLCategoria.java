package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;

public class SQLCategoria {
	
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	
	/**
	 * Constructor 
	 * @param psa
	 */
	public SQLCategoria(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * M�todo que retorna todas las categor�as existentes en la base de datos.
	 * @param pm
	 * @return
	 */
	public List<Categoria> darCategorias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria  ());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}
	
	/**
	 * M�todo que agrega una nueva categor�a a la relaci�n Categoria.
	 * @param pm
	 * @param nombre
	 * @param caracteristicas
	 * @param almacenamiento
	 * @param manejo
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, String nombre, String caracteristicas, String manejo)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCategoria() + "(nombre, caracteristicas, manejo) values (?, ?, ?)");
		q.setParameters(nombre, caracteristicas, manejo);
		return (long) q.executeUnique();
	}

	/**
	 * M�todo que elimina una tupla de la relaci�n Categoria con el nombre dado por par�metro.
	 * @param pm
	 * @param nombre
	 * @return
	 */
	public long eliminarCategoria(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que retorna la informaci�n correspondiente a la tupla de la categor�a con el nombre dado por par�metro.
	 * @param pm
	 * @param nombre
	 * @return
	 */
	public Categoria darCategoriaPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCategoria() + " WHERE nombre = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(nombre);
		return (Categoria) q.executeUnique();
	}
}
