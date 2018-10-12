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
	 * Logger para escribir la traza de la ejecución
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
	 * 			Métodos
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
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia()
	{
		psa.cerrarUnidadPersistencia();
	}
	
	
	/* *************************************************
	 * Métodos 
	 * *************************************************/
	 /**
	  * Método que retorna las categorías existentes en la base de datos.
	  * @return
	  */
	public List<Categoria> darCategorias()
	{
		log.info("Consultando categorías.");
		List<Categoria> categorias = psa.darCategorias();
		log.info("Consultando categorías: " + categorias.size() + " existentes." );
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
