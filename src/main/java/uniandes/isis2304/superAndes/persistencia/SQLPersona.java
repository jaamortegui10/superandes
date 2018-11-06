package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Persona;

public class SQLPersona {
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
	public SQLPersona(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla con tipoPersona = cliente.
	 * @param pm
	 * @param cedula
	 * @param idUser
	 * @param puntos
	 * @param tipoPersona
	 * @return
	 */
	public long agregarCliente(PersistenceManager pm, int cedula, long idUser, String tipoPersona)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPersona() + "(cedula, idUser, idSucursal, puntos, tipoPersona) values (?, ?, -1, 0, ?)");
		q.setParameters(cedula, idUser, tipoPersona);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que agrega una tupla con tipoPersona = trabajador.
	 * @param pm
	 * @param cedula
	 * @param idUser
	 * @param idSucursal
	 * @param tipoPersona
	 * @return
	 */
	public long agregarTrabajadorSucursal(PersistenceManager pm, int cedula, long idUser, long idSucursal, String tipoPersona)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaPersona() + "(cedula, idUser, idSucursal, puntos, tipoPersona) values (?, ?, ?, -1, ?)");
		q.setParameters(cedula, idUser, idSucursal, tipoPersona);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método que retorna todas las tuplas con el tipoPersona dado por parámetro.
	 * @param pm
	 * @param tipoPersona
	 * @return
	 */
	public List<Persona> darPersonasPorTipoPersona(PersistenceManager pm, String tipoPersona)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersona() + " WHERE tipoPersona = ?");
		q.setParameters(tipoPersona);
		return (List<Persona>) q.executeList();
	}
	
	/**
	 * Método que retorna la persona con el id dado por paráemtro.
	 * @param pm
	 * @param cedula
	 * @return
	 */
	public List<Persona> darPersonaPorCedula(PersistenceManager pm, int cedula)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaPersona() + " WHERE cedula = ?");
		q.setParameters(cedula);
		return (List<Persona>) q.executeList();
	}
	
}
