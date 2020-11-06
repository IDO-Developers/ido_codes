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
import clases.pines;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_alumnos;
import consultas.consultas_usuario;
import controles.control_pines;
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
import javax.swing.border.BevelBorder;

public class ventana_principal extends JFrame {

	public JPanel contentPane;
	public JPanel panel;
	public JButton btnImprimir;
	public JButton btnImprimir1;
	
	public static String Registro_Nombres;
	public static String Registro_Apellidos;

	public static DefaultComboBoxModel modelo;
	public static String id_rol;
	public static String identidadRepetida;

	public static JTextField txtUsuario;
	public static JTextField txtContraseña;
	public static JTextField txtIdentidad;

	public static JTextField txtUsuario1;
	public static JTextField txtContraseña1;
	public static JTextField txtIdentidad1;

	public JComboBox cbxGrado;
	public JComboBox cbxGrado1;

	public static String cadena = null;
	public static String cadena1 = null;
	public static String contraseñaEncriptada = null;
	public JButton btnUsuarios;
	public JButton btn8_9_11_12;
	public JButton btn7_10;
	public JButton btnCredenciales;
	public JButton btnCredenciales1;

	public static String id_grupo1;
	public static String id_grupo2;
	public static String id_rol_base;

	public static String user;
	public static String pass;

	public static String IDGRUPOSep;
	public static String GRADOSep;

	public static String IDGRUPODec;
	public static String GRADODec;

	public static JLabel lblHoraActual;
	public static JLabel lblFechaActual;

	public static String USUARIO_id = null;
	public static String USUARIO_users = null;

	public static String USUARIO_Registrar = null;
	public static String USUARIO_Actualizar = null;

	public static String USUARIO_Prematriculas = null;
	public static String ID_Prematriculas = null;
	public static String ROL = null;

