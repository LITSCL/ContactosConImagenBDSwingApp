package cl.inacap.contactosconimagenbdswingapp;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Conexion {
	private final String servidor = "localhost"; //Direcci�n del servidor.
	private final String baseDeDatos = "dbcontactosconimagenbdswingapp"; //Nombre de la base de datos.
	private final String usuario = "root"; //Usuario para conectarse al dbms.
	private final String clave = "root"; //Contrase�a para conectarse al dbms.
	
	//M�todo que conecta el programa a la base de datos.
	public Connection conectar() {
		Connection conexion = null;
		try {
			String url = "jdbc:mysql://" + servidor + ":3306/" + baseDeDatos; //Esta es la url de conexi�n para la base de datos.
			Class.forName("com.mysql.cj.jdbc.Driver"); //Class.forName es necesario para registrar el driver a utilizar.
			conexion = DriverManager.getConnection(url, usuario, clave); //Este m�todo trata de establecer la conexi�n a la base de datos entregada utilizando el driver registrado.
			System.out.println("Conexi�n BD: True");
		} catch (Exception ex) {
			System.out.println("Conexi�n BD: False");
		}
		return conexion;
	}
	
	public ResultSet visualizar() {
		Connection conexion = conectar();
		ResultSet rs = null;
		try {
			PreparedStatement st = conexion.prepareStatement("SELECT * FROM contacto");
			rs = st.executeQuery();
			System.out.println("Ejecuci�n SQL: True");
		} catch (Exception ex) {
			System.out.println("Ejecuci�n SQL: False");
		}
		return rs;
	}
	
	public void guardarImagen(String ruta, String nombre) {
		Connection conexion = conectar();
		String sql = "INSERT INTO contacto (nombre,foto) VALUES (?, ?)";
		FileInputStream fis = null;
		PreparedStatement st = null;
		try {
			File file = new File(ruta); //Aca se almacena la ruta.
			fis = new FileInputStream(file); //Aca se lee el archivo en bytes.
			st = conexion.prepareStatement(sql);
			st.setString(1, nombre);
			st.setBinaryStream(2, fis);
			st.executeUpdate(); //Se ejecuta la consulta SQL.
			System.out.println("Ejecuci�n SQL: True");
		} catch (Exception ex) {
			System.out.println("Ejecuci�n SQL: False");
		}
	}
}
