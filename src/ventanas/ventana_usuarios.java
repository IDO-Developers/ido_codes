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

import clases.usuarios;
import conexion.conexion;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class ventana_usuarios extends JFrame {

	private JPanel contentPane;
	public JFormattedTextField txtIdentidad;
	public JPasswordField txtContraseña;
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
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public static DefaultComboBoxModel modelo;
	public static String id_rol;
	public static String identidadRepetida;
	public JToggleButton btnMostrar_Ocultar_Pass;
	public JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventana_usuarios() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/recursos/logo_ido.png")));
		final ImageIcon ver = new ImageIcon(getClass().getResource("/recursos/ver.png"));
		final ImageIcon ocultar = new ImageIcon(getClass().getResource("/recursos/ocultar.png"));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 11, 252, 339);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsuario = new JLabel("Identidad:");
		lblUsuario.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setBounds(79, 130, 86, 20);
		panel.add(lblUsuario);

		MaskFormatter identidad = null;
		try {
			identidad = new MaskFormatter("#############");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txtIdentidad = new JFormattedTextField(identidad);
		txtIdentidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtIdentidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdentidad.setBounds(29, 150, 192, 20);
		panel.add(txtIdentidad);
		txtIdentidad.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(79, 175, 86, 20);
		panel.add(lblContrasea);

		txtContraseña = new JPasswordField();
		txtContraseña.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(29, 195, 170, 20);
		panel.add(txtContraseña);
		InputMap map1 = txtContraseña.getInputMap(JComponent.WHEN_FOCUSED);
		map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContraseña.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {

				if (txtContraseña.getText().length() == 20)
					ke.consume();

				if (txtContraseña.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContraseña.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {
			}

			@Override
			public void keyReleased(KeyEvent ke) {
			}
		});

		JLabel lblRol = new JLabel("Rol:");
		lblRol.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblRol.setHorizontalAlignment(SwingConstants.CENTER);
		lblRol.setBounds(79, 219, 86, 20);
		panel.add(lblRol);

		cbxRol = new JComboBox();
		cbxRol.setBounds(29, 239, 192, 20);
		panel.add(cbxRol);
		cbxRol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarIdRol();
			}
		});

		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnGuardar.setBounds(135, 305, 107, 23);
		panel.add(btnGuardar);

		btnActualizar_Usuario = new JButton("ACTUALIZAR");
		btnActualizar_Usuario.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnActualizar_Usuario.setBounds(135, 305, 107, 23);
		panel.add(btnActualizar_Usuario);

		JLabel USUARIOS = new JLabel(" REGISTRO DE USUARIOS");
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(29, 22, 192, 33);
		panel.add(USUARIOS);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(55, 50, 52, 23);
		panel.add(lblId);

		lblID = new JLabel("");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(100, 50, 78, 23);
		panel.add(lblID);

		btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnAceptar.setBounds(12, 305, 107, 23);
		panel.add(btnAceptar);

		btnMostrar_Ocultar_Pass = new JToggleButton("");
		btnMostrar_Ocultar_Pass.setForeground(Color.BLACK);
		btnMostrar_Ocultar_Pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnMostrar_Ocultar_Pass.isSelected()) {
					btnMostrar_Ocultar_Pass.setText("Ocultar");
					txtContraseña.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(
							ver.getImage().getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(),
									btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
					btnMostrar_Ocultar_Pass.setIcon(iconover);
				} else {
					btnMostrar_Ocultar_Pass.setText("Mostrar");
					txtContraseña.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(
							ocultar.getImage().getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(),
									btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
					btnMostrar_Ocultar_Pass.setIcon(iconoocultar);
				}
			}
		});
		btnMostrar_Ocultar_Pass.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar_Ocultar_Pass.setBounds(198, 195, 23, 20);
		panel.add(btnMostrar_Ocultar_Pass);
		final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(),
				btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
		btnMostrar_Ocultar_Pass.setIcon(iconover);

		JLabel lblNombreCompleto = new JLabel("Nombre Completo:");
		lblNombreCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCompleto.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblNombreCompleto.setBounds(29, 78, 192, 20);
		panel.add(lblNombreCompleto);

		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombre.setColumns(10);
		txtNombre.setBounds(29, 98, 192, 20);
		panel.add(txtNombre);
		
		JButton btnEstudiante = new JButton("ACEPTAR");
		btnEstudiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana_alumnos alumnos = new ventana_alumnos();
				alumnos.setLocationRelativeTo(null);
				alumnos.setVisible(true);
				if (ventana_login.rol.equals("1")) {
					alumnos.btnMenu.setEnabled(true);
				} else {
					if (ventana_login.rol.equals("2")) {
						alumnos.btnMenu.setEnabled(false);

					} else {
						if (ventana_login.rol.equals("3")) {
							alumnos.btnMenu.setEnabled(false);

						} else {
							alumnos.btnMenu.setEnabled(false);

						}

					}

				}
				dispose();
			}
		});
		btnEstudiante.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnEstudiante.setBounds(0, 0, 31, 33);
		panel.add(btnEstudiante);
		final ImageIcon logom = new ImageIcon(getClass().getResource("/recursos/estudiante.png"));
		final ImageIcon iconom = new ImageIcon(
				logom.getImage().getScaledInstance(btnEstudiante.getWidth(), btnEstudiante.getHeight(), Image.SCALE_DEFAULT));
		btnEstudiante.setIcon(iconom);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(272, 11, 410, 339);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(10, 38, 388, 262);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(68, 14, 330, 20);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		InputMap map = txtBuscar.getInputMap(JComponent.WHEN_FOCUSED);
		map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtBuscar.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {
				trsfiltroCodigo = new TableRowSorter(tabla.getModel());
				tabla.setRowSorter(trsfiltroCodigo);

				if (txtBuscar.getText().length() == 20)
					ke.consume();

				if (txtBuscar.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtBuscar.setText("");
				}
			}

			@Override
			public void keyPressed(KeyEvent ke) {

			}

			@Override
			public void keyReleased(KeyEvent ke) {
				String cadena = (txtBuscar.getText().toString());
				txtBuscar.setText(cadena);
				repaint();
				filtro();
			}
		});

		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		lblBuscar.setBounds(10, 14, 102, 20);
		panel_1.add(lblBuscar);

		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnActualizar.setBounds(282, 306, 116, 23);
		panel_1.add(btnActualizar);

		btnBorrar = new JButton("BORRAR");
		btnBorrar.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnBorrar.setBounds(10, 305, 91, 23);
		panel_1.add(btnBorrar);

		btnVer = new JButton("VER");
		btnVer.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		btnVer.setBounds(211, 306, 59, 23);
		panel_1.add(btnVer);

		barra = new JScrollPane(tabla, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_2.add(barra);
		barra.setBounds(0, 0, 388, 262);

		tabla = new JTable();
		barra.setViewportView(tabla);
	}

	public void filtro() {
		filtroCodigo = txtBuscar.getText().toString();
		trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText().toString(), 0, 1, 2, 3));
	}

	public void validarUsuarioPorIdentidad() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT RNE_Empleado FROM dbo.users where RNE_Empleado = '"
					+ txtIdentidad.getText().toString() + "'");

			if (rs.next()) {
				identidadRepetida = (rs.getString("RNE_Empleado"));
			}

			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException exx) {
			System.out.println(exx.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	@SuppressWarnings("unchecked")
	public static void llena_combobox_con_roles() {
		conexion conex = new conexion();
		try {
			modelo = new DefaultComboBoxModel();
			modelo.removeAllElements();
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("Select * from Roles");

			while (rs.next()) {
				modelo.addElement(rs.getString("Nombre_Rol"));
			}
			ventana_usuarios.cbxRol.setModel(modelo);
		} catch (SQLException ex) {
			Logger.getLogger(ventana_usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void cargarIdRol() {
		conexion conex = new conexion();
		try {

			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"Select id_Rol from Roles where Nombre_Rol='" + cbxRol.getSelectedItem().toString() + "'");
			while (rs.next()) {
				id_rol = rs.getString("Id_Rol");
			}
		} catch (SQLException ex) {
			Logger.getLogger(ventana_usuarios.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void obtenerUltimoId() {
		String ultimoValor = null;
		int valor;
		String id = null;
		conexion objCon = new conexion();
		Connection conn = objCon.getConexion();
		try {
			PreparedStatement stmtr = conn.prepareStatement("SELECT * FROM dbo.users ORDER BY id DESC");
			ResultSet rsr = stmtr.executeQuery();
			if (rsr.next()) {
				ultimoValor = rsr.getString("id");
				valor = Integer.parseInt(ultimoValor);
				valor = valor + 1;
				id = String.valueOf(valor);
			}
			lblID.setText(id);
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void construirTabla() {
		String titulos[] = { "N°", "Usuario", "Identidad", "Contraseña", "Rol" };
		String informacion[][] = obtenerMatriz();
		tabla = new JTable(informacion, titulos);
		barra.setViewportView(tabla);
		for (int c = 0; c < tabla.getColumnCount(); c++) {
			Class<?> col_class = tabla.getColumnClass(c);
			tabla.setDefaultEditor(col_class, null);
			tabla.getTableHeader().setReorderingAllowed(false);

		}
	}

	public static ArrayList<usuarios> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<usuarios> miLista = new ArrayList<usuarios>();
		usuarios usuarios;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT * FROM users ");

			while (rs.next()) {
				usuarios = new usuarios();
				usuarios.setId(Integer.parseInt(rs.getString("id")));
				usuarios.setName(rs.getString("name"));
				usuarios.setRNE_Empleado(rs.getString("RNE_Empleado"));
				usuarios.setPassword(rs.getString("password"));
				usuarios.setId_Rol(rs.getString("Id_Rol"));
				
				miLista.add(usuarios);
			}
			rs.close();
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);

		}
		return miLista;
	}

	public static String[][] obtenerMatriz() {
		ArrayList<usuarios> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][5];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getName() + "";
			matrizInfo[i][2] = miLista.get(i).getRNE_Empleado() + "";
			matrizInfo[i][3] = miLista.get(i).getPassword() + "";
			matrizInfo[i][4] = miLista.get(i).getId_Rol() + "";
		
		}

		return matrizInfo;
	}
}
