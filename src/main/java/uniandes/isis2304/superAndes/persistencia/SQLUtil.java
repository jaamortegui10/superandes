package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUtil {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private static final String SQL = PersistenciaSuperAndes.SQL;
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación.
	 */
	private PersistenciaSuperAndes psa;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor.
	 * @param psa
	 */
	public SQLUtil(PersistenciaSuperAndes psa)
	{
		this.psa = psa;
	}
	
	/**
	 * Crea y ejecuta la sencentia sql para obtener un nuevo número de secuencia.
	 * @param pm
	 * @return
	 */
	public long nextval(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT " + psa.darSeqSuperAndes() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}
	
	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE
	 * @param pm
	 * @return
	 */
	public long[] limpiarSuperAndes(PersistenceManager pm)
	{
		Query qCarrito = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCarrito());
		Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria());
		Query qCiudad = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCiudad());
		Query qCliente = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCliente());
		Query qClienteCarrito = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPorcentajeDescuento());
		Query qContenedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaContenedor());
		Query qFactura = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura());
		Query qOfrecidoProveedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOfrecidoProveedor());
		Query qOfrecidoSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOfrecidoSucursal());
		Query qPedido = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPedido());
		Query qProductoAbstracto = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoAbstracto());
		Query qProductoCarrito = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoCarrito());
		Query qProductoContenedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoContenedor());
		Query qProductoFactura = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoFactura());
		Query qProductoFisico = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoFisico());
		Query qProductoPedido = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoPedido());
		Query qPromPaqueteProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPaqueteProductos());
		Query qPromPorCantidadOUnidad = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPorCantidadOUnidad());
		Query qPromPorcentajeDescuento = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromPorcentajeDescuento());
		Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedor());
		Query qProveedorSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedorSucursal());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal());
		Query qTipoProducto = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaTipoProducto());

		long carritoEliminados = (long) qCarrito.executeUnique();
		long categoriaEliminados = (long) qCategoria.executeUnique();
		long ciudadEliminados = (long) qCiudad.executeUnique();
		long clienteEliminados = (long) qCliente.executeUnique();
		long clienteCarritoEliminados = (long) qClienteCarrito.executeUnique();
		long contenedorEliminados = (long) qContenedor.executeUnique();
		long facturaEliminados = (long) qFactura.executeUnique();
		long ofrecidoProveedorEliminados = (long) qOfrecidoProveedor.executeUnique();
		long ofrecidoSucursalEliminados = (long) qOfrecidoSucursal.executeUnique();
		long pedidoEliminados = (long) qPedido.executeUnique();
		long productoAbstractoEliminados = (long) qProductoAbstracto.executeUnique();
		long productoCarritoEliminados = (long) qProductoCarrito.executeUnique();
		long productoContenedorEliminados = (long) qProductoContenedor.executeUnique();
		long productoFacturaEliminados = (long) qProductoFactura.executeUnique();
		long productoFisicoEliminados = (long) qProductoFisico.executeUnique();
		long productoPedidoEliminados = (long) qProductoPedido.executeUnique();
		long promPaqueteProductosEliminados = (long) qPromPaqueteProductos.executeUnique();
		long promPorCantidadOUnidadEliminados = (long) qPromPorCantidadOUnidad.executeUnique();
		long promPorcentajeDescuentoEliminados = (long) qPromPorcentajeDescuento.executeUnique();
		long proveedorEliminados = (long) qProveedor.executeUnique();
		long proveedorSucursalEliminados = (long) qProveedorSucursal.executeUnique();
		long sucursalEliminados = (long) qSucursal.executeUnique();
		long tipoProductoEliminados = (long) qTipoProducto.executeUnique();

		return new long[] {carritoEliminados,categoriaEliminados,ciudadEliminados,clienteEliminados,clienteCarritoEliminados,proveedorSucursalEliminados,
				categoriaEliminados, productoAbstractoEliminados, ofrecidoSucursalEliminados, ofrecidoProveedorEliminados, productoFisicoEliminados,
				contenedorEliminados, pedidoEliminados, productoPedidoEliminados, productoCarritoEliminados, 
				productoContenedorEliminados, productoFacturaEliminados, facturaEliminados, promPaqueteProductosEliminados,
				promPorCantidadOUnidadEliminados, promPorcentajeDescuentoEliminados, proveedorEliminados, proveedorSucursalEliminados,
				sucursalEliminados, tipoProductoEliminados};
	}
}
