package uniandes.isis2304.superAndes.persistencia;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Carrito;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Ciudad;
import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Contenedor;
import uniandes.isis2304.superAndes.negocio.Factura;
import uniandes.isis2304.superAndes.negocio.OfrecidoProveedor;
import uniandes.isis2304.superAndes.negocio.OfrecidoSucursal;
import uniandes.isis2304.superAndes.negocio.Pedido;
import uniandes.isis2304.superAndes.negocio.ProductoAbstracto;
import uniandes.isis2304.superAndes.negocio.ProductoFactura;
import uniandes.isis2304.superAndes.negocio.ProductoFisico;
import uniandes.isis2304.superAndes.negocio.ProductoPedido;
import uniandes.isis2304.superAndes.negocio.PromocionPaqueteProductos;
import uniandes.isis2304.superAndes.negocio.PromocionPorCantidadOUnidad;
import uniandes.isis2304.superAndes.negocio.PromocionPorcentajeDescuento;
import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.ProveedorSucursal;
import uniandes.isis2304.superAndes.negocio.Sucursal;

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
	
	
	public final static String SI = "si";
	
	public final static String NO = "no";
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
	 * Atributo para el acceso a la tabla Persona de la base de datos.
	 */
	private SQLCliente sqlCliente;
	/**
	 * Atributo para el acceso a la tabla Empresa de la base de datos.
	 */
	private SQLProveedor sqlProveedor;
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
	 * Atributo para el acceso a la tabla PromocionPorCantidadOUnidad de la base de datos.
	 */
	private SQLPromPorCantidadOUnidad sqlPromPorCantidadOUnidad;
	/**
	 * Atributo para el acceso a la tabla PromocionPorcentajeDescuento de la base de datos.
	 */
	private SQLPromPorcentajeDescuento sqlPromPorcentajeDescuento;
	/**
	 * Atributo para el acceso a la tabla PromocionPaqueteProductos de la base de datos.
	 */
	private SQLPromPaqueteProductos sqlPromPaqueteProductos;
	/**
	 * Atributo para el acceso a la tabla Factura de la base de datos.
	 */
	private SQLFactura sqlFactura;
	/**
	 * Atributo para el acceso a la tabla ItemFactura de la base de datos.
	 */
	private SQLProductoFactura sqlProductoFactura;
	
	/**
	 * Atributo para el acceso a la tabla Carrito de la base de datos.
	 */
	private SQLCarrito sqlCarrito;
	
	private SQLClienteCarrito sqlClienteCarrito;
	
	private SQLProductoCarrito sqlProductoCarrito;
	
	private SQLProductoContenedor sqlProductoContenedor;
	
	private SQLTipoProducto sqlTipoProducto;
	
	private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	private PersistenciaSuperAndes()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		
		//Define los nombres por defecto de las tablas de la base de datos.
		tablas = new LinkedList<String>();
		tablas.add("superandes_sequence");
		tablas.add("CARRITO");
		tablas.add("CATEGORIA");
		tablas.add("CIUDAD");
		tablas.add("CLIENTE");
		tablas.add("CLIENTECARRITO");
		tablas.add("CONTENEDOR");
		tablas.add("FACTURA");
		tablas.add("OFRECIDOPROVEEDOR");
		tablas.add("OFRECIDOSUCURSAL");
		tablas.add("PEDIDO");
		tablas.add("PRODUCTOABSTRACTO");
		tablas.add("PRODUCTOCARRITO");
		tablas.add("PRODUCTOCONTENEDOR");
		tablas.add("PRODUCTOFACTURA");
		tablas.add("PRODUCTOFISICO");
		tablas.add("PRODUCTOPEDIDO");
		tablas.add("PROMPAQUETEPRODUCTOS");
		tablas.add("PROMPORCANTIDADOUNIDAD");
		tablas.add("PROMPORCENTAJEDESCUENTO");
		tablas.add("PROVEEDOR");
		tablas.add("PROVEEDORSUCURSAL");
		tablas.add("SUCURSAL");
		tablas.add("TIPOPRODUCTO");
		
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
	
	private void crearClasesSQL()
	{
		sqlCarrito = new SQLCarrito(this);
		sqlCategoria = new SQLCategoria(this);
		sqlCiudad = new SQLCiudad(this);
		sqlCliente = new SQLCliente(this);
		sqlClienteCarrito = new SQLClienteCarrito(this);
		sqlContenedor = new SQLContenedor(this);
		sqlFactura = new SQLFactura(this);
		sqlOfrecidoProveedor = new SQLOfrecidoProveedor(this);
		sqlOfrecidoSucursal = new SQLOfrecidoSucursal(this);
		sqlPedido = new SQLPedido(this);
		sqlProductoAbstracto = new SQLProductoAbstracto(this);
		sqlProductoCarrito = new SQLProductoCarrito(this);
		sqlProductoContenedor = new SQLProductoContenedor(this);
		sqlProductoFactura = new SQLProductoFactura(this);
		sqlProductoFisico = new SQLProductoFisico(this);
		sqlProductoPedido = new SQLProductoPedido(this);
		sqlPromPaqueteProductos = new SQLPromPaqueteProductos(this);
		sqlPromPorCantidadOUnidad = new SQLPromPorCantidadOUnidad(this);
		sqlPromPorcentajeDescuento = new SQLPromPorcentajeDescuento(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProveedorSucursal = new SQLProveedorSucursal(this);
		sqlSucursal = new SQLSucursal(this);
		sqlTipoProducto = new SQLTipoProducto(this);		
		sqlUtil = new SQLUtil(this);
		
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

	
	/**
	 * La cadena de caracteres con el nombre del secuenciador de SuperAndes.
	 * @return
	 */
	public String darSeqSuperAndes()
	{
		return tablas.get(0);
	}
	
	
	public String darTablaCarrito()
	{
		return tablas.get(1);
	}
	public String darTablaCategoria()
	{
		return tablas.get(2);
	}
	public String darTablaCiudad()
	{
		return tablas.get(3);
	}
	public String darTablaCliente()
	{
		return tablas.get(4);
	}
	public String darTablaClienteCarrito()
	{
		return tablas.get(5);
	}

	public String darTablaContenedor()
	{
		return tablas.get(6);
	}
	public String darTablaFactura()
	{
		return tablas.get(7);
	}
	public String darTablaOfrecidoProveedor()
	{
		return tablas.get(8);
	}
	public String darTablaOfrecidoSucursal()
	{
		return tablas.get(9);
	}
	public String darTablaPedido()
	{
		return tablas.get(10);
	}
	public String darTablaProductoAbstracto()
	{
		return tablas.get(11);
	}
	public String darTablaProductoCarrito()
	{
		return tablas.get(12);
	}
	public String darTablaProductoContenedor()
	{
		return tablas.get(13);
	}
	public String darTablaProductoFactura()
	{
		return tablas.get(14);
	}
	public String darTablaProductoFisico()
	{
		return tablas.get(15);
	}
	public String darTablaProductoPedido()
	{
		return tablas.get(16);
	}
	public String darTablaPromPaqueteProductos()
	{
		return tablas.get(17);
	}
	public String darTablaPromPorCantidadOUnidad()
	{
		return tablas.get(18);
	}
	public String darTablaPromPorcentajeDescuento()
	{
		return tablas.get(19);
	}
	public String darTablaProveedor()
	{
		return tablas.get(20);
	}
	public String darTablaProveedorSucursal()
	{
		return tablas.get(21);
	}
	public String darTablaSucursal()
	{
		return tablas.get(22);
	}
	public String darTablaTipoProducto()
	{
		return tablas.get(23);
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

	
	public Carrito agregarCarrito( long idCliente, long idSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadasEnCarrito = sqlCarrito.agregarTupla(pm,  id,  idSucursal, SI);
			long tuplasInsertadasEnClienteCarrito = sqlClienteCarrito.agregarTupla(pm, idCliente, id);
			//Falta la implementación de los productosCarrito.
			tx.commit();
			
			log.trace("Inserción de carrito: " + id + ": " + tuplasInsertadasEnCarrito + " tuplas insertadas en carrito.");
			log.trace("Inserción en ClienteCarrito: idCarrito: " + id + ", idCliente: " + idCliente + "; tuplasIncertadas: " + tuplasInsertadasEnClienteCarrito );
			return new Carrito(id, idSucursal, SI);
			
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
	
	public Categoria agregarCategoria(String nombre, String caracteristicas, String manejo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCategoria.agregarTupla(pm, nombre, caracteristicas, manejo);
			tx.commit();
			
			log.trace("Inserción de categoria: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			
			return new Categoria(nombre, caracteristicas, manejo);
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
	
	public Contenedor agregarContenedor( long sucursalId, String tipo, int capacidad, int capacidadOcupada, String tipoProducto)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlContenedor.agregarTupla(pm, id, sucursalId, tipo, capacidad, capacidadOcupada, tipoProducto);
			tx.commit();
			
			log.trace("Inserción de contenedor: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Contenedor(id, sucursalId, tipo, capacidad, capacidadOcupada, tipoProducto);
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
	
	public Proveedor agregarProveedor( int nit, String nombre, String password, String dir )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			System.out.println("Agregando proveedor en Persistencia.");
			tx.begin();
				double tuplasInsertadas;
				tuplasInsertadas = sqlProveedor.agregarTupla(pm, nit, nombre, password, dir, NO);
			
			tx.commit();
			
			log.trace("Inserción de proveedor: " + nit + ": " + tuplasInsertadas + " tuplas insertadas.");
			return new Proveedor(nit, nombre, password, dir, NO);
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
	
	/**
	 * Método para agregar un nuevo proveedor
	 * @param nit
	 * @param idUser
	 * @param direccion
	 * @param tipoEmpresa
	 * @return
	 */
	public Cliente agregarCliente( int documento, String nombre, String password, String dir, String tipo )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
				double tuplasInsertadas;
				tuplasInsertadas = sqlCliente.agregarCliente(pm, documento, nombre, password, dir, tipo, 0, NO);
			tx.commit();
			
			log.trace("Inserción de Cliente: " + documento + ", " + tipo +  ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Cliente(documento, nombre, password, dir, tipo, 0, NO);
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
	
	
	
    public ProductoFactura agregarProductoFactura(  long idFactura, long idProductoFisico)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductoFactura.agregarTupla(pm, idFactura, idProductoFisico);
			tx.commit();
			
			log.trace("Inserción de ItemFactura: " + idFactura + ", " + idProductoFisico + ": " + tuplasInsertadas + "tuplas insertadas.");
			
			return new ProductoFactura(idFactura, idProductoFisico);
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
			long id = nextVal();
			long tuplasInsertadas = sqlOfrecidoProveedor.agregarTupla(pm, id, idAbstracto, precio, nitProveedor);
			tx.commit();
			
			log.trace("Inserción de ofrecidoProveedor: " + idAbstracto + "," + nitProveedor + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new OfrecidoProveedor(id, idAbstracto, precio, nitProveedor);
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
    
    public OfrecidoSucursal agregarOfrecidoSucursal( long idAbstracto, long idSucursal, double precio)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlOfrecidoSucursal.agregarTupla(pm, id, idAbstracto, idSucursal, precio);
			
			tx.commit();
			
			log.trace("Inserción de OfrecidoSucursal: " + idAbstracto + ","+ idSucursal + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new OfrecidoSucursal(id, idAbstracto, idSucursal, precio, SQLOfrecidoSucursal.NINGUNA);
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
    
    public Pedido agregarPedido(  long idSucursal, int nitProveedor, double precio, Date fechaEntrega)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlPedido.agregarTupla(pm, id, idSucursal, nitProveedor, precio, Pedido.ESTADO_POR_ENTREGAR, dateFormat.format(fechaEntrega));
			tx.commit();
			
			log.trace("Inserción de Pedido: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Pedido(id, idSucursal, nitProveedor, precio, Pedido.ESTADO_POR_ENTREGAR, fechaEntrega, "", -1);
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
    
    public ProductoAbstracto agregarProductoAbstracto( String nombre, String unidadMedida, int cantidadMedida, long idTipo)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlProductoAbstracto.agregarTupla(pm, id, nombre, unidadMedida, cantidadMedida, idTipo);
			tx.commit();
			
			log.trace("Inserción de ProductoAbstracto: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProductoAbstracto(id, nombre, unidadMedida, cantidadMedida, idTipo);
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
    
    /**
     * Método para agregar producto físico.
     * @param idOfrecido
     * @param cantidadMedida
     * @param codigoBarras
     * @param idContenedor
     * @return
     */
    public ProductoFisico agregarProductoFisico( String codigoBarras, long idContenedor, String estado, long idTipo, long idAbstracto, long idSucursal)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			
			double precio = this.darPrecioPorIdAbstractoYIdSucursal(pmf.getPersistenceManager(), idAbstracto, idSucursal);
			long tuplasInsertadas = sqlProductoFisico.agregarTupla(pm, id, codigoBarras, estado, idTipo, idAbstracto, precio);
			
			tx.commit();
			
			log.trace("Inserción de ProductoFisico: " + id + "," + codigoBarras + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new ProductoFisico(id, codigoBarras, estado, idTipo, idAbstracto, precio);
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
    
   
    
    public PromocionPaqueteProductos agregarPromPaqueteProductos(double precio, long idProductoOfrecido1, long idProductoOfrecido2, long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		long tuplasInsertadas = 0;
    	try
    	{
    		tx.begin();
    		long id = nextVal();
    		tuplasInsertadas = sqlPromPaqueteProductos.agregarTupla(pm, id, precio, idProductoOfrecido1, idProductoOfrecido2, idSucursal, dateFormat.format(fecha_inicio), dateFormat.format(fecha_fin), descripcion);
    		tx.commit();
    		return new PromocionPaqueteProductos(id, precio, idProductoOfrecido1, idProductoOfrecido2, idSucursal, fecha_inicio, fecha_fin, descripcion);
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
    	return null;
    }
    
    public PromocionPorCantidadOUnidad agregarPromPorCantidadOUnidad(long idProductoOfrecido1, int cantPagada, int cantAdquirida,  long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		long tuplasInsertadas = 0;
    	try
    	{
    		
    		tx.begin();
    		long id = nextVal();
			tuplasInsertadas = sqlPromPorCantidadOUnidad.agregarTupla(pm, id, idProductoOfrecido1, cantPagada, cantAdquirida, idSucursal, dateFormat.format(fecha_inicio), dateFormat.format(fecha_fin), descripcion);
			
    		tx.commit();
    		return new PromocionPorCantidadOUnidad(id, idProductoOfrecido1, cantPagada, cantAdquirida, idSucursal, fecha_inicio, fecha_fin, descripcion);
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
    	return null;
    }
    
    public PromocionPorcentajeDescuento agregarPromPorcentajeDescuento(long idProductoOfrecido1, int porcentajeDescuento,  long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		long tuplasInsertadas = 0;
    	try
    	{
    		tx.begin();
    		long id = nextVal();
			tuplasInsertadas = sqlPromPorcentajeDescuento.agregarTupla(pm, id, idProductoOfrecido1, porcentajeDescuento,idSucursal, dateFormat.format(fecha_inicio), dateFormat.format(fecha_fin), descripcion);

    		tx.commit();
    		return new PromocionPorcentajeDescuento(id, idProductoOfrecido1, porcentajeDescuento, idSucursal, fecha_inicio, fecha_fin, descripcion);
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
    	return null;
    }
    
    
    /**
     * Agregar tupla en la tabla ProveedorSucursal
     * @param idSucursal
     * @param nitProveedor
     * @return
     */
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
    
    /**
     * Método para agregar una sucursal.
     * @param nombre
     * @param tamanho
     * @param direccion
     * @param nivelReorden
     * @param nivelReabastecimiento
     * @param idCiudad
     * @return
     */
    public Sucursal agregarSucursal( String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad, String password)
    {
    	PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlSucursal.agregarTupla(pm, id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password);
			tx.commit();
			
			log.trace("Inserción de Sucursal: " + nombre + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Sucursal(id, nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password );
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
    
    /**
	 * Método que retorna un producto Físico por su id
	 * @param idProducto
	 * @return
	 */
	public ProductoFisico darProductoFisicoPorId(long idProducto)
	{
		return sqlProductoFisico.darProductoFisicoPorId(pmf.getPersistenceManager(), idProducto);
	}
	
	public List<Categoria> darCategorias()
	{
		return sqlCategoria.darCategorias(pmf.getPersistenceManager());
	}
	
	public List<Cliente> darClientesPorTipo(String tipoCliente)
	{
		return sqlCliente.darClientesPorTipo(pmf.getPersistenceManager(),  tipoCliente);
	}
	
	public List<Proveedor> darProveedores()
	{
		System.out.println("En persistencia.");
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}
	
	public List<Ciudad> darCiudades()
	{
		return sqlCiudad.darCiudades(pmf.getPersistenceManager());
	}
	
	public Ciudad darCiudadPorId(long id)
	{
		return sqlCiudad.darCiudadPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Sucursal> darSucursales()
	{
		return sqlSucursal.darSucursales(pmf.getPersistenceManager());
	}
	
	public List<Contenedor> darContenedoresPorSucursalId(long sucursalId)
	{
		return sqlContenedor.darContenedoresPorSucursalId(pmf.getPersistenceManager(), sucursalId);
	}
	
	public List<Factura> darFacturasPorIdUser(long idUser)
	{
		return sqlFactura.darFacturasPorIdUser(pmf.getPersistenceManager(), idUser);
	}
	
	public Factura darFacturaPorId(long id)
	{
		return sqlFactura.darFacturaPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<ProductoFactura> darProductosFacturaPorIdFactura(long idFactura)
	{
		return sqlProductoFactura.darProductosPorIdFactura(pmf.getPersistenceManager(), idFactura);
	}
	
	public List<OfrecidoProveedor> darOfrecidosProveedorPorNITProveedor(int nitProveedor)
	{
		return sqlOfrecidoProveedor.darOfrecidosPorNitProveedor(pmf.getPersistenceManager(), nitProveedor);
	}
	
	public List<OfrecidoSucursal> darOfrecidosSucursalPorSucursalId(long idSucursal)
	{
		return sqlOfrecidoSucursal.darOfrecidosPorIdSucursal(pmf.getPersistenceManager(), idSucursal);
	}
	
	public List<ProductoFisico> darProductosFisicosPorIdContenedor(long idContenedor)
	{
		return sqlProductoFisico.darProductosFisicosPorIdContenedor(pmf.getPersistenceManager(), idContenedor);
	}
	
	public List<Carrito> darCarritos()
	{
		return sqlCarrito.darCarritos(pmf.getPersistenceManager());
	}
	
	public Carrito darCarritoPorUsuarioId(long usuarioId)
	{
		return sqlCarrito.darCarritoPorUsuarioId(pmf.getPersistenceManager(), usuarioId);
	}
	
	public List<ProductoFisico> darProductosFisicosPorCarritoId(long carritoId)
	{
		return sqlProductoFisico.darProductosPorCarritoId(pmf.getPersistenceManager(), carritoId);
	}
	
	
	/**
	 * Método que agrega una factura a la base de datos.
	 * @param idUser
	 * @param costoTotal
	 * @return
	 */
	public Factura agregarFactura( int docCliente, long idSucursal, double costoTotal, Date fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextVal();
			long tuplasInsertadas = sqlFactura.agregarTupla(pm, id, docCliente, idSucursal, costoTotal, dateFormat.format(fecha));
			List<ProductoFisico> productosCarrito = this.darProductosFisicosPorCarritoClienteYSucursal(pmf.getPersistenceManager(), docCliente, idSucursal);
			long productosModificados = agregarProductosFactura(id, productosCarrito);
			tx.commit();
			
			log.trace("Inserción de Factura: " + id + ": " + tuplasInsertadas + "tuplas insertadas.");
			return new Factura(id, docCliente, idSucursal, costoTotal, fecha);
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
	
	/**
	 * Método que elimina del carrito y persiste en la factura cada uno de los productos del carrito.
	 * @param idFactura
	 * @param productos
	 * @return
	 */
	private long agregarProductosFactura(long idFactura, List<ProductoFisico> productos)
	{
		long tuplasModificadas = 0;
		for(ProductoFisico productoActual: productos)
		{
			tuplasModificadas += sqlProductoCarrito.eliminarTupla(pmf.getPersistenceManager(), productoActual.getId());
			tuplasModificadas += sqlProductoFactura.agregarTupla(pmf.getPersistenceManager(), idFactura, productoActual.getId());
		}
		return tuplasModificadas/2;
	}
	
	/**
	 * Método que cambia un producto físico de un contenedor a un carrito.
	 * @param productoFisicoId
	 * @param carritoId
	 * @return
	 */
	public long cambiarProductoACarrito(long idProductoFisico, long idCarrito)
	{
		long resultadoQuery = -1;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try
		{
			tx.begin();
			long idContenedor = sqlProductoContenedor.darIdContenedorPorIdProducto(pmf.getPersistenceManager(), idProductoFisico);
			resultadoQuery = sqlProductoContenedor.eliminarTupla(pmf.getPersistenceManager(), idProductoFisico);
			int cantidadMedida = darProductoAbstractoPorProductoFisico(pmf.getPersistenceManager(), idProductoFisico).getCantidadMedida();
			this.cambiarCapacidadContenedor(idContenedor, cantidadMedida, false);
			
			sqlProductoCarrito.agregarTupla(pmf.getPersistenceManager(), idCarrito, idProductoFisico);
			tx.commit();
			return resultadoQuery;
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
			
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
	 * Cambiar un producto 
	 * @param productoFisicoId
	 * @param usuarioId
	 * @return
	 */
	public long cambiarProductosAContenedor(long idCarrito, long idSucursal, List<ProductoFisico> productos)
	{
		long resultadoQuery = -1;
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			for(ProductoFisico productoActual: productos)
			{
				sqlProductoCarrito.eliminarTupla(pmf.getPersistenceManager(), productoActual.getId());
				List<Contenedor> contenedoresPorCategoriaProducto = darContenedoresPorCategoriaProducto(pmf.getPersistenceManager(), idSucursal, productoActual.getId(), sqlProductoFisico.ESTANTE);
				agregarProductoAContenedor(productoActual, contenedoresPorCategoriaProducto);
				sqlProductoFisico.cambiarEstado(pmf.getPersistenceManager(), productoActual.getId(), sqlProductoFisico.ESTANTE);	
			}
			
			tx.commit();
			return resultadoQuery;
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
			
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
	 * Método que agrega un carrito a un contenedor.
	 * @param producto
	 * @param contenedores
	 * @return
	 */
	private long agregarProductoAContenedor(ProductoFisico producto, List<Contenedor> contenedores)
	{
		ProductoAbstracto abstracto = this.darProductoAbstractoPorProductoFisico(pmf.getPersistenceManager(), producto.getId());
		int ocupacionProducto = abstracto.getCantidadMedida();
		long idContenedor = -1;
		for(Contenedor contenedorActual: contenedores)
			if(cambiarCapacidadContenedor(contenedorActual.getId(), ocupacionProducto, true))
				return sqlProductoContenedor.agregarTupla(pmf.getPersistenceManager(), idContenedor, producto.getId());
			
		return -1;
	}
	
	/**
	 * Cambia la capacidad de un contenedor.
	 * @param idContenedor
	 * @param producto
	 * @param sumar
	 */
	private boolean cambiarCapacidadContenedor( long idContenedor, int cantidadMedida, boolean sumar)
	{
		
		try
		{
			
			Contenedor contenedor = sqlContenedor.darContenedorPorId(pmf.getPersistenceManager(), idContenedor);
			int capacidadOcupada = sumar? contenedor.getCapacidadOcupada() + cantidadMedida : contenedor.getCapacidadOcupada() - cantidadMedida;
			
			if(capacidadOcupada < 0  || capacidadOcupada > contenedor.getCapacidad())
				return false;
			sqlContenedor.cambiarCapacidadOcupada(pmf.getPersistenceManager(), capacidadOcupada, idContenedor);
			return true;
		}catch(Exception e)
		{
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return false;
		}
	}
	
	
	public List<ProductoFisico> darProductosPorClienteYSucursal(int docCliente, long idSucursal)
	{
		return this.darProductosFisicosPorCarritoClienteYSucursal(pmf.getPersistenceManager(), docCliente, idSucursal);
	}
	//métodos configuración
	
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
	
	/**
	 * Metodo que retorna los carritos abandonados.
	 * @param idSucursal
	 * @return
	 */
	public List<Carrito> darCarritosAbandonadosEnSucursal(long idSucursal)
	{
		return darCarritosAbandonados(pmf.getPersistenceManager(), idSucursal);
	}
	
	public List<Cliente> darClientes()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
	/**
	 * Método que retorna los productos pertenecientes a un carrito.
	 * @param idCarrito
	 * @return
	 */
	public List<ProductoFisico> darProductosDeCarrito(long idCarrito)
	{
		return this.darProductosFisicosPorCarrito(pmf.getPersistenceManager(), idCarrito);
	}
	
	//Requerimiento func consulta 10
	public List<Cliente> consultarConsumo(String criterio, Date fecha_inicial, Date fecha_final, long idAbstracto)
	{
		return this.consultarConsumo(pmf.getPersistenceManager(),criterio, dateFormat.format(fecha_inicial), dateFormat.format(fecha_final), idAbstracto);
	}
	
	//Requerimiento func consulta 11
	public List<Cliente> consultarConsumo2(String criterio, Date fecha_inicial, Date fecha_final, long idAbstracto)
	{
		return this.consultarConsumo2(pmf.getPersistenceManager(),criterio, dateFormat.format(fecha_inicial), dateFormat.format(fecha_final), idAbstracto);
	}
	
	//Requerimiento func consulta 12
	
	
	//Sql's de tablas combinadas.
	private ProductoAbstracto darProductoAbstractoPorProductoFisico(PersistenceManager pm, long idProductoFisico)
	{
		String sql = "Select pa.* From "
				+ darTablaProductoAbstracto() +" pa Inner Join " + darTablaProductoFisico() + " pf "
				+"On pa.id = pf.idAbstracto "
				+"Where pf.id = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoAbstracto.class);
		q.setParameters(idProductoFisico);
		return (ProductoAbstracto) q.executeUnique();
	}
	
	private List<Contenedor> darContenedoresPorCategoriaProducto(PersistenceManager pm, long idSucursal, long idProductoFisico, String tipoContenedor)
	{
		String sql = "Select cont.*"
				+ "From " + darTablaProductoFisico() + " pf Inner Join " + darTablaProductoAbstracto() + " pa"
				+" On pf.idAbstracto = pa.id"
				+" Inner Join " + darTablaTipoProducto() + " tp"
				+" On pa.idTipo = tp.id"
				+" Inner Join " + darTablaCategoria() + " cat"
				+" On tp.categoria = cat.nombre"
				+" Inner Join " + darTablaContenedor() + " cont"
				+" On cont.categoria = cat.nombre"
				+" Where cont.idSucursal = ? And pf.id = ? AND cont.tipo = ?";
		
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(Contenedor.class);
		q.setParameters(idSucursal, idProductoFisico, tipoContenedor);
		return (List<Contenedor>) q.executeList();
	}
	
	private List<Carrito> darCarritosAbandonados(PersistenceManager pm, long idSucursal)
	{
		
		String sql = "Select ca.*"
				+" From " + darTablaCarrito() +" ca Inner Join " + darTablaClienteCarrito() +" cc"
				+" On ca.id = cc.idCarrito"
				+" Inner Join + " + darTablaCliente() +" c" 
				+" On cc.docCliente = c.documento"
				+" Where ca.ocupado = 'si' AND ca.idSucursal = ? AND c.conectado = 'no'";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(Carrito.class);
		q.setParameters(idSucursal);
		return (List<Carrito>) q.executeList();
	}
	
	private List<ProductoFisico> darProductosFisicosPorCarrito(PersistenceManager pm, long idCarrito)
	{
		String sql = "Select pf.*"
				+" From " + darTablaProductoFisico() +" pf Inner Join " + darTablaProductoCarrito()+ " pc"
				+" On pf.id = pc.idProducto"
				+" Where pc.idCarrito = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoFisico.class);
		q.setParameters(idCarrito);
		return q.executeList();
	}
	
	private List<ProductoFisico> darProductosFisicosPorCarritoClienteYSucursal(PersistenceManager pm, long docCliente, long idSucursal)
	{
		String sql = "Select pf.*"
				+ " From " + darTablaProductoFisico() + " pf Inner Join " + darTablaProductoCarrito() + " pc"
				+ " On pf.id = pc.idProductoFisico"
				+ " Inner Join " + darTablaClienteCarrito() + " cc"
				+ " On pc.idCarrito = cc.idCarrito"
				+ " Inner Join " + darTablaCarrito() + " c"
				+ " On c.id = cc.idCarrito"
				+ " Where cc.docCliente = ? And c.idSucursal = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(ProductoFisico.class);
		q.setParameters(docCliente, idSucursal);
		return q.executeList();
	}
	
	private double darPrecioPorIdAbstractoYIdSucursal(PersistenceManager pm, long idAbstracto, long idSucursal)
	{
		String sql = "Select os.precio"
				+ " From " + darTablaProductoAbstracto() + " pa Inner Join " + darTablaOfrecidoSucursal() + " os"
				+ " On pa.id = os.idAbstracto"
				+ " Where idAbstracto = ? And os.idSucursal = ?";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idAbstracto, idSucursal);
		return (double) q.executeUnique();
	}
	
	
	
	/***********************************************************
	 * Requerimientos funcionales de consulta iteración 3
	 ***********************************************************/
	
	
	//Req Func Consulta 10
	
	private List<Cliente> consultarConsumo(PersistenceManager pm, String criterio, String fecha_inicial, String fecha_final, long idAbstracto)
	{
		
		String sql = "Select Unique c.*"
				+ " From " + darTablaCliente() + " c Inner Join  " + darTablaFactura() + " f" 
				+ " On c.documento = f.docCliente"
				+ " Inner Join " + darTablaProductoFactura() +  " pf"
				+ " On f.id = pf.idFactura"
				+ " Inner Join " + darTablaProductoFisico() + " pfi"
				+ " On pf.idProductoFisico = pfi.id"
				+ " Where f.fecha >= TO_DATE( ? , 'dd-MM-yyyy') AND"
				+ " f.fecha <= TO_DATE( ? , 'dd-MM-yyyy') AND pfi.idAbstracto = ? ";
		String sqlCriterio = "";
		if(criterio.equals("datos_cliente"))
			sqlCriterio = " Order By c.documento";
		Query q = pm.newQuery(SQL, sql + sqlCriterio);
		q.setResultClass(Cliente.class);
		q.setParameters(fecha_inicial, fecha_final, idAbstracto);
		System.out.println("La consulta ****************************** <----");
		System.out.println(q.toString());
		return (List<Cliente>) q.executeList();
	}
	
	
	private List<Cliente> consultarConsumo2(PersistenceManager pm, String criterio, String fecha_inicial, String fecha_final, long idAbstracto)
	{
		String sql = "(Select cl.* "
				+ " From " + darTablaCliente() + " cl" 
				+ " minus"
				+ " (Select Unique c.*"
				+ " From " + darTablaCliente() + " c Inner Join " + darTablaFactura() + " f"
				+ " On c.documento = f.docCliente"
				+ " Inner Join " + darTablaProductoFactura() + " pf"
				+ " On f.id = pf.idFactura"
				+ " Inner Join " + darTablaProductoFisico() + " pfi"
				+ " On pf.idProductoFisico = pfi.id"
				+ " Where f.fecha >= TO_DATE ( ? , 'dd-MM-yyyy') AND"
				+ " f.fecha <= TO_DATE( ? , 'dd-MM-yyyy')  AND pfi.idAbstracto = ?)) ";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(Cliente.class);
		q.setParameters(fecha_inicial, fecha_final, idAbstracto);
		
		return (List<Cliente>) q.executeList();
	}
	
	private void consultarFuncionamiento()
	{
		
	}
	
	
}
