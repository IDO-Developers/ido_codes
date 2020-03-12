package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import org.apache.commons.codec.binary.Base64;

import clases.alumnos;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_alumnos;
import consultas.consultas_usuario;
import controles.control_usuario;

import java.awt.Color;
import java.awt.Event;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.Component;

public class ventana_codigos_alumnos_7_10 extends JFrame {

	private JPanel contentPane;
	public JButton btnImprimir;
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public static DefaultComboBoxModel modelo;
	public static String id_rol;
	public static String identidadRepetida;
	public static JTextField txtUsuario;
	public static JTextField txtContraseña;
	public static JFormattedTextField txtIdentidad;
	public JComboBox cbxGrado;
	public static String cadena = null;
	public static String contraseñaEncriptada = null;
	public JButton btnUsuarios;

	public static String user;
	public static String pass;

	public static String USUARIO_id = null;
	public static String USUARIO_users= null;
	public static String USUARIO_Prematriculas= null;
	public static String ID_Prematriculas= null;
	public static String ROL= null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarios clase = new usuarios();
					consultas_usuario consulta = new consultas_usuario();
					ventana_codigos_alumnos_7_10 formulario = new ventana_codigos_alumnos_7_10();
					formulario.setVisible(true);
					formulario.setLocationRelativeTo(null);
					formulario.txtIdentidad.requestFocusInWindow();
					formulario.btnImprimir.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventana_codigos_alumnos_7_10() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_ido.png")));
		final ImageIcon SEPYDEC = new ImageIcon(getClass().getResource("/recursos/7,10.png"));
		final ImageIcon OCTNOVUNDDUO = new ImageIcon(getClass().getResource("/recursos/8,9,11,12.png"));
		final ImageIcon LOGOUSER = new ImageIcon(getClass().getResource("/recursos/usuario.png"));
		final ImageIcon LOGOSALIR = new ImageIcon(getClass().getResource("/recursos/logout.png"));
		final ImageIcon LOGOPREMATRICULA = new ImageIcon(getClass().getResource("/recursos/usuario.png"));
		final ImageIcon LOGOCOMPROBARMATRICULA = new ImageIcon(getClass().getResource("/recursos/logout.png"));
		final ImageIcon LogoPREMA = new ImageIcon(getClass().getResource("/recursos/pre_matricula.png"));
		final ImageIcon LogoCompr = new ImageIcon(getClass().getResource("/recursos/prueba.png"));
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 278, 398);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Grado:");
		lblUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(81, 175, 112, 20);
		panel.add(lblUsuario);

		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana_detalle_comprobante comprobante = new ventana_detalle_comprobante();
				ventana_detalle_comprobante.lblIdentidad.setText(user);
				ventana_detalle_comprobante.lblCodigo.setText(cadena);
				ventana_detalle_comprobante.getHora();
				ventana_detalle_comprobante.lblFecha.setText(ventana_detalle_comprobante.getFecha());
				comprobante.setVisible(true);
				comprobante.setLocationRelativeTo(null);

			}
		});
		btnImprimir.setBackground(new Color(46, 139, 87));
		btnImprimir.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnImprimir.setBounds(41, 357, 183, 23);
		panel.add(btnImprimir);

		JLabel USUARIOS = new JLabel("DATOS DEL COMPROBANTE");
		USUARIOS.setForeground(new Color(46, 139, 87));
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(8, 11, 256, 20);
		panel.add(USUARIOS);

		JLabel lblUsuario_1 = new JLabel("Usuario:");
		lblUsuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario_1.setBounds(8, 261, 258, 20);
		panel.add(lblUsuario_1);

		txtUsuario = new JTextField();
		txtUsuario.setForeground(new Color(0, 0, 128));
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(41, 283, 183, 20);
		panel.add(txtUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblContrasea.setBounds(10, 304, 258, 20);
		panel.add(lblContrasea);

		txtContraseña = new JTextField();
		txtContraseña.setForeground(new Color(0, 0, 128));
		txtContraseña.setEditable(false);
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(41, 326, 183, 20);
		panel.add(txtContraseña);

		JButton btnCredenciales = new JButton("GENERAR CREDENCIALES");
		btnCredenciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdentidad.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor escriba la identidad del alumno");
				} else {
					preguntarPorCredenciales();
					preguntarPorGrupo();
					preguntarPorRol();
					if (USUARIO_users == null && USUARIO_Prematriculas == null) {
						Registrar_Usuario_Contraseña_Identidad_Grupo();
					} else {
						Actualizar_Usuario_Contraseña_Identidad_Grupo();
					}

				}
				btnImprimir.setVisible(true);
			}

		});
		btnCredenciales.setBackground(new Color(255, 215, 0));
		btnCredenciales.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnCredenciales.setBounds(45, 227, 183, 23);
		panel.add(btnCredenciales);

		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblIdentidad.setBounds(8, 133, 256, 20);
		panel.add(lblIdentidad);

		MaskFormatter identidad = null;
		try {
			identidad = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(identidad);
		txtIdentidad.setForeground(new Color(0, 0, 128));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(56, 154, 160, 20);
		panel.add(txtIdentidad);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(66, 11, 139, 135);
		panel.add(lblNewLabel);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		cbxGrado = new JComboBox();
		cbxGrado.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxGrado.setModel(new DefaultComboBoxModel(new String[] { "Séptimo", "Decimo" }));
		cbxGrado.setBounds(91, 196, 93, 20);
		panel.add(cbxGrado);

		
		JButton btnCerrarSesion = new JButton();
		btnCerrarSesion.setBounds(279, 347, 52, 51);
		contentPane.add(btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ventana_login login = new ventana_login();
				ventana_login principal = new ventana_login();
	    		principal.setVisible(true);
	    		principal.setLocationRelativeTo(null);
			}
		});
		btnCerrarSesion.setBackground(new Color(178, 34, 34));
		final ImageIcon iconom1 = new ImageIcon(
				LOGOSALIR.getImage().getScaledInstance(btnCerrarSesion.getWidth(), btnCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		btnCerrarSesion.setIcon(iconom1);
		
				btnUsuarios = new JButton();
				btnUsuarios.setBounds(279, 285, 52, 51);
				contentPane.add(btnUsuarios);
				btnUsuarios.setBackground(new Color(30, 144, 255));
				btnUsuarios.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						usuarios clase = new usuarios();
						consultas_usuario consulta = new consultas_usuario();
						ventana_usuarios formulario = new ventana_usuarios();
						control_usuario control = new control_usuario(clase, consulta, formulario);
						formulario.setVisible(true);
						formulario.setLocationRelativeTo(null);
						formulario.txtNombre.requestFocusInWindow();
						formulario.construirTabla();
						formulario.obtenerUltimoId();
						formulario.llena_combobox_con_roles();
						formulario.btnBorrar.setVisible(false);
						formulario.btnGuardar.setVisible(true);
						formulario.btnActualizar.setVisible(true);
						formulario.btnActualizar_Usuario.setVisible(false);
						formulario.btnVer.setVisible(true);
						formulario.btnAceptar.setVisible(false);
						dispose();
					}
				});
				final ImageIcon iconom = new ImageIcon(
						LOGOUSER.getImage().getScaledInstance(btnUsuarios.getWidth(), btnUsuarios.getHeight(), Image.SCALE_DEFAULT));
				btnUsuarios.setIcon(iconom);
				
				JButton btn8_9_11_12 = new JButton();
				btn8_9_11_12.setBackground(Color.ORANGE);
				btn8_9_11_12.setBounds(279, 99, 52, 51);
				contentPane.add(btn8_9_11_12);
				final ImageIcon icono111= new ImageIcon(
						OCTNOVUNDDUO.getImage().getScaledInstance(btn8_9_11_12.getWidth(), btn8_9_11_12.getHeight(), Image.SCALE_DEFAULT));
				btn8_9_11_12.setIcon(icono111);
				
				JButton btnPrematricula = new JButton();
				btnPrematricula.setBackground(new Color(0, 100, 0));
				btnPrematricula.setBounds(279, 161, 52, 51);
				contentPane.add(btnPrematricula);
				final ImageIcon icono1112= new ImageIcon(
						LogoPREMA.getImage().getScaledInstance(btnPrematricula.getWidth(), btnPrematricula.getHeight(), Image.SCALE_DEFAULT));
				btnPrematricula.setIcon(icono1112);
				
				JButton btnComprobarMatricula = new JButton();
				btnComprobarMatricula.setBackground(new Color(0, 100, 0));
				btnComprobarMatricula.setBounds(279, 223, 52, 51);
				contentPane.add(btnComprobarMatricula);
				final ImageIcon icono1122= new ImageIcon(
						LogoCompr.getImage().getScaledInstance(btnComprobarMatricula.getWidth(), btnComprobarMatricula.getHeight(), Image.SCALE_DEFAULT));
				btnComprobarMatricula.setIcon(icono1122);
				
				JButton btn7_10 = new JButton();
				btn7_10.setBackground(Color.ORANGE);
				btn7_10.setBounds(279, 37, 52, 51);
				contentPane.add(btn7_10);
				final ImageIcon icono11= new ImageIcon(
						SEPYDEC.getImage().getScaledInstance(btn7_10.getWidth(), btn7_10.getHeight(), Image.SCALE_DEFAULT));
				btn7_10.setIcon(icono11);
				
				JLabel lblMen = new JLabel("Men\u00FA");
				lblMen.setForeground(new Color(0, 0, 0));
				lblMen.setHorizontalAlignment(SwingConstants.CENTER);
				lblMen.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
				lblMen.setBounds(279, 0, 52, 38);
				contentPane.add(lblMen);
	
	}

	@SuppressWarnings("unlikely-arg-type")
	public void generarCodigo() {

		String valorGrado = null;
		if (cbxGrado.getSelectedItem().toString().equals("Séptimo")) {
			valorGrado = "SEP21-";
		} else {
			valorGrado = "DEC21-";
		}

		String NumGenerado = String.valueOf(genererNumero());
		cadena = valorGrado + NumGenerado;
	}

	public static int genererNumero() {
		return (int) (1000 * Math.random());
	}

	public void preguntarPorCredenciales() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM dbo.users where RNE_Alumno= '" + txtIdentidad.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				USUARIO_users = rsr.getString("RNE_Alumno");
				USUARIO_id = rsr.getString("id");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preguntarPorGrupo() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM dbo.Prematriculas where RNE_Alumno= '" + txtIdentidad.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				USUARIO_Prematriculas = rsr.getString("RNE_Alumno");
				ID_Prematriculas = rsr.getString("Id_Prematricula");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void preguntarPorRol() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.Roles where Nombre_Rol='Alumno'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ROL = rsr.getString("Id_Rol");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void Registrar_Usuario_Contraseña_Identidad_Grupo() {
		txtUsuario.setText(txtIdentidad.getText().toString());
		generarCodigo();
		txtContraseña.setText(cadena);
		alumnos clase = new alumnos();
		alumnos clase2 = new alumnos();
		consultas_alumnos consulta = new consultas_alumnos();

		clase.setRNE_Alumno(txtIdentidad.getText().toString());
		contraseñaEncriptada = recursos.BCrypt.hashpw(cadena, recursos.BCrypt.gensalt());
		clase.setPassword(contraseñaEncriptada);
		clase.setId_Rol(ROL);

		clase2.setRNE_Alumno(txtIdentidad.getText().toString());
		if (cbxGrado.getSelectedItem().equals("Séptimo")) {
			clase2.setId_Grupo("101");
		} else {
			clase2.setId_Grupo("104");
		}

		if (consulta.insertarUserYpass(clase) && consulta.insertarRNEyGrupo(clase2)) {
			JOptionPane.showMessageDialog(null, "Credenciales del alumno registradas!");

		} else {
			JOptionPane.showMessageDialog(null, "Error! Credenciales del alumno NO registradas!");

		}

	}

	public void Actualizar_Usuario_Contraseña_Identidad_Grupo() {
		txtUsuario.setText(txtIdentidad.getText().toString());
		generarCodigo();
		txtContraseña.setText(cadena);
		alumnos clase = new alumnos();
		alumnos clase2 = new alumnos();
		consultas_alumnos consulta = new consultas_alumnos();

		clase.setRNE_Alumno(txtIdentidad.getText().toString());
		contraseñaEncriptada = recursos.BCrypt.hashpw(cadena, recursos.BCrypt.gensalt());
		clase.setPassword(contraseñaEncriptada);
		clase.setId_Rol(ROL);
		clase.setId(Integer.parseInt(USUARIO_id));

		clase2.setRNE_Alumno(txtIdentidad.getText().toString());
		if (cbxGrado.getSelectedItem().equals("Séptimo")) {
			clase2.setId_Grupo("101");
		} else {
			clase2.setId_Grupo("104");
		}
		clase2.setId_Prematricula(Integer.parseInt(ID_Prematriculas));

		if (consulta.actualizarUserYpass(clase) && consulta.actualizarRNEyGrupo(clase2)) {
			JOptionPane.showMessageDialog(null, "Credenciales del alumno actualizadas!");

		} else {
			JOptionPane.showMessageDialog(null, "Error! Credenciales del alumno NO actualizadas!");

		}

	}
}
