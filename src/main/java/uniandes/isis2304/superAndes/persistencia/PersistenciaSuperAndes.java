package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Ciudad;
import uniandes.isis2304.superAndes.negocio.Contenedor;

public class PersistenciaSuperAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n.
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta.
	 */
	public final static String SQL = "javax.jdo.query.SQL";
	
	/**
	 * Atributo privado que es el �nico objeto de la clase.
	 */
	private static PersistenciaSuperAndes instance;
	/**
	 * F�brica de manejadores de la persistencia. Para el manejo correcto de las transacciones.
	 */
	private PersistenceManagerFactory pmf;
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * 
	 */
	private List<String> tablas;
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaSuperAndes.
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla User de la base de datos.
	 */
	private SQLUser sqlUser;
	/**
	 * Atributo para el acceso a la tabla Persona de la base de datos.
	 */
	private SQLPersona sqlPersona;
	/**
	 * Atributo para el acceso a la tabla Empresa de la base de datos.
	 */
	private SQLEmpresa sqlEmpresa;
	/**
	 * Atributo para el acceso a la tabla Ciudad de la base de datos.
	 */
	private SQLCiudad sqlCiudad;
	/**
	 * Atributo para el acceso a la tabla Sucursal de la base de datos.
	 */
	private SQLSucursal sqlSucursal;
	
	/**
	 * Atributo para el acceso a la tabla ProveedorSucursal de la base de datos.
	 */
	private SQLProveedorSucursal sqlProveedorSucursal;
	/**
	 * Atributo para el acceso a la tabla Categoria de la base de datos.
	 */
	private SQLCategoria sqlCategoria;
	/**
	 * Atributo para el acceso a la tabla ProductoAbstracto de la base de datos.
	 */
	private SQLProductoAbstracto sqlProductoAbstracto;
	/**
	 * Atributo para el acceso a la tabla OfrecidoSucursal de la base de datos.
	 */
	private SQLOfrecidoSucursal sqlOfrecidoSucursal;
	/**
	 * Atributo para el acceso a la tabla OfrecidoProveedor de la base de datos.
	 */
	private SQLOfrecidoProveedor sqlOfrecidoProveedor;
	/**
	 * Atributo para el acceso a la tabla ProductoFisico de la base de datos.
	 */
	private SQLProductoFisico sqlProductoFisico;
	/**
	 * Atributo para el acceso a la tabla Contenedor de la base de datos.
	 */
	private SQLContenedor sqlContenedor;
	/**
	 * Atributo para el acceso a la tabla Pedido de la base de datos.
	 */
	private SQLPedido sqlPedido;
	/**
	 * Atributo para el acceso a la tabla ProductoPedido de la base de datos.
	 */
	private SQLProductoPedido sqlProductoPedido;
	/**
	 * Atributo para el acceso a la tabla Promocion de la base de datos.
	 */
	private SQLPromocion sqlPromocion;
	/**
	 * Atributo para el acceso a la tabla PromocionPorCantidadOUnidad de la base de datos.
	 */
	private SQLPromocionPorCantidadOUnidad sqlPromocionPorCantidadOUnidad;
	/**
	 * Atributo para el acceso a la tabla PromocionPorcentajeDescuento de la base de datos.
	 */
	private SQLPromocionPorcentajeDescuento sqlPromocionPorcentajeDescuento;
	/**
	 * Atributo para el acceso a la tabla PromocionPaqueteProductos de la base de datos.
	 */
	private SQLPromocionPaqueteProductos sqlPromocionPaqueteProductos;
	/**
	 * Atributo para el acceso a la tabla Factura de la base de datos.
	 */
	private SQLFactura sqlFactura;
	/**
	 * Atributo para el acceso a la tabla ItemFactura de la base de datos.
	 */
	private SQLItemFactura sqlItemFactura;
	
	/**
	 * Atributo para el acceso a la tabla Carrito de la base de datos.
	 */
	private SQLCarrito sqlCarrito;

	
	
	private PersistenciaSuperAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		
		//Define los nombres por defecto de las tablas de la base de datos.
		tablas = new LinkedList<String>();
		tablas.add("superandes_sequence");
		tablas.add("USER");
		tablas.add("PERSONA");
		tablas.add("EMPRESA");
		tablas.add("CIUDAD");
		tablas.add("SUCURSAL");
		tablas.add("PROVEEDORSUCURSAL");
		tablas.add("CATEGORIA");
		tablas.add("PRODUCTOABSTRACTO");
		tablas.add("OFRECIDOSUCURSAL");
		tablas.add("OFRECIDOPROVEEDOR");
		tablas.add("PRODUCTOFISICO");
		tablas.add("CONTENEDOR");
		tablas.add("PEDIDO");
		tablas.add("PRODUCTOPEDIDO");
		tablas.add("PROMOCION");
		tablas.add("PROMOCIONPORCANTIDADOUNIDAD");
		tablas.add("PROMOCIONPORCENTAJEDESCUENTO");
		tablas.add("PROMOCIONPAQUETEPRODUCTOS");
		tablas.add("FACTURA");
		tablas.add("ITEMFACTURA");
		tablas.add("CARRITO");
	}
	/**
	 * Constructor privado
	 * @param tableConfig
	 */
	private PersistenciaSuperAndes(JsonObject tableConfig )
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia" + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}
	
	/**
	 * Retorna el �nico objeto PersistenciaSuperAndes existentes.
	 * @return
	 */
	public static PersistenciaSuperAndes getInstance()
	{
		if(instance == null)
		{
			instance = new PersistenciaSuperAndes();
		}
		return instance;
	}
	
	/**
	 * Cosntructor que toma los nombres de las tablas de la base de datos del objeto tableConfig.
	 * @param tableConfig
	 * @return
	 */
	public static PersistenciaSuperAndes getInstance(JsonObject tableConfig)
	{
		if(instance == null)
		{
			instance = new PersistenciaSuperAndes(tableConfig);
		}
		return instance;
	}
	
	/**
	 * Se cierra la conexi�n con la base de datos.
	 */
	public void cerrarUnidadPersistencia()
	{
		pmf.close();
		instance = null;
	}
	
	
	
	/**
	 * Genera una lista con los nombres de las tablas de las bases de datos.
	 * @param tableConfig
	 * @return
	 */
	private List<String> leerNombresTablas(JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas");
		List<String> resp = new LinkedList<String>();
		for(JsonElement nom: nombres)
		{
			resp.add(nom.getAsString());
		}
		return resp;
	}
	
	/**
	 * Instancia las clases correspondientes a cada tabla de la base de datos.
	 */
	
	private void crearClasesSQL()
	{
		sqlUser = new SQLUser(this);
		sqlPersona = new SQLPersona(this);	
		sqlEmpresa = new SQLEmpresa(this);
		sqlSucursal = new SQLSucursal(this);
		sqlCiudad = new SQLCiudad(this);
		sqlProveedorSucursal = new SQLProveedorSucursal(this);
		 sqlCategoria = new SQLCategoria(this);
		sqlProductoAbstracto = new SQLProductoAbstracto(this);
		sqlOfrecidoSucursal = new SQLOfrecidoSucursal(this);
		sqlOfrecidoProveedor = new SQLOfrecidoProveedor(this);
		sqlProductoFisico = new SQLProductoFisico(this);
		sqlContenedor = new SQLContenedor(this);
		sqlPedido = new SQLPedido(this);
		sqlProductoPedido = new SQLProductoPedido(this);
		sqlPromocion = new SQLPromocion(this);
		sqlPromocionPorCantidadOUnidad = new SQLPromocionPorCantidadOUnidad(this);
		sqlPromocionPorcentajeDescuento = new SQLPromocionPorcentajeDescuento(this);
		sqlPromocionPaqueteProductos = new SQLPromocionPaqueteProductos(this);
		sqlFactura = new SQLFactura(this);
		sqlItemFactura = new SQLItemFactura(this);
		sqlCarrito = new SQLCarrito(this);
		
	}
	

	
	
	
	/**
	 * La cadena de caracteres con el nombre del secuenciador de SuperAndes.
	 * @return
	 */
	public String darSeqSuperAndes()
	{
		return tablas.get(0);
	}
	
	
	public String darTablaUser()
	{
		return tablas.get(1);
	}
	public String darTablaPersona()
	{
		return tablas.get(2);
	}
	public String darTablaEmpresa()
	{
		return tablas.get(3);
	}
	public String darTablaCiudad()
	{
		return tablas.get(4);
	}
	public String darTablaSucursal()
	{
		return tablas.get(5);
	}

	public String darTablaProveedorSucursal()
	{
		return tablas.get(6);
	}
	public String darTablaCategoria()
	{
		return tablas.get(7);
	}
	public String darTablaProductoAbstracto()
	{
		return tablas.get(8);
	}
	public String darTablaOfrecidoSucursal()
	{
		return tablas.get(9);
	}
	public String darTablaOfrecidoProveedor()
	{
		return tablas.get(10);
	}
	public String darTablaProductoFisico()
	{
		return tablas.get(11);
	}
	public String darTablaContenedor()
	{
		return tablas.get(12);
	}
	public String darTablaPedido()
	{
		return tablas.get(13);
	}
	public String darTablaProductoPedido()
	{
		return tablas.get(14);
	}
	public String darTablaPromocion()
	{
		return tablas.get(15);
	}
	public String darTablaPromocionPorCantidadOUnidad()
	{
		return tablas.get(16);
	}
	public String darTablaPromocionPorcentajeDescuento()
	{
		return tablas.get(17);
	}
	public String darTablaPromocionPaqueteProductos()
	{
		return tablas.get(18);
	}
	public String darTablaFactura()
	{
		return tablas.get(19);
	}
	public String darTablaItemFactura()
	{
		return tablas.get(20);
	}
	public String darTablaCarrito()
	{
		return tablas.get(21);
	}
	
	/**
	 * Transacci�n para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de Parranderos
	 */
	private long nextVal()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
	}
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* *******************************************************
	 * M�todos consultas-adiciones sql.
	 * *******************************************************/
	
	
	public Carrito agregarCarrito( long idUser)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlCarrito.agregarTupla(pm,  id,  idUser);
			//Falta la implementaci�n de los productosCarrito.
			tx.commit();
			
			log.trace("Inserci�n de carrito: " + id + ": " + tuplasInsertadas + " tuplas insertadas.");
			return new Carrito(id, idUser);
			
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	public Categoria agregarCategoria(String nombre, String caracteristicas, String almacenamiento, String manejo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.agregarTupla(pm, nombre, caracteristicas, almacenamiento, manejo);
			tx.commit();
			
			log.trace("Inserci�n de categoria: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			
			return new Categoria(nombre, caracteristicas, almacenamiento, manejo);
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	public Ciudad agregarCiudad(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			//Falta implementaci�n restricci�n nombre  no repetido.
			long id = nextVal();
			long tuplasInsertadas = sqlCiudad.agregarTupla(pm, id, nombre);
			tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Ciudad(id, nombre);
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	public Contenedor agregarContenedor( long sucursalId, String tipo, int capacidad, int capacidadOcupada)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlContenedor.agregarTupla(pm, id, sucursalId, tipo, capacidad, capacidadOcupada);
			tx.commit();
			
			log.trace("Inserci�n de contenedor: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Contenedor(id, sucursalId, tipo, capacidad, capapcidadOcupada);
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
		
	}
	
	public void agregarProveedor( int nit, long idUser, String dir, String tipoEmpresa )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	public void agregarClienteEmpresa( int nit, long idUser, String dir, int puntos, String tipoEmpresa)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	public void agregarFactura(long id, long idUser, double costoTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
    public void agregarItemFactura(  long idFactura, long idProductoOfrecido, int cantidad, double costo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarOfrecidoProveedor(  long idAbstracto, double precio, int nitProveedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarOfrecidoSucursal(  long idAbstracto, long idSucursal, double precio)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
		
    }
    
    public void agregarPedido(  long id, long idSucursal, int nitProveedor, double precio, String estado, String fechaEntrega)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarClientePersona(  int cedula, long idUser, int puntos, String tipoPersona)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarTrabajadorSucursal(  int cedula, long idUser, long idSucursal, String tipoPersona)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarProductoAbstracto(  long id, String nombre, String tipo, String unidadMedida, String categoria)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarProductoFisico(  long id, long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarProductoPedido(  long idPedido, long idProductoOfrecido, int cantidad)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarPromocion(  long id, long idSucursal, String descripcion, String tipo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarPromocionPaqueteProductos(  long idPromocion, double precio, long idProductoOfrecido1, long idProductoOfrecido2)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void  agregarPromocionPorCantidadOUnidad(  long idOfrecido, long idPromocion, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarPromocionPorcentajeDescuento(  long idProductoOfrecido, long idPromocion, int porcentajeDescuento)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarProveedorSucursal(  long idSucursal, int nitProveedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarSucursal(  long id, String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    public void agregarUser(  long id, String password, String nombre, String correo, String tipo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
tx.commit();
			
			log.trace("Inserci�n de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
    }
    
    
	
	public List<Categoria> darCategorias()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}
	
	public void metodo(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			
		}finally
		{
			 if (tx.isActive())
	            {
	                tx.rollback();
	            }
	            pm.close();
		}
	}
	
	
	
	
	/**
	 * Elimiina todas as tuplas de la base de datos de SuperAndes.
	 * @return
	 */
	public long[] limpiarSuperAndes()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
}
