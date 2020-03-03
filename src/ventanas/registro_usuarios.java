package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import clases.usuarios;
import consultas.consultas_usuario;
import controles.control_usuario;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Window.Type;

public class registro_usuarios extends JFrame {

	private JPanel contentPane;
	public JTextField txtIdentidad;
	public JTextField txtContraseña;
	public JTextField txtBuscar;
	public JLabel lblID;
	public JButton btnGuardar;
	public JButton btnActualizar;
	public JButton btnActualizar_Usuario;
	public JButton btnBorrar;
	public JButton btnVer;
	public JButton btnAceptar;
	public static JComboBox<?> cbxRol;
	public JScrollPane barra;
	public JTable tabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarios clase = new usuarios();
					consultas_usuario consulta = new consultas_usuario();
					registro_usuarios formulario = new registro_usuarios();
					control_usuario control = new control_usuario(clase, consulta, formulario);
					formulario.setVisible(true);
					formulario.setLocationRelativeTo(null);
					formulario.txtIdentidad.requestFocusInWindow();
					control.construirTabla();
					control.obtenerUltimoId();
					formulario.btnBorrar.setVisible(false);
					formulario.btnGuardar.setVisible(true);
					formulario.btnActualizar.setVisible(true);
					formulario.btnActualizar_Usuario.setVisible(false);
					formulario.btnVer.setVisible(true);
					formulario.btnAceptar.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registro_usuarios() {
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 252, 339);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Identidad:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(78, 102, 86, 20);
		panel.add(lblUsuario);

		txtIdentidad = new JTextField();
		txtIdentidad.setBounds(69, 122, 107, 20);
		panel.add(txtIdentidad);
		txtIdentidad.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(78, 147, 86, 20);
		panel.add(lblContrasea);

		txtContraseña = new JTextField();
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(69, 167, 107, 20);
		panel.add(txtContraseña);

		JLabel lblRol = new JLabel("Rol:");
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setBounds(78, 192, 86, 20);
		panel.add(lblRol);

		cbxRol = new JComboBox();
		cbxRol.setBounds(69, 212, 107, 20);
		panel.add(cbxRol);

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnGuardar.setBounds(135, 305, 107, 23);
		panel.add(btnGuardar);

		btnActualizar_Usuario = new JButton("ACTUALIZAR");
		btnActualizar_Usuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnActualizar_Usuario.setBounds(10, 305, 107, 23);
		panel.add(btnActualizar_Usuario);

		JLabel USUARIOS = new JLabel(" REGISTRO DE USUARIOS");
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(28, 26, 192, 31);
		panel.add(USUARIOS);

		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(78, 51, 86, 20);
		panel.add(lblId);

		lblID = new JLabel("");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(69, 68, 107, 23);
		panel.add(lblID);

		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnAceptar.setBounds(69, 243, 107, 23);
		panel.add(btnAceptar);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(272, 11, 302, 339);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 38, 282, 256);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(68, 14, 224, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblBuscar.setBounds(10, 14, 102, 20);
		panel_1.add(lblBuscar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnActualizar.setBounds(176, 305, 116, 23);
		panel_1.add(btnActualizar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnBorrar.setBounds(10, 305, 91, 23);
		panel_1.add(btnBorrar);

		btnVer = new JButton("VER");
		btnVer.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnVer.setBounds(111, 305, 59, 23);
		panel_1.add(btnVer);
		
		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_2.add(barra);
		barra.setBounds(0, 0, 282, 257);

		tabla = new JTable();
		barra.setViewportView(tabla);
	}
}
