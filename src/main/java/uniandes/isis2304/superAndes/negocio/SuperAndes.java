package uniandes.isis2304.superAndes.negocio;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

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
	

	
	public Carrito agregarCarrito( long idUser)
	{
		log.info("Agregando Carrito: " + idUser);
		Carrito carrito = psa.agregarCarrito(idUser);
		log.info("Agregando Carrito:" + carrito);
		return carrito;
	}
	
	public Categoria agregarCategoria(String nombre, String caracteristicas, String almacenamiento, String manejo)
	{
		log.info("Agregando Categoria:" + nombre);
		Categoria categoria = psa.agregarCategoria(nombre, caracteristicas, almacenamiento, manejo);
		log.info("Agregando Categoria:" + categoria);
		return categoria;
	}
	
	public Ciudad agregarCiudad(String nombre)
	{
		log.info("Agregando Ciudad:" + nombre );
		Ciudad ciudad = psa.agregarCiudad(nombre);
		log.info("Agregando Ciudad:" + ciudad);
		return ciudad;
	}
	
	
	/*******************
	 * RF5, RF6
	 *******************/
	/**
	 * Se agrega un nuevo contenedor.
	 * @param sucursalId
	 * @param tipo
	 * @param capacidad
	 * @param capacidadOcupada
	 * @return
	 */
	public Contenedor agregarContenedor( long sucursalId, String tipo, int capacidad, int capacidadOcupada)
	{
		log.info("Agregando Contenedor:" );
		Contenedor contenedor = psa.agregarContenedor(sucursalId, tipo, capacidad, capacidadOcupada);
		log.info("Agregando Contenedor:");
		return contenedor;
		
	}
	
	
	/*******************
	 * RF1
	 *******************/
	
	/**
	 * Se agrega una nueva empresa.
	 * @param nit
	 * @param idUser
	 * @param direccion
	 * @param puntosCompras
	 * @param tipoEmpresa
	 * @return
	 */
	public Empresa agregarEmpresa( int nit, long idUser, String direccion, int puntosCompras, String tipoEmpresa )
	{
		log.info("Agregando Empresa: " + nit);
		Empresa empresa = null;
		if(tipoEmpresa.equals(Empresa.PROVEEDOR))
			empresa = psa.agregarProveedor( nit, idUser, direccion, tipoEmpresa);
		else
			empresa = psa.agregarEmpresaCliente( nit, idUser, direccion, puntosCompras, tipoEmpresa);
		log.info("Agregando Empresa: " + empresa);
		return empresa;
	}
	
	public Factura agregarFactura( long idUser, double costoTotal)
	{
		log.info("Agregando Factura: " + idUser);
		Factura factura = psa.agregarFactura(idUser, costoTotal);
		log.info("Agregando Factura: " + factura);
		return factura;
	}
	
    public ItemFactura agregarItemFactura(  long idFactura, long idProductoOfrecido, int cantidad, double costo)
    {
    	log.info("Agregando ItemFactura: " + idFactura);
    	ItemFactura itemFactura = psa.agregarItemFactura(idFactura, idProductoOfrecido, cantidad, costo);
		log.info("Agregando : " + itemFactura);
		return itemFactura;
    }
    
    
    /*******************
	 * RF2.2.1
	 *******************/
    /**
     * Se agrega un nuevo producto ofrecido por sucursal.
     * @param idAbstracto
     * @param idSucursal
     * @param precio
     * @return
     */
    public OfrecidoSucursal agregarOfrecidoSucursal( long idAbstracto, long idSucursal, double precio)
    {
    	log.info("Agregando OfrecidoSucursal: " + idAbstracto + "," + idSucursal);
    	OfrecidoSucursal ofrecidoSucursal = psa.agregarOfrecidoSucursal( idAbstracto, idSucursal, precio);
		log.info("Agregando OfrecidoSucursal: " + ofrecidoSucursal);
		return ofrecidoSucursal;
    }
    
    /*******************
	 * RF2.2.2
	 *******************/
    /**
     * Se agrega un nuevo producto ofrecido por proveedor
     * @param idAbstracto
     * @param precio
     * @param nitProveedor
     * @return
     */
    public OfrecidoProveedor agregarOfrecidoProveedor(  long idAbstracto, double precio, int nitProveedor)
    {
    	log.info("Agregando OfrecidoProveedor :" + idAbstracto + "," + nitProveedor);
    	OfrecidoProveedor ofrecidoProveedor = psa.agregarOfrecidoProveedor(idAbstracto, precio, nitProveedor);
		log.info("Agregando OfrecidoProveedor: " + ofrecidoProveedor);
		return ofrecidoProveedor;
    }
    
   
    
    public Pedido agregarPedido(  long idSucursal, int nitProveedor, double precio, String fechaEntrega)
    {
    	log.info("Agregando Pedido: " + idSucursal + "," + nitProveedor);
    	Pedido pedido = psa.agregarPedido(idSucursal, nitProveedor, precio, fechaEntrega);
		log.info("Agregando Pedido: " + pedido);
		return pedido;
    }
    
    
    /*******************
	 * RF3
	 *******************/
    /**
     * Se agrega una nueva persona.
     * @param cedula
     * @param idUser
     * @param puntos
     * @param idSucursal
     * @param tipoPersona
     * @return
     */
    public Persona agregarPersona(  int cedula, long idUser,  int puntos, long idSucursal, String tipoPersona)
    {
    	log.info("Agregando Persona: " + cedula);
    	Persona persona = null;
    	if(tipoPersona.equals(Persona.TIPO_CLIENTE))
			persona = psa.agregarPersonaCliente( cedula, idUser, puntos, tipoPersona);
		else
			persona = psa.agregarTrabajadorSucursal(cedula, idUser, idSucursal, tipoPersona);
    	
		log.info("Agregando Persona: " + persona);
		return persona;
    }
    
    
    /*******************
	 * RF2.1
	 *******************/
    
    /**
     * Se agrega un nuevo producto abstracto.
     * @param nombre
     * @param tipo
     * @param unidadMedida
     * @param categoria
     * @return
     */
    public ProductoAbstracto agregarProductoAbstracto( String nombre, String tipo, String unidadMedida, String categoria)
    {
    	log.info("Agregando ProductoAbstracto: " + nombre);
    	ProductoAbstracto productoAbstracto = psa.agregarProductoAbstracto(nombre, tipo, unidadMedida, categoria);
		log.info("Agregando ProductoAbstracto: " + productoAbstracto); 
		return productoAbstracto;
    }
    
    /*******************
	 * RF2.3
	 *******************/
    
    /**
     * 
     * @param idOfrecido
     * @param cantidadMedida
     * @param codigoBarras
     * @param idContenedor
     * @return
     */
    public ProductoFisico agregarProductoFisico( long idOfrecido, int cantidadMedida, String codigoBarras, long idContenedor)
    {
    	log.info("Agregando ProductoFisico: " + idOfrecido + "," + cantidadMedida);
    	ProductoFisico productoFisico = psa.agregarProductoFisico(idOfrecido, cantidadMedida, codigoBarras, idContenedor);
		log.info("Agregando ProductoFisico: " + productoFisico);
		return productoFisico;
    }
    
    public ProductoPedido agregarProductoPedido(  long idPedido, long idProductoOfrecido, int cantidad)
    {
    	log.info("Agregando ProductoPedido: " + idPedido + "," + idProductoOfrecido);
    	ProductoPedido productoPedido = psa.agregarProductoPedido(idPedido, idProductoOfrecido, cantidad);
		log.info("Agregando ProductoPedido: " + productoPedido);
		return productoPedido;
    }
    
    public Promocion agregarPromocion( long idSucursal, String descripcion, String tipo)
    {
    	log.info("Agregando Promocion: " + idSucursal + "," + tipo);
    	Promocion promocion = psa.agregarPromocion(idSucursal, descripcion, tipo);
		log.info("Agregando Promocion: " + promocion);
		return promocion;
    }
    
    public PromocionPaqueteProductos agregarPromocionPaqueteProductos(  long idPromocion, double precio, long idProductoOfrecido1, long idProductoOfrecido2)
    {
    	log.info("Agregando PromocionPaqueteProductos: " + idPromocion);
    	PromocionPaqueteProductos promocionPaqueteProductos = psa.agregarPromocionPaqueteProductos(idPromocion, precio, idProductoOfrecido1, idProductoOfrecido2);
		log.info("Agregando PromocionPaqueteProductos: " + promocionPaqueteProductos);
		return promocionPaqueteProductos;
    }
    
    public PromocionPorCantidadOUnidad  agregarPromocionPorCantidadOUnidad(  long idOfrecido, long idPromocion, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas)
    {
    	log.info("Agregando PromocionPorCantidadOUnidad: " + idPromocion + "," + idOfrecido);
    	PromocionPorCantidadOUnidad promocionPorCantidadOUnidad = psa.agregarPromocionPorCantidadOUnidad(idOfrecido, idPromocion, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas);
		log.info("Agregando PromocionPorCantidadOUnidad: " + promocionPorCantidadOUnidad);
		return promocionPorCantidadOUnidad;
    }
    
    public PromocionPorcentajeDescuento agregarPromocionPorcentajeDescuento(  long idProductoOfrecido, long idPromocion, int porcentajeDescuento)
    {
    	log.info("Agregando PromocionPorcentajeDescuento: " + idPromocion + "," + idProductoOfrecido);
    	PromocionPorcentajeDescuento promocionPorcentajeDescuento = psa.agregarPromocionPorcentajeDescuento(idProductoOfrecido, idPromocion, porcentajeDescuento);
		log.info("Agregando PromocionPorcentajeDescuento: " + promocionPorcentajeDescuento);
		return promocionPorcentajeDescuento;
    }
    
    public ProveedorSucursal agregarProveedorSucursal(  long idSucursal, int nitProveedor)
    {
    	log.info("Agregando ProveedorSucursal: " );
    	ProveedorSucursal proveedorSucursal = psa.agregarProveedorSucursal(idSucursal, nitProveedor);
		log.info("Agregando ProveedorSucursal: ");
		return proveedorSucursal;
    }
    
    
    /*******************
	 * RF4
	 *******************/
    /**
     * Se agrega una nueva sucursal.
     * @param nombre
     * @param tamanho
     * @param direccion
     * @param nivelReorden
     * @param nivelReabastecimiento
     * @param idCiudad
     * @return
     */
    public Sucursal agregarSucursal( String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad)
    {
    	log.info("Agregando Sucursal: " + nombre);
    	Sucursal sucursal = psa.agregarSucursal(nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad);
		log.info("Agregando Sucursal: " + sucursal);
		return sucursal;
    }
    
    public User agregarUser( String password, String nombre, String correo, String tipo)
    {
    	log.info("Agregando User: " + nombre);
    	User user = psa.agregarUser(password, nombre, correo, tipo);
		log.info("Agregando User: " + user);
		return user;
    }
    
    
	
	
	
	
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
