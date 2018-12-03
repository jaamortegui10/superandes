package uniandes.isis2304.superAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOCarrito;
import uniandes.isis2304.superAndes.negocio.VOCategoria;
import uniandes.isis2304.superAndes.negocio.VOCiudad;
import uniandes.isis2304.superAndes.negocio.VOCliente;
import uniandes.isis2304.superAndes.negocio.VOOfrecidoProveedor;
import uniandes.isis2304.superAndes.negocio.VOOfrecidoSucursal;
import uniandes.isis2304.superAndes.negocio.VOProductoAbstracto;
import uniandes.isis2304.superAndes.negocio.VOProductoFisico;
import uniandes.isis2304.superAndes.negocio.VOPromocionPaqueteProductos;
import uniandes.isis2304.superAndes.negocio.VOPromocionPorCantidadOUnidades;
import uniandes.isis2304.superAndes.negocio.VOPromocionPorcentajeDescuento;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOSucursal;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener {

	/*
	 * ****************************************************************
	 * Constantes
	 *****************************************************************/
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());

	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json";

	private static final String CONFIG_TABLAS = "./src/main/resources/config/tablasBD_A.json";

	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * Objecto json con los nombres de la tabla de la base de datos que se
	 * quieren utilizar.
	 */
	private JsonObject tableConfig;
	/**
	 * Cl�se principal de la l�gica de la app.
	 */
	private SuperAndes superAndes;

	/*
	 * ****************************************************************
	 * Atributos Interfaz
	 *****************************************************************/
	/**
	 * Objecto json con la configuraci�n
	 */
	private JsonObject guiConfig;
	/**
	 * Panel de los datos
	 */
	private PanelDatos panelDatos;
	/**
	 * Men� con las diferentes opciones del usuario dentro de la app.
	 */
	private JMenuBar menuBar;

	/*
	 * **************************************************************** M�todos
	 *****************************************************************/

	public InterfazSuperAndesApp() {
		// Carga la configuraci�n de la interfaz desde un archivo json
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gr�fica.
		configurarFrame();
		if (guiConfig != null) {
			crearMenu(guiConfig.getAsJsonArray("menuBar"));
		}
		tableConfig = openConfig("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos();

		setLayout(new BorderLayout());
		add(new JLabel(new ImageIcon(path)), BorderLayout.NORTH);
		add(panelDatos, BorderLayout.CENTER);

	}

	/*
	 * **************************************************************** M�todos
	 * de configuraci�n de la interfaz
	 *****************************************************************/
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} catch (Exception e) {
			// e.printStackTrace ();
			log.info("NO se encontr� un archivo de configuraci�n v�lido");
			JOptionPane.showMessageDialog(null,
					"No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Parranderos App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	private void configurarFrame() {
		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuraci�n por defecto");
			titulo = "SuperAndes APP default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuraci�n indicada en el archivo de configuraci�n");
			titulo = guiConfig.get("title").getAsString();
			alto = guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(50, 50);
		setResizable(true);
		setBackground(Color.WHITE);

		setTitle(titulo);
		setSize(ancho, alto);
		setVisible(true);
	}

	private void crearMenu(JsonArray jsonMenu) {
		// Creaci�n de la barra de men�s
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creaci�n de cada uno de los men�s
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creaci�n de cada una de las opciones del men�
				JsonObject jo = op.getAsJsonObject();
				String lb = jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem(lb);
				mItem.addActionListener(this);
				mItem.setActionCommand(event);

				menu.add(mItem);
			}
			menuBar.add(menu);
		}
		setJMenuBar(menuBar);
	}
	/*
	 * **************************************************************** 
	 * CRUD de Usuario

	 *****************************************************************/
	/**
	 * M�todo para adicionar un usuario.
	 */
	public void adicionarCliente()
	{
		String resultado = "" ;
		try
		{
			
			
			int documento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el n�mero de documento"));
			String nombre = JOptionPane.showInputDialog("Ingrese su nombre.");
			String password = JOptionPane.showInputDialog("Ingrese su password");
			String dir = JOptionPane.showInputDialog("Introduzca la direcci�n");
			String tipo = JOptionPane.showInputDialog("Ingrese el tipo de cliente: persona, empresa");
			if(!(tipo.equals("persona") || tipo.equals("empresa")))
				throw new Exception("El tipo ingresado no es v�lido.");
			VOCliente agregado = superAndes.agregarCliente(documento, nombre, password, dir, tipo);
			
			
				
			resultado += "En agregar cliente"
						+ "\nEmpresa adicionada exitosamente: " + agregado
						+ "\n Operaci�n terminada";
			
		}catch(Exception e)
		{
			resultado = generarMensajeError(e);
			
		}
		panelDatos.actualizarPanel(resultado);
	}
	
	/*
	 * **************************************************************** CRUD de
	 * CRUD de Empresa
	 *****************************************************************/
	
	public void listarCliente()
	{
		try
		{
			long initTime, finTime, time;
			
			initTime = System.currentTimeMillis();
			List<VOCliente> lista = superAndes.darClientes();
			finTime = System.currentTimeMillis();
			time = finTime - initTime;
			System.out.println(lista != null? "En interfaz: tama�o lista: " + lista.size() + ( lista.size() > 0? ", Elemento 1: " + lista.get(0).getDocumento() : "Lista vac�a: " )  : "Lista null");
			String resultado = "En listar clientes \n \n";
			resultado += "Tiempo en ms: " + time + "\n \n";
			resultado += this.listarClientes(lista);
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarProveedor()
	{
		try
		{
			List<VOProveedor> lista = superAndes.darProveedores();
			System.out.println("Listando proveedor.");
			String resultado = "En Listar Proveedor";
			resultado += "\n" + listarProveedores(lista);
			panelDatos.actualizarPanel(resultado);
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}

	
	
	
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	public void adicionarCiudad()
	{
		try
		{
			String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad");
			VOCiudad agregada = superAndes.agregarCiudad(nombre);
			String respuesta = "En Adicionar Lugar"
					+ "\nAdicionada: " + agregada;
			panelDatos.actualizarPanel(respuesta);
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarCiudad()
	{
		try
		{
			List<VOCiudad> lista = superAndes.darCiudades();
			String resultado = "En listar ciudad";
			resultado += "\n" + listarCiudades(lista);
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	/*
	 * **************************************************************** CRUD de
	 * CRUD Categoria
	 *****************************************************************/
	public void adicionarCategoria()
	{
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
		String caracteristicas = JOptionPane.showInputDialog("Ingrese las caracter�sticas");
		String almacenamiento = JOptionPane.showInputDialog("Ingrese el tipo de almacenamiento");
		String manejo = JOptionPane.showInputDialog("Ingrese el manejo que se le debe dar.");
		
		VOCategoria categoria = superAndes.agregarCategoria(nombre, caracteristicas, manejo);
		String respuesta = "En adicionar categora"
				+ "\n categor�a: " + categoria;
		panelDatos.actualizarPanel(respuesta);;
	}
	
	public void listarCategoria()
	{
		try
		{
			List<VOCategoria > lista = superAndes.darCategorias();
			String respuesta = "En listar categoria";
			respuesta += "\n" + listarCategorias(lista);
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
		
	}
	
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	public void adicionarSucursal()	
	{
		try
		{
			String nombre = JOptionPane.showInputDialog("Introduzca el nombre");
			int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tama�o"));
			String direccion = JOptionPane.showInputDialog("Intrese la direcci�n de la sucursal.");
			int nivelReorden = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el nivel de reorden."));
			int nivelReabastecimiento = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el nivel de reabastecimiento"));
			long idCiudad = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la ciudad"));
			String password = JOptionPane.showInputDialog("Introduzca el password de la sucursal.");
			VOSucursal sucursal = superAndes.agregarSucursal(nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad, password);
			
			String respuesta = "En Adicionar Sucursal";
			respuesta += "\n"+ sucursal;
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void ordenarProductosCarrosAbandonados()
	{
		try
		{
			long idTrabajador = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id del trabajador de la sucursal."));
			long idSucursal = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la sucursal."));
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
		
	}
	
	public void listarSucursal()
	{
		try
		{
			List<VOSucursal> lista = superAndes.darSucursales();
			String respuesta = "En Listar Sucursal";
			respuesta += "\n" + listarSucursales(lista);
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	public void adicionarContenedor()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarContenedor()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	public void adicionarProductoAbstracto(){
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void adicionarProductoOfrecidoSucursal()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void adicionarProductoOfrecidoProveedor()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void adicionarProductoFisico()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarProductoOfrecidoProveedor()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarProductoOfrecidoSucursal()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarProductoFisicoPorSucursal()
	{
		try
		{
			String respuesta = "En listar productos f�sicos por sucursal";
			long idSucursal = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la sucursal"));
			List<VOProductoFisico> lista = superAndes.darProductosFisicosPorIdSucursal(idSucursal);
			respuesta += "\n" + listarProductosFisicos(lista);
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarProductoFisicoPorContenedor()
	{
		try
		{
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	/*
	 * **************************************************************** CRUD de
	 * CRUD de Carrito
	 *****************************************************************/
	public void pedirCarrito()
	{
		try
		{
			int doc = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el doc del cliente"));
			long idSucursal = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la sucursal"));
			VOCarrito carrito = superAndes.agregarCarrito(doc, idSucursal);
			String respuesta = "En Pedir Carrito";
			respuesta += "\n" + carrito;
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarCarrito()
	{
		try
		{
			List<VOCarrito> lista = superAndes.darCarritos();
			String respuesta = "En listar carritos";
			respuesta += "\n" + listarCarritos(lista);
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void mostrarCarrito()
	{
		try
		{
			String respuesta = "En mostrar carrito";
			long usuarioId = Long.parseLong(JOptionPane.showInputDialog("Intrese el id de usuario"));
			VOCarrito carrito = superAndes.darCarritoPorUsuarioId(usuarioId);
			List<VOProductoFisico> productos = superAndes.darProductosPorCarritoId(carrito.getId());
			respuesta += listarCarritoConProductos(carrito,  productos);
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void ingresarProductoACarrito()
	{
		try
		{
			String respuesta = "En Ingresar producto a carrito";
			long usuarioId = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del usuario"));
			long productoFisicoId = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto f�sico"));
			long resultadoInsercion = superAndes.cambiarProductoACarrito(productoFisicoId, usuarioId);
			respuesta += "\n" + resultadoInsercion;
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	/**
	public void devolverProductoDeCarrito()
	{
		try
		{
			
			long idUsuario = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del usuario"));
			long productoFisicoId = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto f�sico"));
			String resultado = "En devolver producto de carrito.";
			long resultadoQuery = superAndes.cambiarProductoAContenedor(productoFisicoId, idUsuario);
			resultado += "\n" + resultadoQuery;
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			System.out.println(e.getMessage());
			panelDatos.actualizarPanel(resultado);
		}
	}
	*/
	
	/**
	public void devolverCarrito()
	{
		try
		{
			long usuarioId = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de usuario"));
			long resultadoQuery = superAndes.devolverCarrito(usuarioId);
			String respuesta = "En devolver carrito";
			respuesta += "\n" + resultadoQuery;
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			System.out.println(e.getMessage());
			panelDatos.actualizarPanel(resultado);
		}
	}
	*/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/
	/*
	 * **************************************************************** CRUD de
	 * tabla1
	 *****************************************************************/

	/*
	 * **************************************************************** M�todos
	 *            M�todos administrativos
	 *****************************************************************/
	
	
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogSuperAndes ()
	{
		mostrarArchivo ("superandes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarSuperAndes ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de superandes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarPanel(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarPanel(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el n�mero de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecuci�n de la demo y recolecci�n de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();
			
			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " ITEMFACTURA eliminados\n";
			resultado += eliminados [1] + " FACTURA eliminados\n";
			resultado += eliminados [2] + " PRODUCTOPEDIDO eliminados\n";
			resultado += eliminados [3] + " PEDIDO eliminadas\n";
			resultado += eliminados [4] + " PROMPORCANTIDADOUNIDAD de bebida eliminados\n";
			resultado += eliminados [5] + " PROMPORCENTAJEDESCUENTO eliminados\n";
			resultado += eliminados [6] + " PROMPAQUETEPRODUCTOS eliminados\n";
			resultado += eliminados [6] + " PROMOCION eliminados\n";
			resultado += eliminados [6] + " OFRECIDOPROVEEDOR eliminados\n";
			resultado += eliminados [6] + " PRODUCTOFISICO eliminados\n";
			resultado += eliminados [6] + " OFRECIDOSUCURSAL eliminados\n";
			resultado += eliminados [6] + " PRODUCTOABSTRACTO eliminados\n";
			resultado += eliminados [6] + " CATEGORIA eliminados\n";
			resultado += eliminados [6] + " CONTENEDOR eliminados\n";
			resultado += eliminados [6] + " PROVEEDORSUCURSAL eliminados\n";
			resultado += eliminados [6] + " SUCURSAL eliminados\n";
			resultado += eliminados [6] + " EMPRESA eliminados\n";
			resultado += eliminados [6] + " PERSONA eliminados\n";
			resultado += eliminados [6] + " USUARIO eliminados\n";
			resultado += eliminados [6] + " CIUDAD eliminados\n";
			resultado += eliminados [6] + " CARRITO eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarPanel(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	/**
	 * Muestra la presentaci�n general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-SuperAndesJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual SuperAndes.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/EsquemaBDSuperAndes.xlsx");
	}
	
	/**
	 * Muestra el script de creaci�n de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaSuperAndes.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	
	/**
     * Muestra la informaci�n acerca del desarrollo de esta apicaci�n
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogot�	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versi�n 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germ�n Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Realizado por: Jhonatan Andr�s Am�rtegui Garc�a\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarPanel(resultado);		
    }
    
    
	
	
	
    /* ****************************************************************
	 * 			M�todos privados para la presentaci�n de resultados y otras operaciones
	 *****************************************************************/
	 
    private String listarProveedores(List<VOProveedor> proveedores)
    {
    	String respuesta = "";
    	for(VOProveedor pActual: proveedores)
    		respuesta += "\n***********************" 
    		+ "\n nit:"+pActual.getNit() 
    		+ "\n idUser: " + pActual.getNombre()
    		+"\n tipo:" + pActual.getDir();
    	return respuesta;
    }
    
    private String listarClientes(List<VOCliente> clientes)
    {
    	String respuesta = "";
    	for(VOCliente empresaActual: clientes)
    	{
    		respuesta += "\n***********************"
    				+ "\n nit:" + empresaActual.getDocumento()
    				+ "\n idUser: " + empresaActual.getNombre()
    				+ "\n tipo: " + empresaActual.getTipo()
    				+ "\n puntos: " + empresaActual.getPuntos();
    	}
    	
    	return respuesta;
    }
    
    private String listarClientesNoVo(List<Cliente> clientes)
    {
    	String respuesta = "";
    	for(VOCliente empresaActual: clientes)
    	{
    		respuesta += "\n***********************"
    				+ "\n nit:" + empresaActual.getDocumento()
    				+ "\n idUser: " + empresaActual.getNombre()
    				+ "\n tipo: " + empresaActual.getTipo()
    				+ "\n puntos: " + empresaActual.getPuntos();
    	}
    	
    	return respuesta;
    }
    
    
    private String listarCiudades(List<VOCiudad> ciudades)
    {
    	String respuesta = "";
    	for(VOCiudad ciudadActual: ciudades)
    	{
    		respuesta += "\n*************"
    				+ "\n id: " + ciudadActual.getId()
    				+ "\n nombre: " + ciudadActual.getNombre();
    	}
    	
    	return respuesta;
    	
    }
    
    private String listarCategorias(List<VOCategoria> categorias)
    {
    	String respuesta = "";
    	for(VOCategoria categoriaActual: categorias)
    	{
    		respuesta += "***********************"
        			+ "\n nombre: " + categoriaActual.getNombre() 
        			+ "\n caracteristicas:" + categoriaActual.getCaracteristicas()
        			+ "\n " + categoriaActual.getManejo();
    	}    	
    	return respuesta;
    }
    
    
    
    
    private String listarPromocionesPaqueteProductos(List<VOPromocionPaqueteProductos> promociones)
    {
    	String respuesta = "";
    	for(VOPromocionPaqueteProductos promocionActual: promociones)
    	{
    		respuesta += "\n*************************"
    				+ "\n id: " + promocionActual.getId()
    				+ "\n sucursalId: " + promocionActual.getIdSucursal()
    				+ "\n descripci�n: " + promocionActual.getDescripcion()
    				+ "\n producto 1: " + promocionActual.getIdProductoOfrecido1()
    				+ "\n producto 2: " + promocionActual.getIdProductoOfrecido2();
    	}
    	
    	return respuesta;
    }
    private String listarPromocionesPorcentajeDescuento(List<VOPromocionPorcentajeDescuento> promociones)
    {
    	String respuesta = "";
    	for(VOPromocionPorcentajeDescuento promocionActual: promociones)
    	{
    		respuesta += "\n*************************"
    				+ "\n id: " + promocionActual.getId()
    				+ "\n sucursalId: " + promocionActual.getIdSucursal()
    				+ "\n descripci�n: " + promocionActual.getDescripcion()
    				+ "\n producto: " + promocionActual.getIdProductoOfrecido()
    				+ "\n porcentaje descuento: " + promocionActual.getPorcentajeDescuento();
    	}
    	
    	return respuesta;
    }
    private String listarPromocionesPorCantidadOUnidad(List<VOPromocionPorCantidadOUnidades> promociones)
    {
    	String respuesta = "";
    	for(VOPromocionPorCantidadOUnidades promocionActual: promociones)
    	{
    		respuesta += "\n*************************"
    				+ "\n id: " + promocionActual.getId()
    				+ "\n sucursalId: " + promocionActual.getIdSucursal()
    				+ "\n descripci�n: " + promocionActual.getDescripcion()
    				+ "\n producto : " + promocionActual.getIdProductoOfrecido()
    				+ "\n unidades pagadas: " + promocionActual.getCantidadOUnidadesPagadas()
    				+ "\n uniades adquiridas: " + promocionActual.getCantidadOUnidadesCompradas();
    	}
    	
    	return respuesta;
    }
    
    private String listarSucursales(List<VOSucursal> sucursales)
    {
    	String respuesta = "";
    	for(VOSucursal sucursalActual: sucursales)
    	{
    		respuesta += "\n**************************"
    				+ "\n id: " + sucursalActual.getId()
    				+ "\n nombre: " + sucursalActual.getNombre()
    				+ "\n tama�o: " + sucursalActual.getTamanho()
    				+ "\n idCiudad: " + sucursalActual.getIdCiudad()
    				+ "\n direcci�n: " + sucursalActual.getDireccion();
    		
    	}
    	return respuesta;
    }
    
    private String listarProductosAbstractos(List<VOProductoAbstracto> productos)
    {
    	String respuesta = "";
    	for(VOProductoAbstracto prodAbstractoActual: productos)
    	{
    		respuesta += "\n******************************"
    				+ "\n id: " + prodAbstractoActual.getId()
    				+ "\n nombre: " + prodAbstractoActual.getNombre()
    				+ "\n unidad Medida: " + prodAbstractoActual.getUnidadMedida()
    				+ "\n tipo: " + prodAbstractoActual.getIdTipo();
    	}
    	
    	return respuesta;
    }
    
    private String listarOfrecidoProveedor(List<VOOfrecidoProveedor> ofrecidos)
    {
    	String respuesta = "";
    	for(VOOfrecidoProveedor ofrecidoActual: ofrecidos)
    	{
    		respuesta += "\n************************"
    				+ "\n id:" + ofrecidoActual.getId()
    				+ "\n id Abstracto: " + ofrecidoActual.getIdAbstracto()
    				+ "\n nit Proveedor: " + ofrecidoActual.getNitProveedor()
    				+ "\n precio: " + ofrecidoActual.getPrecio();
    	}
    	return respuesta;
    }
    
    private String listarOfrecidoSucursal(List<VOOfrecidoSucursal> ofrecidos)
    {
    	String respuesta = "";
    	for(VOOfrecidoSucursal ofrecidoActual: ofrecidos)
    	{
    		respuesta += "\n*******************"
    				+ "\n id: " + ofrecidoActual.getId()
    				+ "\n id Abstracto: " + ofrecidoActual.getIdAbstracto()
    				+ "\n id Sucursal: " + ofrecidoActual.getIdSucursal()
    				+ "\n precio: " + ofrecidoActual.getPrecio();
    	}
    	return respuesta;
    }
    
    
    private String listarProductosFisicos(List<VOProductoFisico> fisicos)
    {
    	String respuesta = "";
    	for(VOProductoFisico fisicoActual: fisicos)
    	{
    		respuesta += "\n********************"
    				+ "\n id:" + fisicoActual.getId()
    				+ "\n codigo barras: " + fisicoActual.getCodigoBarras()
    				+ "\n C�digo Barras: " + fisicoActual.getCodigoBarras()
    				+ "\n costo: " + fisicoActual.getPrecio();
    	}
    	
    	return respuesta;
    }
    
    private String listarProductosFisicosEnCarrito(List<VOProductoFisico> fisicos)
    {
    	String respuesta = "";
    	for(VOProductoFisico fisicoActual: fisicos)
    	{
    		respuesta += "\n********************"
    				+ "\n id:" + fisicoActual.getId()
    				+ "\n c�digo barras: " + fisicoActual.getCodigoBarras()
    				+ "\n id tipo producto: " + fisicoActual.getIdTipo();
    	}
    	return respuesta;
    }
    
    private String listarCarritos(List<VOCarrito> carritos)
    {
    	String respuesta = "";
    	for(VOCarrito carritoActual: carritos)
    	{
    		respuesta += "\n********************"
    				+ "\n id: " + carritoActual.getId()
    				+ "\n id sucursal: " + carritoActual.getIdSucursal();
    	}
    	return respuesta;
    }
    
    private String listarCarritoConProductos(VOCarrito carrito, List<VOProductoFisico> productos)
    {
    	String respuesta = "";
    	respuesta += "\n********************"
				+ "\n id: " + carrito.getId()
				+ "\n id sucursal: " + carrito.getIdSucursal();
    	respuesta += "\n----"
    			+"\nproductos: ";
    	for(VOProductoFisico productoActual : productos)
    	{
    		respuesta += "\n�������������������������������"
    				+ "\n id: " + productoActual.getId()
    				+ "\n C�digo Barras: " + productoActual.getCodigoBarras()
    				+ "\n precio: "  + productoActual.getPrecio();
    	}
    	return respuesta;
    }
	/**
     * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
     * @param e - La excepci�n recibida
     * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	
	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superAndes.log para m�s detalles";
		return resultado;
	}
	
	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del
	 * sistema
	 * 
	 * @param nombreArchivo
	 *            - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo(String nombreArchivo) {
		try {
			Desktop.getDesktop().open(new File(nombreArchivo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * **************************************************************** M�todos
	 *           M�todos de la interacci�n
	 *****************************************************************/

	@Override
	public void actionPerformed(ActionEvent pEvento) {
		// TODO Auto-generated method stub
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
	
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	
	public static void main(String[] args)
	{
		try
        {
			BasicConfigurator.configure();
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
	}
	
	
	
	/***********************************
	 *M�todos de consulta iteraci�n 3 
	 ***********************************/
	//Consultar Consumo 1
	public void consultarConsumo1()
	{
		try
		{
			String criterio = JOptionPane.showInputDialog("Introduzca el criterio de orden");
			String fecha_inicial_string = JOptionPane.showInputDialog("Introduzca la fecha inicial (MM-dd-yyyy)");
			String fecha_final_string = JOptionPane.showInputDialog("Introduzca la fecha final (MM-dd-yyyy)");
			long idAbstracto = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto Abstracto"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha_inicial = sdf.parse(fecha_inicial_string);
			Date fecha_final = sdf.parse(fecha_final_string);
			
			long initTime, finTime, time;
			initTime = System.currentTimeMillis();
			List<Cliente> lista = superAndes.consultarConsumo(criterio, fecha_inicial, fecha_final, idAbstracto);
			finTime = System.currentTimeMillis();
			time = finTime - initTime;
			
			String resultado = "OBtenidos usuarios que consumieron al menos una vez el producto dado por par�metro entre las fechas dadas.";
			resultado += "Tiempo: " + time + "\n \n";
			resultado += listarClientesNoVo(lista);
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
			System.out.println("*******************************");
			e.printStackTrace();
			
		}
	}
	
	public void consultarConsumo2()
	{
		try
		{
			String criterio = JOptionPane.showInputDialog("Introduzca el criterio de orden");
			String fecha_inicial_string = JOptionPane.showInputDialog("Introduzca la fecha inicial (MM-dd-yyyy)");
			String fecha_final_string = JOptionPane.showInputDialog("Introduzca la fecha final (MM-dd-yyyy)");
			long idAbstracto = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto Abstracto"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date fecha_inicial = sdf.parse(fecha_inicial_string);
			Date fecha_final = sdf.parse(fecha_final_string);
			
			long initTime, finTime, time;
			initTime = System.currentTimeMillis();
			List<VOCliente> lista = superAndes.consultarConsumo2(criterio, fecha_inicial, fecha_final, idAbstracto);
			finTime = System.currentTimeMillis();
			time = finTime - initTime;
			System.out.println(lista != null? "En interfaz: tama�o lista: " + lista.size() + ( lista.size() > 0? ", Elemento 1: " + lista.get(0).getDocumento() : "Lista vac�a: " )  : "Lista null");

			String resultado = "OBtenidos usuarios que NO consumieron al menos una vez el producto dado por par�metro entre las fechas dadas.";
			resultado += "Tiempo: " + time + "\n \n";
			resultado += listarClientes(lista);
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}

}
