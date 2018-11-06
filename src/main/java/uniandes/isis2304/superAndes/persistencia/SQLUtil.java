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
		Query qUser = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaUser());
		Query qPersona = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPersona());
		Query qEmpresa = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaEmpresa());
		Query qCiudad = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCiudad());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaSucursal());
		Query qProveedorSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProveedorSucursal());
		Query qCategoria = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaCategoria());
		Query qProductoAbstracto = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoAbstracto());
		Query qOfrecidoSucursal = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOfrecidoSucursal());
		Query qOfrecidoProveedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaOfrecidoProveedor());
		Query qProductoFisico = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoFisico());
		Query qContenedor = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaContenedor());
		Query qPedido = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPedido());
		Query qProductoPedido = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaProductoPedido());
		Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromocion());
		Query qPromocionPorCantidadOUnidad = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromocionPorCantidadOUnidad());
		Query qPromocionPorcentajeDescuento = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromocionPorcentajeDescuento());
		Query qPromocionPaqueteProductos = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaPromocionPaqueteProductos());
		Query qFactura = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaFactura());
		Query qItemFactura = pm.newQuery(SQL, "DELETE FROM " + psa.darTablaItemFactura());
		
		long userEliminados = (long) qUser.executeUnique();
		long personaEliminados = (long) qPersona.executeUnique();
		long empresaEliminados = (long) qEmpresa.executeUnique();
		long ciudadEliminados = (long) qCiudad.executeUnique();
		long sucursalEliminados = (long) qSucursal.executeUnique();
		long proveedorSucursalEliminados = (long) qProveedorSucursal.executeUnique();
		long categoriaEliminados = (long) qCategoria.executeUnique();
		long productoAbstractoEliminados = (long) qProductoAbstracto.executeUnique();
		long ofrecidoSucursalEliminados = (long) qOfrecidoSucursal.executeUnique();
		long ofrecidoProveedorEliminados = (long) qOfrecidoProveedor.executeUnique();
		long productoFisicoEliminados = (long) qProductoFisico.executeUnique();
		long contenedorEliminados = (long) qContenedor.executeUnique();
		long pedidoEliminados = (long) qPedido.executeUnique();
		long productoPedidoEliminados = (long) qProductoPedido.executeUnique();
		long promocionEliminados = (long) qPromocion.executeUnique();
		long promocionPorCantidadOUnidadEliminados = (long) qPromocionPorCantidadOUnidad.executeUnique();
		long promocionPorcentajeDescuentoEliminados = (long) qPromocionPorcentajeDescuento.executeUnique();
		long promocionPaqueteProductosEliminados = (long) qPromocionPaqueteProductos.executeUnique();
		long facturaEliminados = (long) qFactura.executeUnique();
		long itemFacturaEliminados = (long) qItemFactura.executeUnique();
		
		return new long[] {userEliminados,personaEliminados,empresaEliminados,ciudadEliminados,sucursalEliminados,proveedorSucursalEliminados,
				categoriaEliminados, productoAbstractoEliminados, ofrecidoSucursalEliminados, ofrecidoProveedorEliminados, productoFisicoEliminados,
				contenedorEliminados, pedidoEliminados, productoPedidoEliminados, promocionEliminados, promocionPorCantidadOUnidadEliminados, 
				promocionPorcentajeDescuentoEliminados, promocionPaqueteProductosEliminados, facturaEliminados, itemFacturaEliminados};
	}
}
