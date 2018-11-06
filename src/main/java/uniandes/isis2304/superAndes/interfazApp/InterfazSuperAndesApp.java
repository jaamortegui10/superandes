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
import java.util.LinkedList;
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

import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOCarrito;
import uniandes.isis2304.superAndes.negocio.VOCategoria;
import uniandes.isis2304.superAndes.negocio.VOCiudad;
import uniandes.isis2304.superAndes.negocio.VOEmpresa;
import uniandes.isis2304.superAndes.negocio.VOOfrecidoProveedor;
import uniandes.isis2304.superAndes.negocio.VOOfrecidoSucursal;
import uniandes.isis2304.superAndes.negocio.VOPersona;
import uniandes.isis2304.superAndes.negocio.VOProductoAbstracto;
import uniandes.isis2304.superAndes.negocio.VOProductoFisico;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
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
	 * Cláse principal de la lógica de la app.
	 */
	private SuperAndes superAndes;

	/*
	 * ****************************************************************
	 * Atributos Interfaz
	 *****************************************************************/
	/**
	 * Objecto json con la configuración
	 */
	private JsonObject guiConfig;
	/**
	 * Panel de los datos
	 */
	private PanelDatos panelDatos;
	/**
	 * Menú con las diferentes opciones del usuario dentro de la app.
	 */
	private JMenuBar menuBar;

	/*
	 * **************************************************************** Métodos
	 *****************************************************************/

	public InterfazSuperAndesApp() {
		// Carga la configuración de la interfaz desde un archivo json
		guiConfig = openConfig("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica.
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
	 * **************************************************************** Métodos
	 * de configuración de la interfaz
	 *****************************************************************/
	private JsonObject openConfig(String tipo, String archConfig) {
		JsonObject config = null;
		try {
			Gson gson = new Gson();
			FileReader file = new FileReader(archConfig);
			JsonReader reader = new JsonReader(file);
			config = gson.fromJson(reader, JsonObject.class);
			log.info("Se encontró un archivo de configuración válido: " + tipo);
		} catch (Exception e) {
			// e.printStackTrace ();
			log.info("NO se encontró un archivo de configuración válido");
			JOptionPane.showMessageDialog(null,
					"No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App",
					JOptionPane.ERROR_MESSAGE);
		}
		return config;
	}

	private void configurarFrame() {
		int alto = 0;
		int ancho = 0;
		String titulo = "";

		if (guiConfig == null) {
			log.info("Se aplica configuración por defecto");
			titulo = "SuperAndes APP default";
			alto = 300;
			ancho = 500;
		} else {
			log.info("Se aplica configuración indicada en el archivo de configuración");
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
		// Creación de la barra de menús
		menuBar = new JMenuBar();
		for (JsonElement men : jsonMenu) {
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject();

			String menuTitle = jom.get("menuTitle").getAsString();
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu(menuTitle);

			for (JsonElement op : opciones) {
				// Creación de cada una de las opciones del menú
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
	 * Método para adicionar un usuario.
	 */
	public void adicionarUsuario()
	{
		try
		{
			String resultado = "" ;
			
			String nombre = JOptionPane.showInputDialog("Ingrese su nombre.");
			String password = JOptionPane.showInputDialog("Ingrese su password");
			String correo = JOptionPane.showInputDialog("Ingrese su correo.");
			String tipo = JOptionPane.showInputDialog("Ingrese el tipo de usuario: persona, empresa");
			
			if(tipo.equals("persona"))
			{
				String tipoPersona = JOptionPane.showInputDialog("Intrese el tipo de persona: cliente, trabajador_sucursal");
				int idSucursal = -1;
				if(tipoPersona.equals("trabajador_sucursal"))
					idSucursal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la sucursal del trabajador"));
				int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula"));
				VOPersona agregada = superAndes.agregarPersona(cedula, idSucursal, password, nombre, correo, tipoPersona);
				resultado += "En agregar Persona"
						+ "\nPersona adicionada exitosamente: " + agregada
						+ "\n Operación terminada";
			}
			else if(tipo.equals("empresa"))
			{
				int nit = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nit de la empresa"));
				String direccion = JOptionPane.showInputDialog("Ingrese la dirección");
				String tipoEmpresa = JOptionPane.showInputDialog("Ingrese el tipo de empresa: proveedor, cliente");
				System.out.println("------------>Interfaz antes de: ");
				VOEmpresa agregada = superAndes.agregarEmpresa(nit, direccion, password, nombre, correo, tipoEmpresa);
				resultado += "En agregar empresa"
						+ "\nEmpresa adicionada exitosamente: " + agregada
						+ "\n Operación terminada";
			}//Aquí estaba agregando usuario, así que ese era el problema.
			else
				throw new Exception("El tipo ingresado no es válido.");
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	/*
	 * **************************************************************** CRUD de
	 * CRUD de Empresa
	 *****************************************************************/
	public void listarProveedor()
	{
		try
		{
			List<VOEmpresa> lista = superAndes.darEmpresasPorTipoEmpresa("proveedor");
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
	
	public void listarClienteEmpresa()
	{
		try
		{
			List<VOEmpresa> lista = superAndes.darEmpresasPorTipoEmpresa("cliente");
			String resultado = "En Listar Cliente Empresa";
			resultado += "\n" + listarClientesEmpresa(lista);
			panelDatos.actualizarPanel(resultado);
			
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	
	/*
	 * **************************************************************** CRUD de
	 * CRUD Persona
	 *****************************************************************/
	public void listarTrabajadorSucursal()
	{
		try
		{
			List<VOPersona> lista = superAndes.darPersonasPorTipoPersona("trabajador_sucursal");
			String resultado = "En listar Trabajador Sucursal";
			resultado += "\n" + listarTrabajadoresSucursales(lista);
			panelDatos.actualizarPanel(resultado);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void listarClientePersona()
	{
		try
		{
			List<VOPersona> lista = superAndes.darPersonasPorTipoPersona("cliente");
			String resultado = "En listar Cliente Persona";
			resultado += "\n" + listarClientesPersona(lista);
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
		String caracteristicas = JOptionPane.showInputDialog("Ingrese las características");
		String almacenamiento = JOptionPane.showInputDialog("Ingrese el tipo de almacenamiento");
		String manejo = JOptionPane.showInputDialog("Ingrese el manejo que se le debe dar.");
		
		VOCategoria categoria = superAndes.agregarCategoria(nombre, caracteristicas, almacenamiento, manejo);
		String respuesta = "En adicionar categora"
				+ "\n categoría: " + categoria;
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
			int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tamaño"));
			String direccion = JOptionPane.showInputDialog("Intrese la dirección de la sucursal.");
			int nivelReorden = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el nivel de reorden."));
			int nivelReabastecimiento = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el nivel de reabastecimiento"));
			long idCiudad = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de la ciudad"));
			VOSucursal sucursal = superAndes.agregarSucursal(nombre, tamanho, direccion, nivelReorden, nivelReabastecimiento, idCiudad);
			
			String respuesta = "En Adicionar Sucursal";
			respuesta += "\n"+ sucursal;
			panelDatos.actualizarPanel(respuesta);
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
			String respuesta = "En listar productos físicos por sucursal";
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
			long idUser = Long.parseLong(JOptionPane.showInputDialog("Ingrese el id de usuario"));
			VOCarrito carrito = superAndes.agregarCarrito(idUser);
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
			long productoFisicoId = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto físico"));
			long resultadoInsercion = superAndes.cambiarProductoACarrito(productoFisicoId, usuarioId);
			respuesta += "\n" + resultadoInsercion;
			panelDatos.actualizarPanel(respuesta);
		}catch(Exception e)
		{
			String resultado = generarMensajeError(e);
			panelDatos.actualizarPanel(resultado);
		}
	}
	
	public void devolverProductoDeCarrito()
	{
		try
		{
			
			long idUsuario = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del usuario"));
			long productoFisicoId = Long.parseLong(JOptionPane.showInputDialog("Introduzca el id del producto físico"));
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
	 * **************************************************************** Métodos
	 *            Métodos administrativos
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
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarSuperAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("superAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de superandes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarPanel(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarPanel(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = superAndes.limpiarSuperAndes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
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
	 * Muestra la presentación general del proyecto
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
	 * Muestra el script de creación de la base de datos
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
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Realizado por: Jhonatan Andrés Amórtegui García\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarPanel(resultado);		
    }
    
    
	
	
	
    /* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	 
    private String listarProveedores(List<VOEmpresa> proveedores)
    {
    	String respuesta = "";
    	for(VOEmpresa empresaActual: proveedores)
    		respuesta += "\n***********************" 
    		+ "\n nit:"+empresaActual.getNit() 
    		+ "\n idUser: " + empresaActual.getIdUser()
    		+"\n tipo:" + empresaActual.getTipoEmpresa()
    		+ "\n dir:: " + empresaActual.getDir();
    	return respuesta;
    }
    
    private String listarClientesEmpresa(List<VOEmpresa> clientes)
    {
    	String respuesta = "";
    	for(VOEmpresa empresaActual: clientes)
    	{
    		respuesta += "\n***********************"
    				+ "\n nit:" + empresaActual.getNit()
    				+ "\n idUser: " + empresaActual.getIdUser()
    				+ "\n tipo: " + empresaActual.getTipoEmpresa()
    				+ "\n puntos: " + empresaActual.getPuntos();
    	}
    	
    	return respuesta;
    }
    
    private String listarClientesPersona(List<VOPersona> clientes)
    {
    	String respuesta = "";
    	for(VOPersona personaActual: clientes)
    	{
    		respuesta += "\n**********************"
    				+ "\n cedula: " + personaActual.getCedula()
    				+ "\n idUser: " + personaActual.getIdUser()
    				+ "\n puntosCompras: " + personaActual.getPuntos()
    				+ "\n tipo: :" + personaActual.getTipoPersona();
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
        			+ "\n almacenamiento: " + categoriaActual.getAlmacenamiento()
        			+ "\n caracteristicas:" + categoriaActual.getCaracteristicas()
        			+ "\n " + categoriaActual.getManejo();
    	}    	
    	return respuesta;
    }
    
    
    
    private String listarTrabajadoresSucursales(List<VOPersona> trabajadores)
    {
    	String respuesta = "";
    	for(VOPersona trabajadorActual: trabajadores)
    	{
    		respuesta += "\n***********************"
    				+ "\n cedula: " + trabajadorActual.getCedula()
    				+ "\n idUser: " + trabajadorActual.getIdUser()
    				+ "\n sucursalId: " + trabajadorActual.getSucursalId()
    				+ "\n tipoPersona: " + trabajadorActual.getTipoPersona();
    	}
    	return respuesta;
    }
    
    private String listarPromociones(List<VOPromocion> promociones)
    {
    	String respuesta = "";
    	for(VOPromocion promocionActual: promociones)
    	{
    		respuesta += "\n*************************"
    				+ "\n id: " + promocionActual.getId()
    				+ "\n sucursalId: " + promocionActual.getIdSucursal()
    				+ "\n descripción: " + promocionActual.getDescripcion();
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
    				+ "\n tamaño: " + sucursalActual.getTamanho()
    				+ "\n idCiudad: " + sucursalActual.getIdCiudad()
    				+ "\n dirección: " + sucursalActual.getDireccion();
    		
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
    				+ "\n categoria: " + prodAbstractoActual.getCategoria()
    				+ "\n unidad Medida: " + prodAbstractoActual.getUnidadMedida()
    				+ "\n tipo: " + prodAbstractoActual.getTipo();
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
    				+ "\n id Ofrecido: " + fisicoActual.getIdOfrecido()
    				+ "\n cantidad medida: " + fisicoActual.getCantidadMedida()
    				+ "\n Código Barras: " + fisicoActual.getCodigoBarras()
    				+ "\n id Contenedor: " + fisicoActual.getIdContenedor();
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
    				+ "\n id Ofrecido: " + fisicoActual.getIdOfrecido()
    				+ "\n cantidad medida: " + fisicoActual.getCantidadMedida()
    				+ "\n Código Barras: " + fisicoActual.getCodigoBarras()
    				+ "\n id Carrito: " + fisicoActual.getIdCarrito();
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
    				+ "\n id User: " + carritoActual.getIdUser();
    	}
    	return respuesta;
    }
    
    private String listarCarritoConProductos(VOCarrito carrito, List<VOProductoFisico> productos)
    {
    	String respuesta = "";
    	respuesta += "\n********************"
				+ "\n id: " + carrito.getId()
				+ "\n id User: " + carrito.getIdUser();
    	respuesta += "\n----"
    			+"\nproductos: ";
    	for(VOProductoFisico productoActual : productos)
    	{
    		respuesta += "\n°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°"
    				+ "\n id: " + productoActual.getId()
    				+ "\n id Ofrecido: " + productoActual.getIdOfrecido()
    				+ "\n Código Barras: " + productoActual.getCodigoBarras();
    	}
    	return respuesta;
    }
	/**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y superAndes.log para más detalles";
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
	 * Abre el archivo dado como parámetro con la aplicación por defecto del
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
	 * **************************************************************** Métodos
	 *           Métodos de la interacción
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

}
