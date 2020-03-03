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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

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

public class registro_usuarios extends JFrame {

	private JPanel contentPane;
	public JFormattedTextField txtIdentidad;
	public JPasswordField txtContrase�a;
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
	public registro_usuarios() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
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
		lblUsuario.setBounds(78, 102, 86, 20);
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
		txtIdentidad.setBounds(28, 122, 192, 20);
		panel.add(txtIdentidad);
		txtIdentidad.setColumns(10);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(78, 147, 86, 20);
		panel.add(lblContrasea);

		txtContrase�a = new JPasswordField();
		txtContrase�a.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtContrase�a.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrase�a.setColumns(10);
		txtContrase�a.setBounds(28, 167, 170, 20);
		panel.add(txtContrase�a);
		InputMap map1 = txtContrase�a.getInputMap(JComponent.WHEN_FOCUSED);
		map1.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
		txtContrase�a.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ke) {

				if (txtContrase�a.getText().length() == 20)
					ke.consume();

				if (txtContrase�a.getText().toString().equals(" ")) {
					JOptionPane.showMessageDialog(null, "No esta permitido escribir espacios vacios!");
					txtContrase�a.setText("");
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
		lblRol.setBounds(78, 191, 86, 20);
		panel.add(lblRol);

		cbxRol = new JComboBox();
		cbxRol.setBounds(28, 211, 192, 20);
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
		btnActualizar_Usuario.setBounds(10, 305, 107, 23);
		panel.add(btnActualizar_Usuario);

		JLabel USUARIOS = new JLabel(" REGISTRO DE USUARIOS");
		USUARIOS.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		USUARIOS.setHorizontalAlignment(SwingConstants.CENTER);
		USUARIOS.setBounds(28, 26, 192, 31);
		panel.add(USUARIOS);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
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
		btnAceptar.setBounds(69, 271, 107, 23);
		panel.add(btnAceptar);
		
		btnMostrar_Ocultar_Pass = new JToggleButton("");
		btnMostrar_Ocultar_Pass.setForeground(Color.BLACK);
		btnMostrar_Ocultar_Pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnMostrar_Ocultar_Pass.isSelected()) {
					btnMostrar_Ocultar_Pass.setText("Ocultar");
					txtContrase�a.setEchoChar((char) 0);
					final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(),
							btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
					btnMostrar_Ocultar_Pass.setIcon(iconover);
				}else {
					btnMostrar_Ocultar_Pass.setText("Mostrar");
					txtContrase�a.setEchoChar('*');
					final ImageIcon iconoocultar = new ImageIcon(ocultar.getImage()
							.getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(), btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
					btnMostrar_Ocultar_Pass.setIcon(iconoocultar);
				}
			}
		});
		btnMostrar_Ocultar_Pass.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMostrar_Ocultar_Pass.setBounds(197, 167, 23, 20);
		panel.add(btnMostrar_Ocultar_Pass);
		final ImageIcon iconover = new ImageIcon(ver.getImage().getScaledInstance(btnMostrar_Ocultar_Pass.getWidth(),
				btnMostrar_Ocultar_Pass.getHeight(), Image.SCALE_DEFAULT));
		btnMostrar_Ocultar_Pass.setIcon(iconover);

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
			registro_usuarios.cbxRol.setModel(modelo);
		} catch (SQLException ex) {
			Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
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
			Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
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
		String titulos[] = { "N�", "Usuario", "Contrase�a", "Rol" };
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
		String matrizInfo[][] = new String[miLista.size()][4];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getId() + "";
			matrizInfo[i][1] = miLista.get(i).getRNE_Empleado() + "";
			matrizInfo[i][2] = miLista.get(i).getPassword() + "";
			matrizInfo[i][3] = miLista.get(i).getId_Rol() + "";
		}

		return matrizInfo;
	}
}
