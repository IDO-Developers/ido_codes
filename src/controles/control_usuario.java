package controles;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;
import ventanas.registro_usuarios;

public class control_usuario implements ActionListener {

	public usuarios clase;
	public consultas_usuario consulta;
	public registro_usuarios ventana;
	public static DefaultComboBoxModel modelo;
	public static String id_rol;
	public static String identidadRepetida;

	public control_usuario(usuarios clase, consultas_usuario consulta, registro_usuarios ventana) {
		this.clase = clase;
		this.consulta = consulta;
		this.ventana = ventana;
		this.ventana.btnActualizar.addActionListener(this);
		this.ventana.btnActualizar_Usuario.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnVer.addActionListener(this);
		this.ventana.btnBorrar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Insertar */
		if (e.getSource() == ventana.btnGuardar) {

			if (ventana.txtIdentidad.getText().isEmpty() || ventana.txtContraseña.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Porfavor llene los campos para guardar el usuario!");

			} else {

				clase.setRNE_Empleado(ventana.txtIdentidad.getText().toString());
				clase.setPassword(ventana.txtContraseña.getText().toString());
				clase.setId_Rol(ventana.cbxRol.getSelectedItem().toString());

				if (consulta.insertar(clase)) {
					JOptionPane.showMessageDialog(null, "Usuario Registrado!");
					limpiar();
					construirTabla();
					obtenerUltimoId();
				} else {
					JOptionPane.showMessageDialog(null, "Error! Usuario no registrado");
					limpiar();
				}
			}
		}

		if (e.getSource() == ventana.btnActualizar) {
			int filaseleccionada;
			try {
				filaseleccionada = ventana.tabla.getSelectedRow();
				if (filaseleccionada == -1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					String codigo = ventana.tabla.getValueAt(filaseleccionada, 0).toString();
					String identidad = ventana.tabla.getValueAt(filaseleccionada, 1).toString();
					String contraseña = ventana.tabla.getValueAt(filaseleccionada, 2).toString();
					String rol = ventana.tabla.getValueAt(filaseleccionada, 3).toString();

					ventana.lblID.setText(codigo);
					ventana.txtIdentidad.setText(identidad);
					ventana.txtContraseña.setText(contraseña);
					ventana.cbxRol.setSelectedItem(rol);

					ventana.lblID.setForeground(Color.BLACK);
					ventana.txtIdentidad.setForeground(Color.BLACK);
					ventana.txtContraseña.setForeground(Color.BLACK);
					ventana.cbxRol.setForeground(Color.BLACK);

					ventana.btnBorrar.setVisible(true);
					ventana.btnGuardar.setVisible(false);
					ventana.btnActualizar.setVisible(true);
					ventana.btnActualizar_Usuario.setVisible(true);
					ventana.btnVer.setVisible(false);
					ventana.btnAceptar.setText("Cancelar");
					ventana.btnAceptar.setVisible(true);

					ventana.txtIdentidad.requestFocusInWindow();

				}

			} catch (HeadlessException ex) {
				JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
						" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
			}
		}

		if (e.getSource() == ventana.btnVer) {
			int filaseleccionada;
			try {
				filaseleccionada = ventana.tabla.getSelectedRow();
				if (filaseleccionada == -1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					String codigo = ventana.tabla.getValueAt(filaseleccionada, 0).toString();
					String identidad = ventana.tabla.getValueAt(filaseleccionada, 1).toString();
					String contraseña = ventana.tabla.getValueAt(filaseleccionada, 2).toString();
					String rol = ventana.tabla.getValueAt(filaseleccionada, 3).toString();

					ventana.lblID.setText(codigo);
					ventana.txtIdentidad.setText(identidad);
					ventana.txtContraseña.setText(contraseña);
					ventana.cbxRol.setSelectedItem(rol);

					ventana.lblID.setForeground(Color.BLACK);
					ventana.txtIdentidad.setForeground(Color.BLACK);
					ventana.txtContraseña.setForeground(Color.BLACK);
					ventana.cbxRol.setForeground(Color.BLACK);

					ventana.btnBorrar.setVisible(false);
					ventana.btnGuardar.setVisible(false);
					ventana.btnActualizar.setVisible(false);
					ventana.btnActualizar_Usuario.setVisible(false);
					ventana.btnAceptar.setText("Aceptar");
					ventana.btnAceptar.setVisible(true);
					ventana.txtIdentidad.setEditable(false);
					ventana.txtContraseña.setEditable(false);
					ventana.btnActualizar_Usuario.setVisible(false);

				}

			} catch (HeadlessException ex) {
				JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente",
						" .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
			}
		}

		/* Actualizar */
		if (e.getSource() == ventana.btnActualizar_Usuario) {

			if (ventana.txtIdentidad.getText().isEmpty() || ventana.txtContraseña.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el usuario!");

			} else {

				clase.setRNE_Empleado(ventana.txtIdentidad.getText().toString());
				clase.setPassword(ventana.txtContraseña.getText().toString());
				clase.setId_Rol(ventana.cbxRol.getSelectedItem().toString());
				clase.setId(Integer.parseInt(ventana.lblID.getText().toString()));

				if (consulta.insertar(clase)) {
					JOptionPane.showMessageDialog(null, "Usuario Actualizado!");
					limpiar();
					construirTabla();
					obtenerUltimoId();
				} else {
					JOptionPane.showMessageDialog(null, "Error! Usuario no actualizado");
					limpiar();
				}

			}
		}

		/* Borrar */
		if (e.getSource() == ventana.btnBorrar) {
			PreparedStatement ps = null;
			int filaseleccionada;
			try {
				filaseleccionada = ventana.tabla.getSelectedRow();
				if (filaseleccionada == -1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					conexion objCon = new conexion();
					Connection conn = objCon.getConexion();
					int Fila = ventana.tabla.getSelectedRow();
					String codigo = ventana.tabla.getValueAt(Fila, 0).toString();
					ps = conn.prepareStatement("DELETE FROM users WHERE id=?");
					ps.setString(1, codigo);
					ps.execute();
					JOptionPane.showMessageDialog(null, "Usuario Eliminado");
					limpiar();
					construirTabla();
					
					ventana.txtIdentidad.setEditable(false);
					ventana.txtContraseña.setEditable(false);
					ventana.btnActualizar_Usuario.setVisible(false);

				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al Eliminar usuario");
				System.out.println(ex.toString());
			}
		}


		/* Aceptar */
		if (e.getSource() == ventana.btnAceptar) {
			limpiar();
			obtenerUltimoId();
			ventana.btnBorrar.setVisible(false);
			ventana.btnGuardar.setVisible(true);
			ventana.btnActualizar.setVisible(true);
			ventana.txtIdentidad.setEditable(true);
			ventana.txtContraseña.setEditable(true);
			ventana.btnActualizar_Usuario.setVisible(false);
			ventana.btnVer.setVisible(true);
			ventana.btnAceptar.setVisible(false);
			construirTabla();
		}

	}

	public void limpiar() {
		ventana.cbxRol.setSelectedItem(0);
		ventana.lblID.setText(null);
		ventana.txtIdentidad.setText(null);
		ventana.txtContraseña.setText(null);
	}

	public void validarUsuarioPorIdentidad() {
		conexion conex = new conexion();
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery("SELECT RNE_Empleado FROM dbo.users where RNE_Empleado = '"
					+ ventana.txtIdentidad.getText().toString() + "'");

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
					"Select id_Rol from Roles where Nombre_Rol='" + ventana.cbxRol.getSelectedItem().toString() + "'");
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
			ventana.lblID.setText(id);
			;
			stmtr.close();
			rsr.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void construirTabla() {
		String titulos[] = { "N°", "Usuario", "Contraseña", "Rol" };
		String informacion[][] = obtenerMatriz();
		ventana.tabla = new JTable(informacion, titulos);
		ventana.barra.setViewportView(ventana.tabla);
		for (int c = 0; c < ventana.tabla.getColumnCount(); c++) {
			Class<?> col_class = ventana.tabla.getColumnClass(c);
			ventana.tabla.setDefaultEditor(col_class, null);
			ventana.tabla.getTableHeader().setReorderingAllowed(false);

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
