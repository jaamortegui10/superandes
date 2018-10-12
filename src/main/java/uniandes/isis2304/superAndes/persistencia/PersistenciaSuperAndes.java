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
import uniandes.isis2304.superAndes.negocio.Empresa;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.ItemFactura;
import uniandes.isis2304.superAndes.negocio.OfrecidoProveedor;
import uniandes.isis2304.superAndes.negocio.OfrecidoSucursal;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.Persona;
import uniandes.isis2304.superAndes.negocio.ProductoAbstracto;
import uniandes.isis2304.superAndes.negocio.ProductoFisico;
import uniandes.isis2304.superAndes.negocio.ProductoPedido;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.PromocionPaqueteProductos;
import uniandes.isis2304.superAndes.negocio.PromocionPorCantidadOUnidad;
import uniandes.isis2304.superAndes.negocio.PromocionPorcentajeDescuento;
import uniandes.isis2304.superAndes.negocio.ProveedorSucursal;
import uniandes.isis2304.superAndes.negocio.Sucursal;
import uniandes.isis2304.superAndes.negocio.User;

public class PersistenciaSuperAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución.
	 */
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta.
	 */
	public final static String SQL = "javax.jdo.query.SQL";
	
	/**
	 * Atributo privado que es el único objeto de la clase.
	 */
	private static PersistenciaSuperAndes instance;
	/**
	 * Fábrica de manejadores de la persistencia. Para el manejo correcto de las transacciones.
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
	 * Retorna el único objeto PersistenciaSuperAndes existentes.
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
	 * Se cierra la conexión con la base de datos.
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
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextVal()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
	}
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
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
	 * Métodos consultas-adiciones sql.
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
			//Falta la implementación de los productosCarrito.
			tx.commit();
			
			log.trace("Inserción de carrito: " + id + ": " + tuplasInsertadas + " tuplas insertadas.");
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
			
			log.trace("Inserción de categoria: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			
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
			//Falta implementación restricción nombre  no repetido.
			long id = nextVal();
			long tuplasInsertadas = sqlCiudad.agregarTupla(pm, id, nombre);
			tx.commit();
			
			log.trace("Inserción de : " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
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
			
			log.trace("Inserción de contenedor: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Contenedor(id, sucursalId, tipo, capacidad, capacidadOcupada);
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
	
	public Empresa agregarEmpresa( int nit, long idUser, String direccion, int puntosCompras, String tipoEmpresa )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			double tuplasInsertadas;
			if(tipoEmpresa.equals(Empresa.PROVEEDOR))
				tuplasInsertadas = sqlEmpresa.agregarProveedor(pm, nit, idUser, direccion, tipoEmpresa);
			else
				tuplasInsertadas = sqlEmpresa.agregarCliente(pm, nit, idUser, direccion, puntosCompras, tipoEmpresa);
			tx.commit();
			
			log.trace("Inserción de Empresa: " + nit + "," + tipoEmpresa +  ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Empresa(nit, idUser, direccion, puntosCompras, tipoEmpresa);
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
	
	public Factura agregarFactura( long idUser, double costoTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlFactura.agregarTupla(pm, id, idUser, costoTotal);
			tx.commit();
			
			log.trace("Inserción de Factura: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Factura(id, idUser, costoTotal);
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
	
    public ItemFactura agregarItemFactura(  long idFactura, long idProductoOfrecido, int cantidad, double costo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlItemFactura.agregarTupla(pm, idFactura, idProductoOfrecido, cantidad, costo);
			tx.commit();
			
			log.trace("Inserción de ItemFactura: " + idFactura + "," + idProductoOfrecido + ": " + tuplasInsertadas + "tuplas insertadas.");
			
			return new ItemFactura(idFactura, idProductoOfrecido, cantidad, costo);
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
    
    public OfrecidoProveedor agregarOfrecidoProveedor(  long idAbstracto, double precio, int nitProveedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfrecidoProveedor.agregarTupla(pm, idAbstracto, precio, nitProveedor);
			tx.commit();
			
			log.trace("Inserción de ofrecidoProveedor: " + idAbstracto + "," + nitProveedor + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new OfrecidoProveedor(idAbstracto, precio, nitProveedor);
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
    
    public OfrecidoSucursal agregarOfrecidoSucursal(long id,  long idAbstracto, long idSucursal, double precio)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfrecidoSucursal.agregarTupla(pm, idAbstracto, idSucursal, precio);
			
			tx.commit();
			
			log.trace("Inserción de OfrecidoSucursal: " + idAbstracto + ","+ idSucursal + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new OfrecidoSucursal(idAbstracto, idSucursal, precio);
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
    
    public Pedido agregarPedido(  long idSucursal, int nitProveedor, double precio, String fechaEntrega)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlPedido.agregarTupla(pm, id, idSucursal, nitProveedor, precio, Pedido.ESTADO_POR_ENTREGAR, fechaEntrega);
			tx.commit();
			
			log.trace("Inserción de Pedido: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Pedido(id, idSucursal, nitProveedor, precio, Pedido.ESTADO_POR_ENTREGAR, fechaEntrega, null, -1);
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
    
    public Persona agregarPersona(  int cedula, long idUser,  int puntos, long idSucursal, String tipoPersona)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas;
			if(tipoPersona.equals(Persona.TIPO_CLIENTE))
				tuplasInsertadas = sqlPersona.agregarCliente(pm, cedula, idUser, puntos, tipoPersona);
			else
				tuplasInsertadas = sqlPersona.agregarTrabajadorSucursal(pm, cedula, idUser, idSucursal, tipoPersona);
			tx.commit();
			
			log.trace("Inserción de Persona: " + cedula + ","+ tipoPersona + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Persona(cedula, idUser,  puntos, idSucursal, tipoPersona);
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
    
    public ProductoAbstracto agregarProductoAbstracto( String nombre, String tipo, String unidadMedida, String categoria)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlProductoAbstracto.agregarTupla(pm, id, nombre, tipo, unidadMedida, categoria);
			tx.commit();
			
			log.trace("Inserción de ProductoAbstracto: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProductoAbstracto(id, nombre, tipo, unidadMedida, categoria);
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
    
    public ProductoFisico agregarProductoFisico( long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlProductoFisico.agregarTupla(pm, id, idOfrecido, cantidadMedida, codigoBarras, idContenedor);
			
			tx.commit();
			
			log.trace("Inserción de ProductoFisico: " + idOfrecido + "," + codigoBarras + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProductoFisico(id, idOfrecido, cantidadMedida, codigoBarras, idContenedor, -1);
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
    
    public ProductoPedido agregarProductoPedido(  long idPedido, long idProductoOfrecido, int cantidad)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoPedido.agregarTupla(pm, idPedido, idProductoOfrecido, cantidad);
			tx.commit();
			
			log.trace("Inserción de ProductoPedido: " + idPedido + "," + idProductoOfrecido + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProductoPedido(idPedido, idProductoOfrecido, cantidad);
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
    
    public Promocion agregarPromocion( long idSucursal, String descripcion, String tipo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlPromocion.agregarTupla(pm, id, idSucursal, descripcion, tipo);
			tx.commit();
			
			log.trace("Inserción de Promocion: " + id + "," + idSucursal + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Promocion(id, idSucursal, descripcion, tipo);
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
    
    public PromocionPaqueteProductos agregarPromocionPaqueteProductos(  long idPromocion, double precio, long idProductoOfrecido1, long idProductoOfrecido2)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPromocionPaqueteProductos.agregarTupla(pm, idPromocion, precio, idProductoOfrecido1, idProductoOfrecido2);
			
			tx.commit();
			
			log.trace("Inserción de PromocionPaqueteProductos: " + idPromocion + "," + idProductoOfrecido1 + "," + idProductoOfrecido2 + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new PromocionPaqueteProductos(idPromocion, precio, idProductoOfrecido1, idProductoOfrecido2);
			
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
    
    public PromocionPorCantidadOUnidad  agregarPromocionPorCantidadOUnidad(  long idOfrecido, long idPromocion, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPromocionPorCantidadOUnidad.agregarTupla(pm, idOfrecido, idPromocion, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas);
			
			tx.commit();
			
			log.trace("Inserción de PromocionPorCantidadOUnidades: " + idPromocion +"," + idOfrecido + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new PromocionPorCantidadOUnidad(idOfrecido, idPromocion, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas);
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
    
    public PromocionPorcentajeDescuento agregarPromocionPorcentajeDescuento(  long idProductoOfrecido, long idPromocion, int porcentajeDescuento)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPromocionPorcentajeDescuento.agregarTupla(pm, idProductoOfrecido, idPromocion, porcentajeDescuento);
			tx.commit();
			
			log.trace("Inserción de PromocionPorcentajeDescuento: " + idPromocion +"," + idProductoOfrecido + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new PromocionPorcentajeDescuento(idProductoOfrecido, idPromocion, porcentajeDescuento);
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
    
    public ProveedorSucursal agregarProveedorSucursal(  long idSucursal, int nitProveedor)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedorSucursal.agregarTupla(pm, idSucursal, nitProveedor);
			tx.commit();
			
			log.trace("Inserción de ProveedorSucursal: " + idSucursal + "," + nitProveedor + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProveedorSucursal(idSucursal, nitProveedor);
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
    
    public Sucursal agregarSucursal( String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlSucursal.agregarTupla(pm, id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad);
			tx.commit();
			
			log.trace("Inserción de Sucursal: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Sucursal(id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad );
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
    
    public User agregarUser( String password, String nombre, String correo, String tipo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlUser.agregarTupla(pm, id, password, nombre, correo, tipo);
			tx.commit();
			
			log.trace("Inserción de User: " + id + "," + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new User(id, password, nombre, correo, tipo);
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
