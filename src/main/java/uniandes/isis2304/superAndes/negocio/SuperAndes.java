package uniandes.isis2304.superAndes.negocio;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes()
	{
		psa = PersistenciaSuperAndes.getInstance();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes(JsonObject tableConfig)
	{
		psa = PersistenciaSuperAndes.getInstance(tableConfig);
	}
	/**
	 * Cierra la conexi�n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia()
	{
		psa.cerrarUnidadPersistencia();
	}
	
	
	/* *************************************************
	 * M�todos 
	 * *************************************************/
	 /**
	  * M�todo que retorna las categor�as existentes en la base de datos.
	  * @return
	  */
	public List<Categoria> darCategorias()
	{
		log.info("Consultando categor�as.");
		List<Categoria> categorias = psa.darCategorias();
		log.info("Consultando categor�as: " + categorias.size() + " existentes." );
		return categorias;
	}
	
	
	public long[] limpiarSuperAndes()
	{
		 log.info ("Limpiando la BD de SuperAndes");
	     long [] borrrados = psa.limpiarSuperAndes();
	     log.info ("Limpiando la BD de SuperAndes: Listo!");
	     return borrrados;
	}
	
}
