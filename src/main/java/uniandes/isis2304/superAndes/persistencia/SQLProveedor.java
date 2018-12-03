package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;

public class SQLProveedor {
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
	public SQLProveedor(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Método que agrega una nueva tupla.
	 * @param pm
	 * @param id
	 * @param idSucursal
	 * @param descripcion
	 * @param tipo
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, int nit, String nombre, String password, String dir, String conectado)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaProveedor() + "(nit, nombre, password, dir, conectado) values (?, ?, ?, ?, ?)");
		q.setParameters(nit, nombre, password, dir, conectado);
		return (long) q.executeUnique();
	}
	
	public List<Proveedor> darProveedores(PersistenceManager pm)
	{
		System.out.println("************************************ \n Tabla proveedor: " + psa.darTablaProveedor());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}
}
