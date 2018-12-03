package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Proveedor;

public class SQLCliente {
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
	public SQLCliente(PersistenciaSuperAndes psa)
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
	public long agregarCliente(PersistenceManager pm, int documento, String nombre, String password, String dir, String tipo, int puntos, String conectado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaCliente() + "(documento, nombre, password, dir, tipo, puntos, conectado) values (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(documento, nombre, password, dir, tipo, puntos, conectado);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Método que retorna todas las tuplas con el tipoPersona dado por parámetro.
	 * @param pm
	 * @param tipoPersona
	 * @return
	 */
	public List<Cliente> darClientesPorTipo(PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente() + " WHERE tipo = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(tipo);
		return (List<Cliente>) q.executeList();
	}
	
	/**
	 * Método que retorna la persona con el id dado por paráemtro.
	 * @param pm
	 * @param cedula
	 * @return
	 */
	public List<Cliente> darClientePorDocumento(PersistenceManager pm, int cedula)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente() + " WHERE documento = ?");
		q.setParameters(cedula);
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientes(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public long cambiarConexion(PersistenceManager pm, String conectado, long documento)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + psa.darTablaCliente() + " SET conectado = ? WHERE documento = ?");
		q.setParameters(conectado , documento);
		return (long) q.executeUnique();
	}
	
}