	public static String rol = null;
	public static String NombreRol = null;
	public static String nombre = null;
	public static String identidad = null;
	public static String contraseña = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ventana_principal principal = new ventana_principal();
					principal.setVisible(true);
					principal.setLocationRelativeTo(null);
					principal.setVisible(true);
					Timer time = new Timer();
					time.schedule(principal.tarea, 0, 1000);
					lblFechaActual.setText(getFecha());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventana_principal() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 774, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(25, 25, 112));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setContentPane(contentPane);

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				close();
			}
		});

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
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		final ImageIcon logo22 = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(532, 49, 217, 463);
		contentPane.add(panel_2);
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setLayout(null);

		JLabel lblMen = new JLabel("Men\u00FA");
		lblMen.setBounds(10, 11, 197, 30);
		panel_2.add(lblMen);
		lblMen.setForeground(new Color(0, 100, 0));
		lblMen.setHorizontalAlignment(SwingConstants.CENTER);
		lblMen.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));

		btn7_10 = new JButton();
		btn7_10.setToolTipText("Generador de credenciales para 7\u00B0 y 10\u00B0");
		btn7_10.setBounds(51, 63, 108, 102);
		panel_2.add(btn7_10);
		btn7_10.setBackground(Color.ORANGE);
		final ImageIcon icono11 = new ImageIcon(
				SEPYDEC.getImage().getScaledInstance(btn7_10.getWidth(), btn7_10.getHeight(), Image.SCALE_DEFAULT));
		btn7_10.setIcon(icono11);
		btn7_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Rol = ventana_login.rol.toString();
				String Nombre = ventana_login.nombre.toString();
				String NombreRol = ventana_login.NombreRol.toString();
				ventana_principal principal = new ventana_principal();
				Timer time = new Timer();
				time.schedule(principal.tarea, 0, 1000);
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				buscarRol();
				principal.lblFechaActual.setText(ventana_principal.getFecha());
				principal.setTitle("Usuario: " + Nombre + "    Permisos: " + NombreRol);
				if (Rol.equals("1")) {
					principal.btnUsuarios.setEnabled(true);
					principal.btn7_10.setEnabled(true);
					principal.btn8_9_11_12.setEnabled(true);

				} else {
					if (Rol.equals("2")) {
						principal.btnUsuarios.setEnabled(false);
						principal.btnUsuarios.setEnabled(true);
						principal.btn7_10.setEnabled(true);
						principal.btn8_9_11_12.setEnabled(true);

					} else {
						if (Rol.equals("3")) {
							principal.btnUsuarios.setEnabled(false);
							principal.btnUsuarios.setEnabled(false);
							principal.btn7_10.setEnabled(false);
							principal.btn8_9_11_12.setEnabled(false);
							principal.btnImprimir.setEnabled(false);

						} else {
							principal.btnUsuarios.setEnabled(false);
							principal.btn7_10.setEnabled(true);
							principal.btn8_9_11_12.setEnabled(true);

						}

					}

				}
				dispose();
			}
		});

		btn8_9_11_12 = new JButton();
		btn8_9_11_12.setEnabled(false);
		btn8_9_11_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				String Rol = ventana_login.rol.toString();
				String Nombre = ventana_login.nombre.toString();
				String NombreRol = ventana_login.NombreRol.toString();
				ventana_principal2 principal = new ventana_principal2();
				Timer time = new Timer();
				time.schedule(principal.tarea, 0, 1000);
				principal.setLocationRelativeTo(null);
				principal.setVisible(true);
				buscarRol();
				principal.lblFechaActual.setText(ventana_principal2.getFecha());
				principal.setTitle("Usuario: " + Nombre + "    Permisos: " + NombreRol);
				if (Rol.equals("1")) {
					principal.btnUsuarios.setEnabled(true);
					principal.btn7_10.setEnabled(true);
					principal.btn8_9_11_12.setEnabled(true);

				} else {
					if (Rol.equals("2")) {
						principal.btnUsuarios.setEnabled(false);
						principal.btnUsuarios.setEnabled(true);
						principal.btn7_10.setEnabled(true);
						principal.btn8_9_11_12.setEnabled(true);

					} else {
						if (Rol.equals("3")) {
							principal.btnUsuarios.setEnabled(false);
							principal.btnUsuarios.setEnabled(false);
							principal.btn7_10.setEnabled(false);
							principal.btn8_9_11_12.setEnabled(false);
							principal.btnImprimir.setEnabled(false);

						} else {
							principal.btnUsuarios.setEnabled(false);
							principal.btn7_10.setEnabled(true);
							principal.btn8_9_11_12.setEnabled(true);

						}

					}

				}
				dispose();
				*/
			}
		});
		btn8_9_11_12.setToolTipText("Generador de credenciales para 8\u00B0,9\u00B0,11\u00B0 y 12\u00B0");
		btn8_9_11_12.setBounds(51, 197, 108, 102);
		panel_2.add(btn8_9_11_12);
		btn8_9_11_12.setBackground(Color.ORANGE);
		final ImageIcon icono111 = new ImageIcon(OCTNOVUNDDUO.getImage().getScaledInstance(btn8_9_11_12.getWidth(),
				btn8_9_11_12.getHeight(), Image.SCALE_DEFAULT));
		btn8_9_11_12.setIcon(icono111);

		btnUsuarios = new JButton();
		btnUsuarios.setToolTipText("Registro de usuarios");
		btnUsuarios.setBounds(10, 322, 89, 85);
		panel_2.add(btnUsuarios);
		btnUsuarios.setBackground(new Color(30, 144, 255));
		final ImageIcon iconom = new ImageIcon(LOGOUSER.getImage().getScaledInstance(btnUsuarios.getWidth(),
				btnUsuarios.getHeight(), Image.SCALE_DEFAULT));
		btnUsuarios.setIcon(iconom);
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

		JButton btnCerrarSesion = new JButton();
		btnCerrarSesion.setToolTipText("Cerrar Sesi\u00F3n");
		btnCerrarSesion.setBounds(118, 322, 89, 85);
		panel_2.add(btnCerrarSesion);
		btnCerrarSesion.setBackground(new Color(178, 34, 34));
		final ImageIcon iconom1 = new ImageIcon(LOGOSALIR.getImage().getScaledInstance(btnCerrarSesion.getWidth(),
				btnCerrarSesion.getHeight(), Image.SCALE_DEFAULT));
		btnCerrarSesion.setIcon(iconom1);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				ventana_login login = new ventana_login();
				ventana_login principal = new ventana_login();
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);
			}
		});

		JLabel lblAcercaDe = new JLabel("Acerca de.");
		lblAcercaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcercaDe.setForeground(new Color(0, 100, 0));
		lblAcercaDe.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblAcercaDe.setBounds(66, 418, 89, 24);
		panel_2.add(lblAcercaDe);
		lblAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acerca_de desarrolladores = new acerca_de();
				desarrolladores.setVisible(true);
				desarrolladores.setLocationRelativeTo(null);
			}
		});

		JLabel lblCredencialesPara = new JLabel("Pines");
		lblCredencialesPara.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredencialesPara.setForeground(Color.BLACK);
		lblCredencialesPara.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblCredencialesPara.setBounds(51, 39, 108, 13);
		panel_2.add(lblCredencialesPara);

		JLabel lblParaY = new JLabel("Para 7\u00B0 y 10\u00B0");
		lblParaY.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaY.setForeground(Color.BLACK);
		lblParaY.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblParaY.setBounds(51, 49, 108, 13);
		panel_2.add(lblParaY);

		JLabel lblPines = new JLabel("Pines");
		lblPines.setHorizontalAlignment(SwingConstants.CENTER);
		lblPines.setForeground(Color.BLACK);
		lblPines.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblPines.setBounds(51, 173, 108, 13);
		panel_2.add(lblPines);

		JLabel lblParaY_1 = new JLabel("Para 8\u00B0,9\u00B0,11\u00B0 y 12\u00B0");
		lblParaY_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaY_1.setForeground(Color.BLACK);
		lblParaY_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblParaY_1.setBounds(51, 183, 108, 13);
		panel_2.add(lblParaY_1);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setForeground(Color.BLACK);
		lblUsuarios.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblUsuarios.setBounds(10, 310, 89, 13);
		panel_2.add(lblUsuarios);

		JLabel lblUsuarios_1 = new JLabel("Cerrar sesi\u00F3n");
		lblUsuarios_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios_1.setForeground(Color.BLACK);
		lblUsuarios_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblUsuarios_1.setBounds(118, 310, 89, 13);
		panel_2.add(lblUsuarios_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 517, 516, 32);
		contentPane.add(panel_3);
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(null);

		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(new Color(0, 100, 0));
		lblFecha.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblFecha.setBounds(92, 0, 56, 30);
		panel_3.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(new Color(0, 100, 0));
		lblHora.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblHora.setBounds(263, 0, 62, 30);
		panel_3.add(lblHora);

		lblFechaActual = new JLabel();
		lblFechaActual.setBackground(new Color(0, 0, 205));
		lblFechaActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaActual.setForeground(Color.BLACK);
		lblFechaActual.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblFechaActual.setBounds(142, 0, 112, 30);
		panel_3.add(lblFechaActual);

		lblHoraActual = new JLabel();
		lblHoraActual.setBackground(new Color(0, 0, 205));
		lblHoraActual.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraActual.setForeground(Color.BLACK);
		lblHoraActual.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblHoraActual.setBounds(318, 0, 112, 30);
		panel_3.add(lblHoraActual);

		panel = new JPanel();
		panel.setBounds(10, 49, 253, 463);
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
		btnImprimir.setBackground(new Color(255, 215, 0));
		btnImprimir.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnImprimir.setBounds(37, 373, 185, 23);
		panel.add(btnImprimir);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtIdentidad.getText().toString().equals("") || txtContraseña.getText().toString().equals("")
						|| txtUsuario.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Por verifique que los datos completos, antes de imprimir.");
				} else {
					ventana_comprobante comprobante = new ventana_comprobante();
					ventana_comprobante.lblIdentidad.setText(txtUsuario.getText().toString());
					ventana_comprobante.lblCodigo.setText(cadena);
					ventana_comprobante.getHora();
					ventana_comprobante.lblFecha.setText(ventana_comprobante.getFecha());
					comprobante.setVisible(true);
					comprobante.setLocationRelativeTo(null);
				}

			}
		});

		JLabel USUARIOS = new JLabel("7\u00B0 y 10\u00B0");
		USUARIOS.setForeground(new Color(46, 139, 87));
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(16, 49, 222, 20);
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

		btnCredenciales = new JButton("GENERAR PIN");
		btnCredenciales.setBackground(new Color(60, 179, 113));
		btnCredenciales.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnCredenciales.setBounds(37, 254, 185, 23);
		panel.add(btnCredenciales);
		btnCredenciales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdentidad.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor escriba la identidad del alumno.");
				} else {
					String identidad = txtIdentidad.getText().toString();
					if (identidad.length() < 13) {
						JOptionPane.showMessageDialog(null,
								"Por favor escriba correctamente la identidad del alumno. ejemplo:"
										+ " (0703200701010) " + " 13 digitos seguidos");
					} else {
						preguntarPorGrupoRegistro();
						preguntarPorRolRegistro();
						consultarPIN_registro();
						if (USUARIO_Registrar == null) {
							Registrar_Usuario_Contraseña_Identidad_Grupo();
						} else {
							JOptionPane.showMessageDialog(null,
									"El alumno ya cuenta con un PIN de prematricula registrado, si desea recuperalo, debe actualizarlo.");
						}
					}
				}
			}

		});

		JLabel lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblIdentidad.setBounds(6, 162, 256, 20);
		panel.add(lblIdentidad);

		txtIdentidad = new JTextField();
		txtIdentidad.setForeground(new Color(0, 0, 128));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIdentidad.setColumns(10);
		txtIdentidad.setBounds(37, 183, 185, 20);
		panel.add(txtIdentidad);
		InputMap map = txtIdentidad.getInputMap(JComponent.WHEN_FOCUSED);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad.addKeyListener(new KeyListener() {
			@Override
			// Metodo que valida el ingreso de solo numeros
			public void keyTyped(KeyEvent ke) {
				if (txtIdentidad.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtIdentidad.setText("");
				}

				char c = ke.getKeyChar();
				if ((c < '0' || c > '9'))
					ke.consume();

				if (txtIdentidad.getText().length() == 13)
					ke.consume();
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(61, 49, 139, 135);
		panel.add(lblNewLabel);
		final ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono);

		cbxGrado = new JComboBox();
		cbxGrado.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxGrado.setModel(new DefaultComboBoxModel(new String[] { "S\u00E9ptimo", "D\u00E9cimo" }));
		cbxGrado.setBounds(37, 223, 185, 20);
		panel.add(cbxGrado);

		JLabel label_6 = new JLabel("DATOS DEL COMPROBANTE");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_6.setBounds(16, 31, 233, 20);
		panel.add(label_6);

		JLabel lblGeneradorDeCredenciales = new JLabel("GENERADOR DE PINES");
		lblGeneradorDeCredenciales.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneradorDeCredenciales.setForeground(new Color(50, 205, 50));
		lblGeneradorDeCredenciales.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblGeneradorDeCredenciales.setBounds(16, 11, 222, 20);
		panel.add(lblGeneradorDeCredenciales);

		JButton btnLimpiar = new JButton("LIMPIAR");
		btnLimpiar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnLimpiar.setBackground(new Color(70, 130, 180));
		btnLimpiar.setBounds(37, 422, 185, 23);
		panel.add(btnLimpiar);
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtIdentidad.setText("");
				txtUsuario.setText("");
				txtContraseña.setText("");

			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(269, 49, 257, 463);
		contentPane.add(panel_1);

		JLabel lblUsuario_2 = new JLabel("Grado:");
		lblUsuario_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario_2.setBounds(79, 202, 112, 20);
		panel_1.add(lblUsuario_2);

		btnImprimir1 = new JButton("IMPRIMIR");
		btnImprimir1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnImprimir1.setBackground(new Color(255, 215, 0));
		btnImprimir1.setBounds(37, 373, 185, 23);
		panel_1.add(btnImprimir1);
		btnImprimir1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtIdentidad1.getText().toString().equals("") || txtContraseña1.getText().toString().equals("")
						|| txtUsuario1.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Por verifique que los datos completos, antes de imprimir.");
				} else {
					ventana_comprobante comprobante = new ventana_comprobante();
					ventana_comprobante.lblIdentidad.setText(txtUsuario1.getText().toString());
					ventana_comprobante.lblCodigo.setText(cadena1);
					ventana_comprobante.getHora();
					ventana_comprobante.lblFecha.setText(ventana_comprobante.getFecha());
					comprobante.setVisible(true);
					comprobante.setLocationRelativeTo(null);
				}

			}
		});

		JLabel USUARIOS_1 = new JLabel("7\u00B0 y 10\u00B0");
		USUARIOS_1.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS_1.setForeground(new Color(46, 139, 87));
		USUARIOS_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		USUARIOS_1.setBounds(16, 49, 222, 20);
		panel_1.add(USUARIOS_1);

		JLabel lblUsuario_1_1 = new JLabel("Usuario:");
		lblUsuario_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario_1_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario_1_1.setBounds(4, 277, 258, 20);
		panel_1.add(lblUsuario_1_1);

		txtUsuario1 = new JTextField();
		txtUsuario1.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario1.setForeground(new Color(0, 0, 128));
		txtUsuario1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtUsuario1.setEditable(false);
		txtUsuario1.setColumns(10);
		txtUsuario1.setBounds(37, 299, 185, 20);
		panel_1.add(txtUsuario1);

		JLabel lblContrasea_1 = new JLabel("Contrase\u00F1a:");
		lblContrasea_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblContrasea_1.setBounds(6, 320, 258, 20);
		panel_1.add(lblContrasea_1);

		txtContraseña1 = new JTextField();
		txtContraseña1.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña1.setForeground(new Color(0, 0, 128));
		txtContraseña1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtContraseña1.setEditable(false);
		txtContraseña1.setColumns(10);
		txtContraseña1.setBounds(37, 342, 185, 20);
		panel_1.add(txtContraseña1);

		btnCredenciales1 = new JButton("ACTUALIZAR PIN");
		btnCredenciales1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnCredenciales1.setBackground(new Color(60, 179, 113));
		btnCredenciales1.setBounds(37, 254, 185, 23);
		panel_1.add(btnCredenciales1);
		btnCredenciales1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtIdentidad1.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor escriba la identidad del alumno.");
				} else {
					String identidad11 = txtIdentidad1.getText().toString();
					if (identidad11.length() < 13) {
						JOptionPane.showMessageDialog(null,
								"Por favor escriba correctamente la identidad del alumno. ejemplo:"
										+ " (0703200701010) " + " 13 digitos seguidos");
					} else {
						preguntarPorGrupoActualizacion();
						preguntarPorRolActualizacion();
						consultarPIN_Actualizacion();
						if (USUARIO_Actualizar == null) {
							JOptionPane.showMessageDialog(null,
									"El alumno no cuenta con un PIN de prematricula registrado, si desea actualizarlo, debe registrarlo antes.");
						} else {
							buscarRegistrosUsuario();
							System.out.print(Registro_Nombres);
							System.out.print(Registro_Apellidos);
							if (Registro_Nombres == null && Registro_Apellidos == null) {
								Actualizar_Usuario_Contraseña_Identidad_Grupo();
							} else {
								JOptionPane.showMessageDialog(null,
										"El alumno ya se encuentra registrado en el sistema y los datos estan guardados correctamente no hace falta actualizar el PIN nuevamente.");
							}
							
						}
					}

				}
			}
		});

		JLabel lblIdentidad_1 = new JLabel("Identidad:");
		lblIdentidad_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentidad_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblIdentidad_1.setBounds(6, 162, 256, 20);
		panel_1.add(lblIdentidad_1);

		txtIdentidad1 = new JTextField();
		txtIdentidad1.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad1.setForeground(new Color(0, 0, 128));
		txtIdentidad1.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIdentidad1.setColumns(10);
		txtIdentidad1.setBounds(37, 183, 185, 20);
		panel_1.add(txtIdentidad1);
		InputMap map1 = txtIdentidad1.getInputMap(JComponent.WHEN_FOCUSED);
		map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtIdentidad1.addKeyListener(new KeyListener() {
			@Override
			// Metodo que valida el ingreso de solo numeros
			public void keyTyped(KeyEvent ke) {
				if (txtIdentidad1.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtIdentidad1.setText("");
				}

				char c = ke.getKeyChar();
				if ((c < '0' || c > '9'))
					ke.consume();

				if (txtIdentidad1.getText().length() == 13)
					ke.consume();
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(61, 49, 139, 135);
		panel_1.add(lblNewLabel_1);
		final ImageIcon icono2 = new ImageIcon(logo.getImage().getScaledInstance(lblNewLabel_1.getWidth(),
				lblNewLabel_1.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(icono2);

		cbxGrado1 = new JComboBox();
		cbxGrado1.setModel(new DefaultComboBoxModel(new String[] { "S\u00E9ptimo", "D\u00E9cimo" }));
		cbxGrado1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		cbxGrado1.setBounds(37, 223, 185, 20);
		panel_1.add(cbxGrado1);

		JLabel label_6_1 = new JLabel("DATOS DEL COMPROBANTE");
		label_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_6_1.setForeground(Color.BLACK);
		label_6_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		label_6_1.setBounds(16, 31, 222, 20);
		panel_1.add(label_6_1);

		JLabel lblGeneradorDeCredenciales_1 = new JLabel("ACTUALIZACION DE PINES");
		lblGeneradorDeCredenciales_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGeneradorDeCredenciales_1.setForeground(new Color(30, 144, 255));
		lblGeneradorDeCredenciales_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblGeneradorDeCredenciales_1.setBounds(16, 11, 222, 20);
		panel_1.add(lblGeneradorDeCredenciales_1);

		JButton btnLimpiar1 = new JButton("LIMPIAR");
		btnLimpiar1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnLimpiar1.setBackground(new Color(70, 130, 180));
		btnLimpiar1.setBounds(37, 422, 185, 23);
		panel_1.add(btnLimpiar1);

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3_1.setBackground(Color.WHITE);
		panel_3_1.setBounds(10, 11, 739, 32);
		contentPane.add(panel_3_1);

		JLabel lblSistemaGeneradorDe = new JLabel("SISTEMA GENERADOR DE PINES DE PREMATRICULA IDO 2021.");
		lblSistemaGeneradorDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaGeneradorDe.setForeground(new Color(0, 0, 0));
		lblSistemaGeneradorDe.setFont(new Font("Segoe UI Black", Font.BOLD | Font.ITALIC, 15));
		lblSistemaGeneradorDe.setBounds(10, 0, 719, 30);
		panel_3_1.add(lblSistemaGeneradorDe);

		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3_2.setBackground(Color.WHITE);
		panel_3_2.setBounds(532, 517, 217, 32);
		contentPane.add(panel_3_2);

		JLabel lblFechaActual_1 = new JLabel();
		lblFechaActual_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaActual_1.setForeground(Color.BLACK);
		lblFechaActual_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblFechaActual_1.setBackground(new Color(0, 0, 205));
		lblFechaActual_1.setBounds(142, 0, 112, 30);
		panel_3_2.add(lblFechaActual_1);

		JLabel lblHoraActual_1 = new JLabel();
		lblHoraActual_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHoraActual_1.setForeground(Color.BLACK);
		lblHoraActual_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblHoraActual_1.setBackground(new Color(0, 0, 205));
		lblHoraActual_1.setBounds(318, 0, 112, 30);
		panel_3_2.add(lblHoraActual_1);

		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		btnCerrar.setBounds(0, 0, 217, 30);
		panel_3_2.add(btnCerrar);
		btnCerrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnCerrar.setBackground(new Color(240, 128, 128));
		btnLimpiar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtIdentidad1.setText("");
				txtUsuario1.setText("");
				txtContraseña1.setText("");

			}
		});

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

	@SuppressWarnings("unlikely-arg-type")
	public void generarCodigoActualizado() {

		String valorGrado = null;
		if (cbxGrado1.getSelectedItem().toString().equals("Séptimo")) {
			valorGrado = "SEP21-";
		} else {
			valorGrado = "DEC21-";
		}

		String NumGenerado = String.valueOf(genererNumero());
		cadena1 = valorGrado + NumGenerado;
	}

	public void preguntarPorCredencialesREGISTRO() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM users where RNE_Alumno= '" + txtIdentidad.getText().toString() + "'");
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

	public void preguntarPorCredencialesACTUALIZAR() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM users where RNE_Alumno= '" + txtIdentidad1.getText().toString() + "'");
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

	public void consultarPIN_registro() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM users where RNE_Alumno= '" + txtIdentidad.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				USUARIO_Registrar = rsr.getString("RNE_Alumno");
			}
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultarPIN_Actualizacion() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM users where RNE_Alumno= '" + txtIdentidad1.getText().toString() + "'");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				USUARIO_Actualizar = rsr.getString("RNE_Alumno");
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

	public void preguntarPorGrupoRegistro() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM Prematriculas where RNE_Alumno= '" + txtIdentidad.getText().toString() + "'");
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

	public void preguntarPorGrupoActualizacion() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement(
					"SELECT * FROM Prematriculas where RNE_Alumno= '" + txtIdentidad1.getText().toString() + "'");
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

	public void preguntarPorRolRegistro() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM Roles where Nombre_Rol='Alumno'");
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

	public void preguntarPorRolActualizacion() {
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM Roles where Nombre_Rol='Alumno'");
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

	// REGISTRO DE DATOS
	public void Registrar_Usuario_Contraseña_Identidad_Grupo() {
		txtUsuario.setText(txtIdentidad.getText().toString());
		generarCodigo();
		txtContraseña.setText(cadena);
		alumnos clase = new alumnos();
		alumnos clase2 = new alumnos();
		consultas_alumnos consulta = new consultas_alumnos();

		// Insertar credenciales en la tabla USERS
		clase.setRNE_Alumno(txtIdentidad.getText().toString());
		contraseñaEncriptada = recursos.BCrypt.hashpw(cadena, recursos.BCrypt.gensalt());
		clase.setPassword(contraseñaEncriptada);
		clase.setId_Rol(ROL);

		// Insertar Datos en la tabla PREMATRICULA.
		clase2.setRNE_Alumno(txtIdentidad.getText().toString());
		buscarIdGrupoSeptimo();
		buscarIdGrupoDecimo();
		if (cbxGrado.getSelectedItem().equals("Séptimo")) {
			clase2.setId_Grupo(IDGRUPOSep);
		} else {
			clase2.setId_Grupo(IDGRUPODec);
		}

		if (consulta.insertarUserYpass(clase) && consulta.insertarRNEyGrupo(clase2)) {
			JOptionPane.showMessageDialog(null, "Credenciales del alumno registradas!");

		} else {
			JOptionPane.showMessageDialog(null, "Error! Credenciales del alumno NO registradas!");

		}

	}

	// ACTUALIZACION DE DATOS
	public void Actualizar_Usuario_Contraseña_Identidad_Grupo() {
		txtUsuario1.setText(txtIdentidad1.getText().toString());
		generarCodigoActualizado();
		txtContraseña1.setText(cadena1);
		alumnos clase = new alumnos();
		alumnos clase2 = new alumnos();
		consultas_alumnos consulta = new consultas_alumnos();

		clase.setRNE_Alumno(txtIdentidad1.getText().toString());
		contraseñaEncriptada = recursos.BCrypt.hashpw(cadena1, recursos.BCrypt.gensalt());
		clase.setPassword(contraseñaEncriptada);
		clase.setId_Rol(ROL);
		clase.setId(Integer.parseInt(USUARIO_id));

		clase2.setRNE_Alumno(txtIdentidad1.getText().toString());
		buscarIdGrupoSeptimo();
		buscarIdGrupoDecimo();
		if (cbxGrado1.getSelectedItem().equals("Séptimo")) {
			clase2.setId_Grupo(IDGRUPOSep);
		} else {
			clase2.setId_Grupo(IDGRUPODec);
		}
		clase2.setId_Prematricula(Integer.parseInt(ID_Prematriculas));

		if (consulta.actualizarUserYpass(clase) && consulta.actualizarRNEyGrupo(clase2)) {
			JOptionPane.showMessageDialog(null, "Credenciales del alumno actualizadas!");

		} else {
			JOptionPane.showMessageDialog(null, "Error! Credenciales del alumno NO actualizadas!");

		}

	}

	// ACTUALIZACION DE PIN
	public void Actualizar_Pin() {
		txtUsuario1.setText(txtIdentidad1.getText().toString());
		generarCodigo();
		txtContraseña1.setText(cadena);

		alumnos clase = new alumnos();
		consultas_alumnos consulta = new consultas_alumnos();

		contraseñaEncriptada = recursos.BCrypt.hashpw(cadena, recursos.BCrypt.gensalt());
		clase.setPassword(contraseñaEncriptada);
		clase.setRNE_Alumno(txtIdentidad1.getText().toString());

		if (consulta.actualizarPIN(clase)) {
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

			lblHoraActual.setText(horas + ":" + minutos + ":" + segundos + " " + ampm);
		}
	};

	public static String getFecha() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("dd'-'MM'-'yyyy");
		date = cal.getTime();
		return df.format(date);
	}

	private void close() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void buscarUsuario() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM users WHERE RNE_Alumno='" + txtUsuario.getText().toString() + "'");
			while (rs.next()) {
				rol = rs.getString("Id_Rol");
				nombre = rs.getString("name");
				identidad = rs.getString("RNE_Alumno");
				contraseña = rs.getString("password");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void buscarRol() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM Roles WHERE Id_Rol='" + rol + "'");
			while (rs.next()) {
				NombreRol = rs.getString("Nombre_Rol");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void buscarIdGrupoSeptimo() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"SELECT * FROM u102794322_matricula_ido.Grupos inner join Modalidades on Modalidades.id=Grupos.Modalidad where Grado = 'Séptimo Grado' and Grupos.Grupo IS NULL \r\n"
							+ "and Grupos.Jornada IS NULL and Grupos.Modulo IS NULL;");
			while (rs.next()) {
				IDGRUPOSep = rs.getString("Id_Grupo");
				GRADOSep = rs.getString("Grado");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void buscarIdGrupoDecimo() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"SELECT * FROM u102794322_matricula_ido.Grupos inner join Modalidades on Modalidades.id=Grupos.Modalidad where Grado = 'Décimo Grado' and Grupos.Grupo IS NULL \r\n"
							+ "and Grupos.Jornada IS NULL and Grupos.Modulo IS NULL;");
			while (rs.next()) {
				IDGRUPODec = rs.getString("Id_Grupo");
				GRADODec = rs.getString("Grado");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void buscarRegistrosUsuario() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"select * from Registro__Alumnos where RNE_Alumno='" + txtIdentidad1.getText().toString() + "'");
			while (rs.next()) {
				Registro_Nombres = rs.getString("Nombres");
				Registro_Apellidos = rs.getString("Apellidos");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
