package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ventana_login extends JFrame {

	private JPanel contentPane;
	public JTextField txtUsuario;
	public JTextField txtContraseña;
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

		txtUsuario = new JTextField();
		txtUsuario.setBounds(125, 40, 150, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblContrasea = new JLabel("CONTRASE\u00D1A");
		lblContrasea.setBounds(31, 72, 84, 14);
		panel.add(lblContrasea);

		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(125, 69, 150, 20);
		panel.add(txtContraseña);

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

								

								if (user.equals(identidad) &&  BCrypt.checkpw(pass, contraseña)) {
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
