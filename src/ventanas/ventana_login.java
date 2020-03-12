package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	public JPasswordField txtContraseña;
	public JButton btnIngresar;
	public JLabel lblAlerta;

	public static String rol = null;
	public static String nombre = null;
	public static String identidad = null;
	public static String contraseña = null;
	private JLabel lblNewLabel;
	private JLabel lblSistemaDeGeneracion;
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
		setBounds(100, 100, 433, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_ido.png")));

		lblAlerta = new JLabel("");
		lblAlerta.setBounds(53, 285, 321, 26);
		contentPane.add(lblAlerta);
		lblAlerta.setForeground(Color.WHITE);
		lblAlerta.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblAlerta.setBackground(Color.WHITE);
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(53, 11, 321, 270);
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
		txtUsuario.setBounds(91, 164, 150, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(91, 184, 150, 14);
		panel.add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setForeground(new Color(0, 0, 0));
		txtContraseña.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(91, 199, 150, 20);
		panel.add(txtContraseña);

		txtContraseña.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtContraseña.getText().length() == 15)
					e.consume();

				if (txtContraseña.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContraseña.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String user = String.valueOf(txtUsuario.getText().toString());
					String pass = String.valueOf(txtContraseña.getText().toString());
					if (user.equals("") && pass.equals("")) {
						lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
						lblAlerta.setForeground(Color.RED);
					} else {
						if (user.equals("")) {
							lblAlerta.setText("El campo de (Usuario) esta vacio.");
							lblAlerta.setForeground(Color.RED);
						} else {
							if (pass.equals("")) {
								lblAlerta.setText("El campo de (Contraseña) esta vacio.");
								lblAlerta.setForeground(Color.RED);
							} else {
								buscarUsuario();
								if (txtUsuario.getText().toString().equals(identidad)) {

									if (user.equals(identidad) && BCrypt.checkpw(pass, contraseña)) {
										ventana_codigos_alumnos_7_10 alumnos = new ventana_codigos_alumnos_7_10();
										alumnos.setLocationRelativeTo(null);
										alumnos.setVisible(true);
										if (rol.equals("1")) {
											alumnos.btnUsuarios.setEnabled(true);
										} else {
											if (rol.equals("2")) {
												alumnos.btnUsuarios.setEnabled(false);

											} else {
												if (rol.equals("3")) {
													alumnos.btnUsuarios.setEnabled(false);

												} else {
													alumnos.btnUsuarios.setEnabled(false);

												}

											}

										}
										dispose();

									} else {
										lblAlerta.setText("El usuario y contraseña son incorrectas");
										lblAlerta.setForeground(Color.RED);

									}

								} else {
									lblAlerta.setText("El usuario y contraseña son incorrectas");
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
				String pass = String.valueOf(txtContraseña.getText().toString());
				if (user.equals("") && pass.equals("")) {
					lblAlerta.setText("Los campos (Usuario) y (Contraseña) estan vacios.");
					lblAlerta.setForeground(Color.WHITE);
				} else {
					if (user.equals("")) {
						lblAlerta.setText("El campo de (Usuario) esta vacio.");
						lblAlerta.setForeground(Color.WHITE);
					} else {
						if (pass.equals("")) {
							lblAlerta.setText("El campo de (Contraseña) esta vacio.");
							lblAlerta.setForeground(Color.WHITE);
						} else {
							buscarUsuario();

							if (txtUsuario.getText().toString().equals(identidad)) {

								if (user.equals(identidad) && BCrypt.checkpw(pass, contraseña)) {
									ventana_codigos_alumnos_7_10 alumnos = new ventana_codigos_alumnos_7_10();
									alumnos.setLocationRelativeTo(null);
									alumnos.setVisible(true);
									if (rol.equals("1")) {
										alumnos.btnUsuarios.setEnabled(true);
									} else {
										if (rol.equals("2")) {
											alumnos.btnUsuarios.setEnabled(false);

										} else {
											if (rol.equals("3")) {
												alumnos.btnUsuarios.setEnabled(false);

											} else {
												alumnos.btnUsuarios.setEnabled(false);

											}

										}

									}
									dispose();

								} else {
									lblAlerta.setText("El usuario y contraseña son incorrectas");
									lblAlerta.setForeground(Color.RED);

								}

							} else {
								lblAlerta.setText("El usuario y contraseña son incorrectas");
								lblAlerta.setForeground(Color.RED);
							}
						}
					}
				}
			}

		});
		btnIngresar.setBounds(91, 230, 150, 23);
		panel.add(btnIngresar);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(91, 23, 150, 140);
		panel.add(lblNewLabel);
		final ImageIcon logo11 = new ImageIcon(getClass().getResource("/recursos/logo_ido.png"));
		final ImageIcon icono11 = new ImageIcon(logo11.getImage().getScaledInstance(lblNewLabel.getWidth(),
				lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icono11);

		lblSistemaDeGeneracion = new JLabel("SISTEMA DE GENERACION DE CREDENCIALES");
		lblSistemaDeGeneracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaDeGeneracion.setForeground(Color.WHITE);
		lblSistemaDeGeneracion.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblSistemaDeGeneracion.setBounds(10, 11, 301, 14);
		panel.add(lblSistemaDeGeneracion);

		lblParaLaMatricula = new JLabel("PARA LA MATRICULA IDO");
		lblParaLaMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaLaMatricula.setForeground(Color.WHITE);
		lblParaLaMatricula.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		lblParaLaMatricula.setBounds(10, 23, 301, 14);
		panel.add(lblParaLaMatricula);

		JLabel lblFoto = new JLabel("");
		lblFoto.setBounds(-15, 0, 455, 331);
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
			ResultSet rs = estatuto.executeQuery(
					"SELECT * FROM dbo.users WHERE RNE_Empleado='" + txtUsuario.getText().toString() + "'");
			while (rs.next()) {
				rol = rs.getString("Id_Rol");
				nombre = rs.getString("name");
				identidad = rs.getString("RNE_Empleado");
				contraseña = rs.getString("password");
			}
		} catch (SQLException ex) {
			Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
