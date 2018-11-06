package uniandes.isis2304.superAndes.negocio;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	public final static String CLIENTE_PERSONA= "cliente persona";
	public final static String CLIENTE_EMPRESA= "cliente empresa";
	public final static String PROVEEDOR = "proveedor";
	public final static String TRABAJADOR_SUCURSAL = "trabajador sucursal";
	
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
	public Contenedor agregarContenedor( long sucursalId, String tipo, int capacidad, int capacidadOcupada, String tipoProducto)
	{
		log.info("Agregando Contenedor:" );
		Contenedor contenedor = psa.agregarContenedor(sucursalId, tipo, capacidad, capacidadOcupada, tipoProducto);
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
	public Empresa agregarEmpresa( int nit, String direccion, String password, String nombre, String correo, String tipoEmpresa )throws Exception
	{
		log.info("Agregando Empresa: " + nit);
		System.out.println("Agregando en SuperAndes");
		//Comienza Transacción
		Empresa empresa = null;
		VOUsuario usuario = agregarUsuario(password, nombre, correo, "empresa");
		long idUsuario = usuario.getId();
		System.out.println("Se agregó el usuario");
		if(tipoEmpresa.equals(Empresa.PROVEEDOR))
			empresa = psa.agregarProveedor( nit, idUsuario, direccion, tipoEmpresa);
		else if(tipoEmpresa.equals(Empresa.CLIENTE))
			empresa = psa.agregarEmpresaCliente( nit, idUsuario, direccion, tipoEmpresa);
		else
			throw new Exception("El tipo de la empresa no es aceptado.");
		//Termina Transacción
		log.info("Agregando Empresa terminado: " + empresa);
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
    public Persona agregarPersona(  int cedula, long idSucursal, String password, String nombre, String correo, String tipoPersona)throws Exception
    {
    	log.info("Agregando Persona: " + cedula);
    	
    	VOUsuario usuario = this.agregarUsuario(password, nombre, correo, "persona");
    	//Comienza Transacción
    	long idUsuario = usuario.getId();
    	Persona persona = null;
    	if(tipoPersona.equals(Persona.TIPO_CLIENTE))
			persona = psa.agregarPersonaCliente( cedula, idUsuario, tipoPersona);
		else if(tipoPersona.equals(Persona.TIPO_TRABAJADOR_SUCURSAL))
			persona = psa.agregarTrabajadorSucursal(cedula, idUsuario, idSucursal, tipoPersona);
		else 
			throw new Exception("El tipo de persona ingresado no es aceptado.");
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
    
    public Usuario agregarUsuario( String password, String nombre, String correo, String tipo)
    {
    	System.out.println("Agregando usuario.");
    	log.info("Agregando User: " + nombre);
    	Usuario usuario = psa.agregarUser(password, nombre, correo, tipo);
		log.info("Agregado User: " + usuario);
		return usuario;
    }
    
    
	
	
	
	
	public List<VOCategoria> darCategorias()
	{
		log.info("Consultando categorías.");
		List<VOCategoria> categorias = new LinkedList<VOCategoria>();
		for(VOCategoria c: psa.darCategorias())
		{
			categorias.add(c);
		}
		log.info("Consultadas categorías: " + categorias.size() + " existentes." );
		return categorias;
	}
	
	public List<VOPersona> darPersonasPorTipoPersona(String tipo)
	{
		log.info ("Generando los VO de las personas de un tipo dado.");        
        List<VOPersona> voPersonas = new LinkedList<VOPersona> ();
        for (Persona p : psa.darPersonasPorTipoPersona(tipo) )
        {
        	voPersonas.add (p);
        }
        log.info ("Generados los VO de Tipos de bebida: " + voPersonas.size() + " existentes");
        return voPersonas;
	}
	
	public List<VOEmpresa> darEmpresasPorTipoEmpresa(String tipo)
	{
		System.out.println("en SuperAndes buscando proveedores.");
		log.info ("Generando los VO de las enpresas de un tipo dado.");        
        List<VOEmpresa> voEmpresas = new LinkedList<VOEmpresa> ();
        System.out.println("En Superandes");
        for (VOEmpresa p : psa.darEmpresasPorTipoEmpresa(tipo) )
        {
        	System.out.println("Empresa Actual: " + p.getIdUser() + ": " + p.getNit());
        	voEmpresas.add (p);
        }
        log.info ("Generados los VO de las empresas de un tipo dado: " + voEmpresas.size() + " existentes");
        return voEmpresas;
	}
	
	public List<VOCiudad> darCiudades()
	{
		log.info ("Generando los VO de las ciudades");
		List<VOCiudad> voCiudades = new LinkedList<VOCiudad>();
		for(VOCiudad c : psa.darCiudades())
		{
			voCiudades.add(c);
		}
		log.info ("Generados los VO de las ciudades");
		return voCiudades;
	}
	
	public List<VOCarrito> darCarritos()
	{
		log.info ("Generando los VO de los carritos.");
		List<VOCarrito> voCarritos = new LinkedList<VOCarrito>();
		for(VOCarrito c: psa.darCarritos())
		{
			voCarritos.add(c);
		}
		log.info ("Generados los VO de los carritos.");
		return voCarritos;
	}
	
	public VOCarrito darCarritoPorUsuarioId(long usuarioId)
	{
		log.info ("Generando el VO del carrito");
		VOCarrito carrito = (VOCarrito) psa.darCarritoPorUsuarioId(usuarioId);
		//implementar los productos que tiene
		log.info ("Generando el VO del carrito");
		return carrito;
	}
	
	public List<VOProductoFisico> darProductosPorCarritoId(long carritoId)
	{
		log.info ("Generando los VO de los ProductosFisicos");
		List<VOProductoFisico> voProductos = new LinkedList<VOProductoFisico>();
		for(VOProductoFisico p: psa.darProductosFisicosPorCarritoId(carritoId)){
			voProductos.add(p);
		}
		log.info ("Generados el VO de los ProductosFisicos");
		return voProductos;
	}
	
	public long cambiarProductoACarrito(long productoFisicoId, long usuarioId)
	{
		VOCarrito carrito = darCarritoPorUsuarioId(usuarioId);
		long carritoId = carrito.getId();
		return psa.cambiarProductoACarrito(productoFisicoId, carritoId);
	}
	
	public long cambiarProductoAContenedor(long productoFisicoId, long usuarioId)
	{
		return psa.cambiarProductoAContenedor(productoFisicoId, usuarioId);
	}
	
	public long devolverCarrito(long usuarioId)
	{
		return psa.devolverCarrito(usuarioId);
	}
	
	public long ordenarCarritos()
	{
		return 1;
	}
	
	public List<VOContenedor> darContenedoresPorSucursalId(long sucursalId)
	{
		log.info ("Generando los VO de las contenedores de una sucursal dada.");
		List<VOContenedor> voContenedores = new LinkedList<VOContenedor>();
		for(VOContenedor c: psa.darContenedoresPorSucursalId(sucursalId))
		{
			voContenedores.add(c);
		}
		log.info ("Generados los VO de las contenedores de una sucursal dada.");
		return voContenedores;
	}
	
	//Falta implementar método en persistencia.
	public List<Contenedor> darContenedoresPorIdSucursalYTipo()
	{
		log.info ("Generando los VO de las contenedores de una sucursal dada y tipo dado.");
		List<VOContenedor> voContenedores = new LinkedList<VOContenedor>();
		log.info ("Generando los VO de las contenedores de una sucursal dada y tipo dado.");
		return null;
	}
	
	
	
	public List<VOFactura> darFacturasPorIdUser(long idUser)
	{
		log.info ("Generando los VO de las facturas de un usuario dado");
		List<VOFactura> voFacturas = new LinkedList<VOFactura>();
		for(VOFactura f: psa.darFacturasPorIdUser(idUser))
		{
			voFacturas.add(f);
		}
		log.info ("Generados los VO de las las facturas de un usuario dado");
		
		return voFacturas;
	}
	
	public List<VOOfrecidoProveedor> darOfrecidosProveedorPorNITProveedor(int nitProveedor)
	{
		log.info ("Generando los VO de los ofrecidos por un proveedor");
		List<VOOfrecidoProveedor> voOfrecidosProveedor = new LinkedList<VOOfrecidoProveedor>();
		for(VOOfrecidoProveedor o: psa.darOfrecidosProveedorPorNITProveedor(nitProveedor))
		{
			voOfrecidosProveedor.add(o);
		}
		log.info ("Generados los VO de los ofrecidos por un proveedor");
		
		return voOfrecidosProveedor;
	}
	
	public List<VOSucursal> darSucursales()
	{
		log.info ("Generando los VO de las sucursales");
		List<VOSucursal> voSucursales = new LinkedList<VOSucursal>();
		for(VOSucursal s: psa.darSucursales())
		{
			voSucursales.add(s);
		}
		log.info ("Generados los VO de las sucursales");
		return voSucursales;
	}
	
	public List<VOOfrecidoSucursal> darOfrecidosSucursalPorSucursalId(long idSucursal)
	{
		log.info ("Generando los VO de los ofrecidos de una sucursal dada.");
		List<VOOfrecidoSucursal> voOfrecidosSucursal = new LinkedList<VOOfrecidoSucursal>();
		for(VOOfrecidoSucursal o: psa.darOfrecidosSucursalPorSucursalId(idSucursal))
		{
			voOfrecidosSucursal.add(o);
		}
		log.info ("Generados los VO de los ofrecidos de una sucursal dada.");
		
		return voOfrecidosSucursal;
	}
	
	public List<VOProductoFisico> darProductosFisicosPorIdSucursal(long idSucursal)
	{
		log.info ("Generando los VO de las promociones de una sucursal dada.");
		List<VOContenedor> contenedores = this.darContenedoresPorSucursalId(idSucursal);
		List<VOProductoFisico> productos = new LinkedList<VOProductoFisico>();
		for(VOContenedor c: contenedores)
			for(VOProductoFisico p: psa.darProductosFisicosPorIdContenedor(c.getId()))
				productos.add(p);
		log.info ("Generados los VO de las promociones de una sucursal dada.");
		return productos;
	}
	
	public List<VOPromocion> darPromocionesPorSucursalId(long idSucursal)
	{
		log.info ("Generando los VO de las promociones de una sucursal dada.");
		List<VOPromocion> voPromociones = new LinkedList<VOPromocion>();
		for(VOPromocion p: psa.darPromocionesPorSucursalId(idSucursal))
		{
			voPromociones.add(p);
		}
		log.info ("Generados los VO de las promociones de una sucursal dada.");
		
		return voPromociones;
	}
	
	public long[] limpiarSuperAndes()
	{
		 log.info ("Limpiando la BD de SuperAndes");
	     long [] borrrados = psa.limpiarSuperAndes();
	     log.info ("Limpiando la BD de SuperAndes: Listo!");
	     return borrrados;
	}
	
}
