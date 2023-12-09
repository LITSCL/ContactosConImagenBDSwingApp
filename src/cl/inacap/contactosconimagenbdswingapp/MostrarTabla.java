package cl.inacap.contactosconimagenbdswingapp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MostrarTabla {
	
	public void mostrarTabla(JTable tabla) {
		Conexion co = new Conexion();
		Connection conexion = co.conectar();
		ResultSet rs = co.visualizar();
		
		tabla.setDefaultRenderer(Object.class, new TablaImagen()); //Aca se llama al método que renderiza la tabla para que permita mostrar JLabel.
		
		DefaultTableModel mo = new DefaultTableModel();
		mo.addColumn("Nombre");
		mo.addColumn("Foto");
		
		try {
			while (rs.next()) {
				Object[] fila = new Object[2];
				fila[0] = rs.getObject(2);
				Blob blob = rs.getBlob(3); //Aca tenemos la imagen en bytes.
				byte[] data = blob.getBytes(1, (int)blob.length());
				BufferedImage imagen = null;
				
				imagen = ImageIO.read(new ByteArrayInputStream(data)); //Aca se lee la imagen.
				ImageIcon icono = new ImageIcon(imagen);
				
				//Proceso de redimensionado de la imagen.
				Image im = icono.getImage(); //Se transforma el dato.
				Image imRedimensionada = im.getScaledInstance(64, 64, Image.SCALE_DEFAULT);
				ImageIcon iconoRedimensionado = new ImageIcon(imRedimensionada);
				
				fila[1] = new JLabel(iconoRedimensionado);
				mo.addRow(fila);
			}
			tabla.setModel(mo);
			tabla.setRowHeight(64); //Aca se modifica el ancho de las filas de la tabla.
		} catch (Exception ex) {
			System.out.println("Error al Visualizar en la tabla");
		}
	}
}
