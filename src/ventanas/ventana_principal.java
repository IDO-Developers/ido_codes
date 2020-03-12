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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class ventana_principal extends JFrame {

	public JPanel contentPane;
	public JPanel panel_1;
	public JPanel panel;
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
	public JFormattedTextField txtIdentidad2;

	public static String user;
	public static String pass;

	public static String USUARIO_id = null;
	public static String USUARIO_users = null;
	public static String USUARIO_Prematriculas = null;
	public static String ID_Prematriculas = null;
	public static String ROL = null;
	private JTextField txtUsuario2;
	private JTextField txtContraseña2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarios clase = new usuarios();
					consultas_usuario consulta = new consultas_usuario();
					ventana_principal formulario = new ventana_principal();
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
	public ventana_principal() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 506);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		final ImageIcon atras = new ImageIcon(getClass().getResource("/recursos/atras.png"));
		final ImageIcon adelante = new ImageIcon(getClass().getResource("/recursos/adelante.png"));
		final ImageIcon MATRI = new ImageIcon(getClass().getResource("/recursos/matricula.png"));
		final ImageIcon estudiante = new ImageIcon(getClass().getResource("/recursos/estudiante.png"));

		contentPane.setLayout(null);

		MaskFormatter identidad = null;
		try {
			identidad = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));

		MaskFormatter identidad1 = null;
		try {
			identidad1 = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(269, 11, 217, 453);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);

		JLabel lblMen = new JLabel("Men\u00FA");
		lblMen.setBounds(78, 0, 52, 30);
		panel_2.add(lblMen);
		lblMen.setForeground(new Color(0, 100, 0));
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		JButton btn7_10 = new JButton();
		btn7_10.setToolTipText("Generador de credenciales para 7\u00B0 y 10\u00B0");
		btn7_10.setBounds(10, 58, 89, 85);
		panel_2.add(btn7_10);
		btn7_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
		});
		btn7_10.setBackground(Color.ORANGE);
		final ImageIcon icono11 = new ImageIcon(
				SEPYDEC.getImage().getScaledInstance(btn7_10.getWidth(), btn7_10.getHeight(), Image.SCALE_DEFAULT));
		btn7_10.setIcon(icono11);

		JButton btn8_9_11_12 = new JButton();
		btn8_9_11_12.setToolTipText("Generador de credenciales para 8\u00B0,9\u00B0,11\u00B0 y 12\u00B0");
		btn8_9_11_12.setBounds(118, 58, 89, 85);
		panel_2.add(btn8_9_11_12);
		btn8_9_11_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		btn8_9_11_12.setBackground(Color.ORANGE);
		final ImageIcon icono111 = new ImageIcon(OCTNOVUNDDUO.getImage().getScaledInstance(btn8_9_11_12.getWidth(),
				btn8_9_11_12.getHeight(), Image.SCALE_DEFAULT));
		btn8_9_11_12.setIcon(icono111);

		JButton btnPrematricula = new JButton();
		btnPrematricula.setToolTipText("Pre-matr\u00EDcula IDO");
		btnPrematricula.setBounds(10, 169, 89, 85);
		panel_2.add(btnPrematricula);
		btnPrematricula.setBackground(new Color(0, 100, 0));
		final ImageIcon icono1112 = new ImageIcon(LogoPREMA.getImage().getScaledInstance(btnPrematricula.getWidth(),
				btnPrematricula.getHeight(), Image.SCALE_DEFAULT));
		btnPrematricula.setIcon(icono1112);

		JButton btnComprobarMatricula = new JButton();
		btnComprobarMatricula.setToolTipText("Verificaci\u00F3n de matricula");
		btnComprobarMatricula.setBounds(118, 291, 89, 85);
		panel_2.add(btnComprobarMatricula);
		btnComprobarMatricula.setBackground(new Color(0, 100, 0));
		final ImageIcon icono1122 = new ImageIcon(LogoCompr.getImage().getScaledInstance(
				btnComprobarMatricula.getWidth(), btnComprobarMatricula.getHeight(), Image.SCALE_DEFAULT));
		btnComprobarMatricula.setIcon(icono1122);

		btnUsuarios = new JButton();
		btnUsuarios.setToolTipText("Registro de usuarios");
		btnUsuarios.setBounds(64, 387, 44, 41);
		panel_2.add(btnUsuarios);
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
		final ImageIcon iconom = new ImageIcon(LOGOUSER.getImage().getScaledInstance(btnUsuarios.getWidth(),
				btnUsuarios.getHeight(), Image.SCALE_DEFAULT));
		btnUsuarios.setIcon(iconom);

		JButton btnCerrarSesion = new JButton();
		btnCerrarSesion.setToolTipText("Cerrar Sesi\u00F3n");
		btnCerrarSesion.setBounds(109, 387, 44, 41);
		panel_2.add(btnCerrarSesion);
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
		final ImageIcon iconom1 = new ImageIcon(LOGOSALIR.getImage().getScaledInstance(btnCerrarSesion.getWidth(),
				btnCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		btnCerrarSesion.setIcon(iconom1);

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acerca_de desarrolladores = new acerca_de();
				desarrolladores.setVisible(true);
				desarrolladores.setLocationRelativeTo(null);
			}
		});
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setForeground(new Color(0, 100, 0));
		lblAcercaDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblAcercaDe.setBounds(64, 429, 89, 24);
		panel_2.add(lblAcercaDe);

		JButton button = new JButton();
		button.setToolTipText("Pre-matr\u00EDcula IDO");
		button.setBackground(new Color(0, 100, 0));
		button.setBounds(118, 169, 89, 85);
		panel_2.add(button);
		final ImageIcon icono1111 = new ImageIcon(
				MATRI.getImage().getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_DEFAULT));
		button.setIcon(icono1111);

		JLabel lblCredencialesPara = new JLabel("Credenciales");
		lblCredencialesPara.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredencialesPara.setForeground(Color.BLACK);
		lblCredencialesPara.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblCredencialesPara.setBounds(10, 34, 89, 13);
		panel_2.add(lblCredencialesPara);

		JLabel lblParaY = new JLabel("Para 7\u00B0 y 10\u00B0");
		lblParaY.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaY.setForeground(Color.BLACK);
		lblParaY.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblParaY.setBounds(10, 44, 89, 13);
		panel_2.add(lblParaY);

		JLabel label_8 = new JLabel("Credenciales");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		label_8.setBounds(118, 34, 89, 13);
		panel_2.add(label_8);

		JLabel lblParaY_1 = new JLabel("Para 8\u00B0,9\u00B0,11\u00B0 y 12\u00B0");
		lblParaY_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaY_1.setForeground(Color.BLACK);
		lblParaY_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblParaY_1.setBounds(109, 44, 108, 13);
		panel_2.add(lblParaY_1);

		JLabel lblPrematrculaIdo = new JLabel("Pre-matr\u00EDcula IDO");
		lblPrematrculaIdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrematrculaIdo.setForeground(Color.BLACK);
		lblPrematrculaIdo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblPrematrculaIdo.setBounds(0, 154, 108, 13);
		panel_2.add(lblPrematrculaIdo);

		JLabel lblMatrculaIdo = new JLabel("Matr\u00EDcula IDO");
		lblMatrculaIdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatrculaIdo.setForeground(Color.BLACK);
		lblMatrculaIdo.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblMatrculaIdo.setBounds(118, 153, 89, 13);
		panel_2.add(lblMatrculaIdo);

		JLabel lblVerificacin = new JLabel("Verificaci\u00F3n ");
		lblVerificacin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerificacin.setForeground(Color.BLACK);
		lblVerificacin.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblVerificacin.setBounds(118, 265, 89, 13);
		panel_2.add(lblVerificacin);

		JLabel lblDeMatrculaIdo = new JLabel("de matr\u00EDcula IDO");
		lblDeMatrculaIdo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeMatrculaIdo.setForeground(Color.BLACK);
		lblDeMatrculaIdo.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblDeMatrculaIdo.setBounds(109, 275, 108, 13);
		panel_2.add(lblDeMatrculaIdo);

		JLabel lblListado = new JLabel("Listado de ");
		lblListado.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado.setForeground(Color.BLACK);
		lblListado.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblListado.setBounds(9, 265, 89, 13);
		panel_2.add(lblListado);

		JLabel lblDeCredencialesRegistradas = new JLabel("credenciales.");
		lblDeCredencialesRegistradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeCredencialesRegistradas.setForeground(Color.BLACK);
		lblDeCredencialesRegistradas.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblDeCredencialesRegistradas.setBounds(0, 275, 108, 13);
		panel_2.add(lblDeCredencialesRegistradas);

		JButton button_1 = new JButton();
		button_1.setToolTipText("Verificaci\u00F3n de matricula");
		button_1.setBackground(new Color(0, 100, 0));
		button_1.setBounds(10, 291, 89, 85);
		panel_2.add(button_1);
		final ImageIcon iconoxx = new ImageIcon(estudiante.getImage().getScaledInstance(button_1.getWidth(),
				button_1.getHeight(), Image.SCALE_DEFAULT));
		button_1.setIcon(iconoxx);

		panel = new JPanel();
		panel.setBounds(6, 11, 253, 419);
		contentPane.add(panel);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Grado:");
		lblUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(79, 202, 112, 20);
		panel.add(lblUsuario);

		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana_comprobante comprobante = new ventana_comprobante();
				ventana_comprobante.lblIdentidad.setText(user);
				ventana_comprobante.lblCodigo.setText(cadena);
				ventana_comprobante.getHora();
				ventana_comprobante.lblFecha.setText(ventana_comprobante.getFecha());
				comprobante.setVisible(true);
				comprobante.setLocationRelativeTo(null);

			}
		});
		btnImprimir.setBackground(new Color(46, 139, 87));
		btnImprimir.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnImprimir.setBounds(37, 373, 185, 23);
		panel.add(btnImprimir);

		JLabel USUARIOS = new JLabel("7\u00B0 y 10\u00B0");
		USUARIOS.setForeground(new Color(46, 139, 87));
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(16, 31, 222, 20);
		panel.add(USUARIOS);

		JLabel lblUsuario_1 = new JLabel("Usuario:");
		lblUsuario_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario_1.setBounds(4, 277, 258, 20);
		panel.add(lblUsuario_1);

		txtUsuario = new JTextField();
		txtUsuario.setForeground(new Color(0, 0, 128));
		txtUsuario.setEditable(false);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(37, 299, 185, 20);
		panel.add(txtUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblContrasea.setBounds(6, 320, 258, 20);
		panel.add(lblContrasea);

		txtContraseña = new JTextField();
		txtContraseña.setForeground(new Color(0, 0, 128));
		txtContraseña.setEditable(false);
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(37, 342, 185, 20);
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
		btnCredenciales.setBounds(37, 254, 185, 23);
		panel.add(btnCredenciales);

		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblIdentidad.setBounds(6, 160, 256, 20);
		panel.add(lblIdentidad);
		txtIdentidad = new JFormattedTextField(identidad);
		txtIdentidad.setForeground(new Color(0, 0, 128));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(37, 181, 185, 20);
		panel.add(txtIdentidad);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(58, 47, 139, 135);
		panel.add(lblNewLabel);
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		cbxGrado = new JComboBox();
		cbxGrado.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxGrado.setModel(new DefaultComboBoxModel(new String[] { "Séptimo", "Decimo" }));
		cbxGrado.setBounds(37, 223, 185, 20);
		panel.add(cbxGrado);

		JLabel label_6 = new JLabel("DATOS DEL COMPROBANTE");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_6.setBounds(10, 47, 233, 20);
		panel.add(label_6);

		JLabel lblGeneradorDeCredenciales = new JLabel("GENERADOR DE CREDENCIALES");
		lblGeneradorDeCredenciales.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneradorDeCredenciales.setForeground(Color.BLACK);
		lblGeneradorDeCredenciales.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblGeneradorDeCredenciales.setBounds(16, 11, 222, 20);
		panel.add(lblGeneradorDeCredenciales);

		panel_1 = new JPanel();
		panel_1.setBounds(6, 11, 253, 419);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);

		JLabel label = new JLabel("Grado:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label.setBounds(83, 202, 112, 20);
		panel_1.add(label);

		JButton btnImprimir2 = new JButton("IMPRIMIR");
		btnImprimir2.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnImprimir2.setBackground(new Color(46, 139, 87));
		btnImprimir2.setBounds(38, 373, 188, 23);
		panel_1.add(btnImprimir2);

		JLabel label_1 = new JLabel("8\u00B0,9\u00B0,11\u00B0,12\u00B0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(46, 139, 87));
		label_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_1.setBounds(20, 50, 239, 20);
		panel_1.add(label_1);

		JLabel label_2 = new JLabel("Usuario:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_2.setBounds(8, 277, 258, 20);
		panel_1.add(label_2);

		txtUsuario2 = new JTextField();
		txtUsuario2.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario2.setForeground(new Color(0, 0, 128));
		txtUsuario2.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtUsuario2.setEditable(false);
		txtUsuario2.setColumns(10);
		txtUsuario2.setBounds(38, 299, 188, 20);
		panel_1.add(txtUsuario2);

		JLabel label_3 = new JLabel("Contrase\u00F1a:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_3.setBounds(10, 320, 258, 20);
		panel_1.add(label_3);

		txtContraseña2 = new JTextField();
		txtContraseña2.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña2.setForeground(new Color(0, 0, 128));
		txtContraseña2.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtContraseña2.setEditable(false);
		txtContraseña2.setColumns(10);
		txtContraseña2.setBounds(38, 342, 188, 20);
		panel_1.add(txtContraseña2);

		JButton btnGenerarCredenciales2 = new JButton("GENERAR CREDENCIALES");
		btnGenerarCredenciales2.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnGenerarCredenciales2.setBackground(new Color(255, 215, 0));
		btnGenerarCredenciales2.setBounds(38, 254, 188, 23);
		panel_1.add(btnGenerarCredenciales2);

		JLabel label_4 = new JLabel("Identidad:");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_4.setBounds(10, 160, 256, 20);
		panel_1.add(label_4);
		txtIdentidad2 = new JFormattedTextField(identidad1);
		txtIdentidad2.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad2.setForeground(new Color(0, 0, 128));
		txtIdentidad2.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIdentidad2.setColumns(10);
		txtIdentidad2.setBounds(38, 181, 188, 20);
		panel_1.add(txtIdentidad2);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(68, 50, 139, 135);
		panel_1.add(label_5);
		final ImageIcon icono22 = new ImageIcon(
				logo22.getImage().getScaledInstance(label_5.getWidth(), label_5.getHeight(), Image.SCALE_DEFAULT));
		label_5.setIcon(icono22);

		JComboBox cbxGrado2 = new JComboBox();
		cbxGrado2.setModel(
				new DefaultComboBoxModel(new String[] { "Octavo", "Noveno", "Und\u00E9cimo", "Duod\u00E9cimo" }));
		cbxGrado2.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxGrado2.setBounds(38, 223, 188, 20);
		panel_1.add(cbxGrado2);

		JLabel label_7 = new JLabel("DATOS DEL COMPROBANTE");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_7.setBounds(10, 31, 249, 20);
		panel_1.add(label_7);

		JLabel lblGeneradorDeCredenciales_1 = new JLabel("GENERADOR DE CREDENCIALES");
		lblGeneradorDeCredenciales_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneradorDeCredenciales_1.setForeground(Color.BLACK);
		lblGeneradorDeCredenciales_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblGeneradorDeCredenciales_1.setBounds(10, 11, 239, 20);
		panel_1.add(lblGeneradorDeCredenciales_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(6, 432, 253, 32);
		contentPane.add(panel_3);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(new Color(0, 100, 0));
		lblFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblFecha.setBounds(10, 0, 52, 30);
		panel_3.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(new Color(0, 100, 0));
		lblHora.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblHora.setBounds(137, 0, 52, 30);
		panel_3.add(lblHora);

		JLabel lblFechaActual = new JLabel();
		lblFechaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaActual.setForeground(Color.BLACK);
		lblFechaActual.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblFechaActual.setBounds(63, 0, 71, 30);
		panel_3.add(lblFechaActual);

		JLabel lblHoraActual = new JLabel();
		lblHoraActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraActual.setForeground(Color.BLACK);
		lblHoraActual.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblHoraActual.setBounds(187, 0, 66, 30);
		panel_3.add(lblHoraActual);

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
	
	Timer time = new Timer();
	public TimerTask tarea = new TimerTask() {
		@Override
		public void run() {
			Calendar calendario = new GregorianCalendar();
			Date fechaHoraActual = new Date();
			calendario.setTime(fechaHoraActual);
			String horas;
			String minutos;
			String segundos;
			String ampm;
			Thread hilo = null;
			Thread hilo2;
			hilo2 = Thread.currentThread();
			hilo = new Thread();
			hilo.start();
			ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
			if (ampm.equals("PM")) {
				int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
				horas = h > 9 ? "" + h : "0" + h;
			} else {
				horas = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY)
						: "0" + calendario.get(Calendar.HOUR_OF_DAY);
			}
			minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE)
					: "0" + calendario.get(Calendar.MINUTE);
			segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND)
					: "0" + calendario.get(Calendar.SECOND);

			lbl.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("EEEEEEEEE dd 'de' MMMMM 'del' yyyy");
		date = cal.getTime();
		return df.format(date);
	}
}
