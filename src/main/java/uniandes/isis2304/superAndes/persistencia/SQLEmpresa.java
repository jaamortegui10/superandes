package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Empresa;

public class SQLEmpresa {
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
	 * Método constructor de clase.
	 * @param psa
	 */
	public SQLEmpresa(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método para agregar una nueva empresa de tipo proveedor.
	 * @param pm
	 * @param nit
	 * @param idUser
	 * @param dir
	 * @param tipoEmpresa
	 * @return
	 */
	public long agregarEmpresaProveedor(PersistenceManager pm, int nit, long idUser, String dir, String tipoEmpresa )
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaEmpresa() + "(nit, idUser, dir, tipoEmpresa) values (?, ?, ?, ?)");
		q.setParameters(nit, idUser, dir, tipoEmpresa);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para agregar una nueva empresa de tipo cliente.
	 * @param pm
	 * @param nit
	 * @param idUser
	 * @param dir
	 * @param puntos
	 * @param tipoEmpresa
	 * @return
	 */
	public long agregarEmpresaCliente(PersistenceManager pm, int nit, long idUser, String dir, int puntos, String tipoEmpresa)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaEmpresa() + "(nit, idUser, dir, puntos, tipoEmpresa) values (?, ?, ?, ?, ?)");
		q.setParameters(nit, idUser, dir, puntos, tipoEmpresa);
		return (long) q.executeUnique();
	}
	
	/**
	 * Método para retornar todas las empresas con el tipoEmpresa dado por parámetro.
	 * @param pm
	 * @param tipoEmpresa
	 * @return
	 */
	public List<Empresa> darEmpresasPorTipo(PersistenceManager pm, String tipoEmpresa)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEmpresa() + " WHERE tipoEmpresa = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(tipoEmpresa);
		return (List<Empresa>) q.executeList();
	}
	
	/**
	 * Método para retornar una Empresa con el nit dado por parámetro.
	 * @param pm
	 * @param nit
	 * @return
	 */
	public Empresa darEmpresaPorNIT(PersistenceManager pm, int nit)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEmpresa() + " WHERE NIT = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(nit);
		return (Empresa) q.executeUnique();
	}
	
	/**
	 * Método para retornar una Empresa con el idUser dado por parámetro.
	 * @param pm
	 * @param idUser
	 * @return
	 */
	public Empresa darEmpresaPorIdUser(PersistenceManager pm, long idUser)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaEmpresa() + " WHERE idUser = ?");
		q.setResultClass(Empresa.class);
		q.setParameters(idUser);
		return (Empresa) q.executeUnique();
	}
	
}
