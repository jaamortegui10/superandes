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

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;

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
		mostrarArchivo ("superAndes.log");
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
		mostrarArchivo ("data/Esquema BD SuperAndes.pdf");
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
