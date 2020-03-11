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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(51, 49, 321, 166);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("USUARIO: ");
		lblUsuario.setBounds(31, 43, 84, 14);
		panel.add(lblUsuario);

		MaskFormatter identidadF = null;
		try {
			identidadF = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtUsuario = new JFormattedTextField(identidadF);
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setBounds(125, 40, 150, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setBounds(31, 72, 84, 14);
		panel.add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(125, 69, 150, 20);
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
										ventana_alumnos alumnos = new ventana_alumnos();
										alumnos.setLocationRelativeTo(null);
										alumnos.setVisible(true);
										if (consultas_usuario.rol.equals("1")) {
											alumnos.btnMenu.setEnabled(true);
										} else {
											if (consultas_usuario.rol.equals("2")) {
												alumnos.btnMenu.setEnabled(false);

											} else {
												if (consultas_usuario.rol.equals("3")) {
													alumnos.btnMenu.setEnabled(false);

												} else {
													alumnos.btnMenu.setEnabled(false);

												}

											}

										}

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
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
									ventana_alumnos alumnos = new ventana_alumnos();
									alumnos.setLocationRelativeTo(null);
									alumnos.setVisible(true);
									if (rol.equals("1")) {
										alumnos.btnMenu.setEnabled(true);
									} else {
										if (rol.equals("2")) {
											alumnos.btnMenu.setEnabled(false);

										} else {
											if (rol.equals("3")) {
												alumnos.btnMenu.setEnabled(false);

											} else {
												alumnos.btnMenu.setEnabled(false);

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
		btnIngresar.setBounds(125, 100, 150, 23);
		panel.add(btnIngresar);

		lblAlerta = new JLabel("");
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblAlerta.setBackground(Color.RED);
		lblAlerta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlerta.setBounds(10, 141, 301, 14);
		panel.add(lblAlerta);

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
