package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;

public class SQLSucursal {
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
	public SQLSucursal(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Agrega una nueva tupla.
	 * @param pm
	 * @param id
	 * @param nombre
	 * @param tamanho
	 * @param direccion
	 * @param nivelReorden
	 * @param nivelReabastecimiento
	 * @param idCiudad
	 * @return
	 */
	public long agregarTupla(PersistenceManager pm, long id, String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad, String password)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + psa.darTablaSucursal() + "(id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password) values (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password);
		return (long) q.executeUnique();
	}
	
	/**
	 * Retorna todas las tuplas.
	 * @param pm
	 * @return
	 */
	public List<Sucursal> darSucursales(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + psa.darTablaSucursal() );
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
}
