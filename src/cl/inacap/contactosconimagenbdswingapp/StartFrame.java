package cl.inacap.contactosconimagenbdswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class StartFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	String ruta = null;
	private JLabel lblImagen;
	private JButton btnAbrirFoto;
	private JButton btnMostrarTabla;
	private JButton btnGuardarContacto;
	private JLabel lblURL;
	private JScrollPane scrollPane;
	private JTable tableContactos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setTitle("ContactosConImagenBDSwingApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(406, 33, 124, 14);
		contentPane.add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(406, 58, 268, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Elegir foto");
		lblNewLabel_1.setBounds(406, 89, 75, 14);
		contentPane.add(lblNewLabel_1);
		
		btnAbrirFoto = new JButton("Abrir");
		btnAbrirFoto.addActionListener(e -> abrirFoto(e));
		btnAbrirFoto.setBounds(469, 85, 89, 23);
		contentPane.add(btnAbrirFoto);
		
		lblImagen = new JLabel("");
		lblImagen.setBounds(406, 114, 256, 256);
		contentPane.add(lblImagen);
		
		btnGuardarContacto = new JButton("Guardar Contacto");
		btnGuardarContacto.addActionListener(e -> agregarContacto(e));
		btnGuardarContacto.setBounds(744, 579, 230, 23);
		contentPane.add(btnGuardarContacto);
		
		btnMostrarTabla = new JButton("Mostrar Tabla");
		btnMostrarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MostrarTabla mt = new MostrarTabla();
				mt.mostrarTabla(tableContactos);
			}
		});
		btnMostrarTabla.setBounds(10, 430, 386, 23);
		contentPane.add(btnMostrarTabla);
		
		lblURL = new JLabel("");
		lblURL.setBounds(406, 381, 568, 14);
		contentPane.add(lblURL);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 386, 386);
		contentPane.add(scrollPane);
		
		tableContactos = new JTable();
		tableContactos.setEnabled(false);
		scrollPane.setViewportView(tableContactos);
	}

	private void agregarContacto(ActionEvent e) {
		Conexion co = new Conexion();
		String nombre = this.textFieldNombre.getText();
		String url = this.lblURL.getText();
		
		if (url.trim().length() != 0 && nombre.trim().length() != 0) { //Se verifica que los campos no esten vacios.
			co.guardarImagen(ruta, nombre);
		}
		else {
			JOptionPane.showMessageDialog(null, "- Dejaste los campos vacios");
		}
	}

	private void abrirFoto(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		int ap = fc.showOpenDialog(this);
		if (ap == JFileChooser.APPROVE_OPTION) {
			ruta = fc.getSelectedFile().getAbsolutePath();
			Image imagen = getToolkit().getImage(ruta);
			imagen = imagen.getScaledInstance(256, 256, Image.SCALE_DEFAULT); //Se redimensiona el tamaño de la imagen (Deben ser las mismas dimensiones que el JLabel).
			lblImagen.setIcon(new ImageIcon(imagen)); //Se le añaade la imagen al JLabel.
			lblURL.setText(ruta);
		}
	}
}
