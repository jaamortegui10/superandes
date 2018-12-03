package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	public final static String PROM_POR_CANTIDAD_O_UNIDAD = "por_cantidad_o_unidad";
	public final static String PROM_PORCENTAJE_DESCUENTO = "porcentaje_descuento";
	public final static String PROM_PAQUETE_PRODUCTOS = "paquete_productos";
	public final static String PROM_NINGUNA = "ninguna";
	
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
	
	public Carrito agregarCarrito( long docCliente, long idSucursal)
	{
		log.info("Agregando Carrito: " + docCliente + ", " + idSucursal);
		Carrito carrito = psa.agregarCarrito(docCliente, idSucursal);
		log.info("Agregando Carrito:" + carrito);
		return carrito;
	}
	
	public Categoria agregarCategoria(String nombre, String caracteristicas, String manejo)
	{
		log.info("Agregando Categoria:" + nombre);
		Categoria categoria = psa.agregarCategoria(nombre, caracteristicas, manejo);
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
	public Cliente agregarCliente( int doc, String nombre, String password, String dir, String tipo )throws Exception
	{
		log.info("Agregando Empresa: " + doc);
		System.out.println("Agregando en SuperAndes");
		//Comienza Transacción
		Cliente empresa = null;
		VOCliente cliente = psa.agregarCliente(doc, nombre, password, dir, tipo);
		int docCliente = cliente.getDocumento();
		System.out.println("Se agregó el cliente");
		
		//Termina Transacción
		log.info("Agregando Empresa terminado: " + empresa);
		return empresa;
	}
	
	public Factura agregarFactura( int docCliente, long idSucursal, double costoTotal)
	{
		log.info("Agregando Factura: " + docCliente);
		Date fecha = new Date();
		Factura factura = psa.agregarFactura(docCliente, idSucursal, costoTotal, fecha);
		log.info("Agregando Factura: " + factura);
		return factura;
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
    
   
    
    public Pedido agregarPedido(  long idSucursal, int nitProveedor, double precio, Date fechaEntrega)
    {
    	log.info("Agregando Pedido: " + idSucursal + "," + nitProveedor);
    	Pedido pedido = psa.agregarPedido(idSucursal, nitProveedor, precio, fechaEntrega);
		log.info("Agregando Pedido: " + pedido);
		return pedido;
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
    public ProductoAbstracto agregarProductoAbstracto( String nombre, String unidadMedida, int cantidadMedida, long idTipo)
    {
    	log.info("Agregando ProductoAbstracto: " + nombre);
    	ProductoAbstracto productoAbstracto = psa.agregarProductoAbstracto(nombre, unidadMedida, cantidadMedida, idTipo);
		log.info("Agregando ProductoAbstracto: " + productoAbstracto); 
		return productoAbstracto;
    }
    
    /*******************
	 * RF2.3
	 *******************/
    
   
    
    public ProductoPedido agregarProductoPedido(  long idPedido, long idProductoOfrecido, int cantidad)
    {
    	log.info("Agregando ProductoPedido: " + idPedido + "," + idProductoOfrecido);
    	ProductoPedido productoPedido = psa.agregarProductoPedido(idPedido, idProductoOfrecido, cantidad);
		log.info("Agregando ProductoPedido: " + productoPedido);
		return productoPedido;
    }
    
    public PromocionPaqueteProductos agregarPromocionPaqueteProductos(  double precio, long idProductoOfrecido1, long idProductoOfrecido2, long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	log.info("Agregando PromocionPaqueteProductos: " + idProductoOfrecido1 + ", " + idProductoOfrecido2 );
    	PromocionPaqueteProductos promocionPaqueteProductos = psa.agregarPromPaqueteProductos(precio, idProductoOfrecido1, idProductoOfrecido2, idSucursal, fecha_inicio, fecha_fin, descripcion);
		log.info("Agregando PromocionPaqueteProductos: " + promocionPaqueteProductos);
		return promocionPaqueteProductos;
    }
    
    public PromocionPorCantidadOUnidad  agregarPromocionPorCantidadOUnidad(  long idOfrecido, int cantidadOUnidadesPagadas, int cantidadOUnidadesCompradas, long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	log.info("Agregando PromocionPorCantidadOUnidad: " + idOfrecido + "," + cantidadOUnidadesPagadas + "," + cantidadOUnidadesCompradas);
    	PromocionPorCantidadOUnidad promocionPorCantidadOUnidad = psa.agregarPromPorCantidadOUnidad(idOfrecido, cantidadOUnidadesPagadas, cantidadOUnidadesCompradas, idSucursal, fecha_inicio, fecha_fin, descripcion);
		log.info("Agregando PromocionPorCantidadOUnidad: " + promocionPorCantidadOUnidad);
		return promocionPorCantidadOUnidad;
    }
    
    public PromocionPorcentajeDescuento agregarPromocionPorcentajeDescuento(  long idProductoOfrecido, int porcentajeDescuento, long idSucursal, Date fecha_inicio, Date fecha_fin, String descripcion)
    {
    	log.info("Agregando PromocionPorcentajeDescuento: " + idProductoOfrecido + "," + porcentajeDescuento);
    	PromocionPorcentajeDescuento promocionPorcentajeDescuento = psa.agregarPromPorcentajeDescuento(idProductoOfrecido, porcentajeDescuento, idSucursal, fecha_inicio, fecha_fin, descripcion);
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
    public Sucursal agregarSucursal( String nombre, int tamanho, String direccion, int nivelReorden, int nivelReabastecimiento, long idCiudad, String password)
    {
    	log.info("Agregando Sucursal: " + nombre);
    	Sucursal sucursal = psa.agregarSucursal(nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password);
		log.info("Agregando Sucursal: " + sucursal);
		return sucursal;
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
	
	public List<VOCliente> darClientesPorTipoCliente(String tipo)
	{
		log.info ("Generando los VO de las personas de un tipo dado.");        
        List<VOCliente> voClientes = new LinkedList<VOCliente> ();
        for (VOCliente c : psa.darClientesPorTipo(tipo))
        {
        	voClientes.add (c);
        }
        log.info ("Generados los VO de Tipos de bebida: " + voClientes.size() + " existentes");
        return voClientes;
	}
	
	public List<VOCliente> darClientes()
	{
		List<VOCliente> voClientes = new LinkedList<VOCliente>();
		for(VOCliente c: psa.darClientes())
			voClientes.add(c);
		return voClientes;
			
	}
	
	public List<VOProveedor> darProveedores()
	{
		System.out.println("en SuperAndes buscando proveedores.");
		log.info ("Generando los VO de las enpresas de un tipo dado.");        
        List<VOProveedor> voEmpresas = new LinkedList<VOProveedor> ();
        System.out.println("En Superandes");
        for (VOProveedor p : psa.darProveedores() )
        {
        	System.out.println("Empresa Actual: " + p.getNit() + ": " + p.getNit());
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
	
	/**
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
	*/
	
	public long[] limpiarSuperAndes()
	{
		 log.info ("Limpiando la BD de SuperAndes");
	     long [] borrrados = psa.limpiarSuperAndes();
	     log.info ("Limpiando la BD de SuperAndes: Listo!");
	     return borrrados;
	}
	
	
	
	
	/***************************************************
	 * Requerimientos Funcionales de Consulta
	 ***************************************************/
	public List<Cliente> consultarConsumo(String criterio, Date fecha_inicial, Date fecha_final, long idAbstracto)
	{
		
		return  psa.consultarConsumo(criterio, fecha_inicial, fecha_final, idAbstracto);
	}
	
	public List<VOCliente> consultarConsumo2(String criterio, Date fecha_inicial, Date fecha_final, long idAbstracto)
	{
		List<Cliente> clientes = psa.consultarConsumo2(criterio, fecha_inicial, fecha_final, idAbstracto);
		System.out.println(clientes != null? "En Logica: tamaño lista: " + clientes.size() + ( clientes.size() > 0? ", Elemento 1: " + clientes.get(0).getDocumento() : "Lista vacía: " )  : "Lista null");

		List<VOCliente> voClientes = new LinkedList<VOCliente>();
		
		for(VOCliente c: psa.consultarConsumo2(criterio, fecha_inicial, fecha_final, idAbstracto))
			voClientes.add(c);
		return voClientes;
	}

}
