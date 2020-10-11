package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;
import controles.control_usuario;
import recursos.BCrypt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class ventana_login extends JFrame {

	private JPanel contentPane;
	public JFormattedTextField txtUsuario;
	public JPasswordField txtContrase�a;
	public JButton btnIngresar;
	public JLabel lblAlerta;

	public static String rol = null;
	public static String NombreRol = null;
	public static String nombre = null;
	public static String identidad = null;
	public static String contrase�a = null;
	private JLabel lblNewLabel;
	private JLabel lblParaLaMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana_login frame = new ventana_login();
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
	public ventana_login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 433, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_ido.png")));

		lblAlerta = new JLabel("");
		lblAlerta.setBounds(53, 325, 321, 26);
		contentPane.add(lblAlerta);
		lblAlerta.setForeground(Color.WHITE);
		lblAlerta.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblAlerta.setBackground(Color.WHITE);
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(53, 11, 321, 303);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 0, 100));

		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(91, 149, 150, 14);
		panel.add(lblUsuario);

		MaskFormatter identidadF = null;
		try {
			identidadF = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtUsuario = new JFormattedTextField(identidadF);
		txtUsuario.setForeground(new Color(0, 0, 0));
		txtUsuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(91, 180, 150, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		InputMap map1 = txtUsuario.getInputMap(JComponent.WHEN_FOCUSED);
		map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(91, 201, 150, 14);
		panel.add(lblContrasea);

		txtContrase�a = new JPasswordField();
		txtContrase�a.setForeground(new Color(0, 0, 0));
		txtContrase�a.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txtContrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrase�a.setColumns(10);
		txtContrase�a.setBounds(91, 230, 150, 20);
		panel.add(txtContrase�a);
		InputMap map2 = txtContrase�a.getInputMap(JComponent.WHEN_FOCUSED);
		map2.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContrase�a.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContrase�a.getText().length() == 15)
					e.consume();

				if (txtContrase�a.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContrase�a.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String user = String.valueOf(txtUsuario.getText().toString());
					String pass = String.valueOf(txtContrase�a.getText().toString());
					if (user.equals("") && pass.equals("")) {
						lblAlerta.setText("Los campos (Usuario) y (Contrase�a) estan vacios.");
						lblAlerta.setForeground(Color.RED);
					} else {
						if (user.equals("")) {
							lblAlerta.setText("El campo de (Usuario) esta vacio.");
							lblAlerta.setForeground(Color.RED);
						} else {
							if (pass.equals("")) {
								lblAlerta.setText("El campo de (Contrase�a) esta vacio.");
								lblAlerta.setForeground(Color.RED);
							} else {
								buscarUsuario();
								if (txtUsuario.getText().toString().equals(identidad)) {

									if (user.equals(identidad) && BCrypt.checkpw(pass, contrase�a)) {
										ventana_principal principal = new ventana_principal();
										Timer time = new Timer();
										time.schedule(principal.tarea, 0, 1000);
										principal.setLocationRelativeTo(null);
										principal.setVisible(true);
										buscarRol();
										principal.lblFechaActual.setText(ventana_principal.getFecha());
										principal.setTitle("Usuario: " + nombre + "    Permisos: " + NombreRol);
										if (rol.equals("1")) {
											principal.btnUsuarios.setEnabled(true);
										} else {
											if (rol.equals("2")) {
												principal.btnUsuarios.setEnabled(false);

											} else {
												if (rol.equals("3")) {
													principal.btnUsuarios.setEnabled(false);

												} else {
													principal.btnUsuarios.setEnabled(false);

												}

											}

										}
										dispose();

									} else {
										lblAlerta.setText("El usuario y contrase�a son incorrectas");
										lblAlerta.setForeground(Color.RED);

									}

								} else {
									lblAlerta.setText("El usuario y contrase�a son incorrectas");
									lblAlerta.setForeground(Color.RED);
								}
							}
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		btnIngresar = new JButton("INGRESAR");
		btnIngresar.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user = String.valueOf(txtUsuario.getText().toString());
				String pass = String.valueOf(txtContrase�a.getText().toString());
				if (user.equals("             ") && pass.equals("")) {
					lblAlerta.setText("Los campos (Usuario) y (Contrase�a) estan vacios.");
					lblAlerta.setForeground(Color.WHITE);
				} else {
					if (user.equals("")) {
						lblAlerta.setText("El campo de (Usuario) esta vacio.");
						lblAlerta.setForeground(Color.WHITE);
					} else {
						if (pass.equals("")) {
							lblAlerta.setText("El campo de (Contrase�a) esta vacio.");
							lblAlerta.setForeground(Color.WHITE);
						} else {
							buscarUsuario();

							if (txtUsuario.getText().toString().equals(identidad)) {

								if (user.equals(identidad) && BCrypt.checkpw(pass, contrase�a)) {
									ventana_principal principal = new ventana_principal();
									Timer time = new Timer();
									time.schedule(principal.tarea, 0, 1000);
									principal.setLocationRelativeTo(null);
									principal.setVisible(true);
									buscarRol();
									principal.lblFechaActual.setText(ventana_principal.getFecha());
									principal.setTitle("Usuario: " + nombre + "    Permisos: " + NombreRol);
									if (rol.equals("1")) {
										principal.btnUsuarios.setEnabled(true);
										principal.btn7_10.setEnabled(true);
										principal.btn8_9_11_12.setEnabled(true);
										principal.btnComprobarMatricula.setEnabled(true);
										principal.btnCredencialesRegistradas.setEnabled(true);

									} else {
										if (rol.equals("2")) {
											principal.btnUsuarios.setEnabled(false);
											principal.btnUsuarios.setEnabled(true);
											principal.btn7_10.setEnabled(true);
											principal.btn8_9_11_12.setEnabled(true);
											principal.btnComprobarMatricula.setEnabled(true);
											principal.btnCredencialesRegistradas.setEnabled(true);

										} else {
											if (rol.equals("3")) {
												principal.btnUsuarios.setEnabled(false);
												principal.btnUsuarios.setEnabled(false);
												principal.btn7_10.setEnabled(false);
												principal.btn8_9_11_12.setEnabled(false);
												principal.btnComprobarMatricula.setEnabled(false);
												principal.btnCredencialesRegistradas.setEnabled(false);
												principal.btnCredenciales.setEnabled(false);

												principal.btnComprobarMatricula.setEnabled(false);
												principal.btnImprimir.setEnabled(false);

											} else {
												principal.btnUsuarios.setEnabled(false);
												principal.btn7_10.setEnabled(true);
												principal.btn8_9_11_12.setEnabled(true);
												principal.btnComprobarMatricula.setEnabled(true);
												principal.btnCredencialesRegistradas.setEnabled(true);

											}

										}

									}
									dispose();

								} else {
									lblAlerta.setText("El usuario y contrase�a son incorrectas");
									lblAlerta.setForeground(Color.RED);

								}

							} else {
								lblAlerta.setText("El usuario y contrase�a son incorrectas");
								lblAlerta.setForeground(Color.RED);
							}
						}
					}
				}
			}

		});
		btnIngresar.setBounds(91, 261, 150, 23);
		panel.add(btnIngresar);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(91, 23, 150, 140);
		panel.add(lblNewLabel);
		final ImageIcon logo11 = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		final ImageIcon icono11 = new ImageIcon(logo11.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono11);

		lblParaLaMatricula = new JLabel("SISTEMA GENERADOR DE PINES IDO 2020");
		lblParaLaMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaLaMatricula.setForeground(Color.WHITE);
		lblParaLaMatricula.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblParaLaMatricula.setBounds(10, 11, 301, 26);
		panel.add(lblParaLaMatricula);

		JLabel lblI = new JLabel("Ingrese su identidad:");
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setForeground(Color.WHITE);
		lblI.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblI.setBounds(91, 163, 150, 14);
		panel.add(lblI);

		JLabel lblIngreseSuContrasea = new JLabel("Ingrese su contrase\u00F1a:");
		lblIngreseSuContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuContrasea.setForeground(Color.WHITE);
		lblIngreseSuContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 10));
		lblIngreseSuContrasea.setBounds(91, 213, 150, 14);
		panel.add(lblIngreseSuContrasea);

		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(-44, 0, 611, 374);
		contentPane.add(lblFoto);
		final ImageIcon logo = new ImageIcon(getClass().getResource("/recursos/ido_foto.jpg"));
		final ImageIcon icono = new ImageIcon(
				logo.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT));
		lblFoto.setIcon(icono);

	}

	public void buscarUsuario() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto
					.executeQuery("SELECT * FROM users WHERE RNE_Empleado='" + txtUsuario.getText().toString() + "'");
			while (rs.next()) {
				rol = rs.getString("Id_Rol");
				nombre = rs.getString("name");
				identidad = rs.getString("RNE_Empleado");
				contrase�a = rs.getString("password");
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
}
