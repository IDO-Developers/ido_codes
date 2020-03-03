package controles;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;
import ventanas.registro_usuarios;

public class control_usuario implements ActionListener {

	public usuarios clase;
	public consultas_usuario consulta;
	public registro_usuarios ventana;
	public static String nombreRol;
	public static String contraseña;

	public control_usuario(usuarios clase, consultas_usuario consulta, registro_usuarios ventana) {
		this.clase = clase;
		this.consulta = consulta;
		this.ventana = ventana;
		this.ventana.btnActualizar.addActionListener(this);
		this.ventana.btnActualizar_Usuario.addActionListener(this);
		this.ventana.btnGuardar.addActionListener(this);
		this.ventana.btnVer.addActionListener(this);
		this.ventana.btnBorrar.addActionListener(this);
		this.ventana.btnAceptar.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ventana.btnGuardar) {
			ventana.validarUsuarioPorIdentidad();
			if (ventana.txtIdentidad.getText().isEmpty() || ventana.txtContraseña.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Porfavor llene los campos para guardar el usuario!");

			} else {
				if (ventana.txtIdentidad.getText().toString().equals(ventana.identidadRepetida)) {
					JOptionPane.showMessageDialog(null,
							"Se encontrado un registro con esta identidad : " + ventana.identidadRepetida,
							"Alerta!\n" + " Nota: Solo debe de haber 1 usuario por identidad",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					clase.setRNE_Empleado(ventana.txtIdentidad.getText().toString());

					clase.setPassword(ventana.txtContraseña.getText().toString());

					if (ventana.cbxRol.getSelectedItem().toString().equals("Administrador")) {
						clase.setId_Rol("1");
					} else {
						if (ventana.cbxRol.getSelectedItem().toString().equals("Docente")) {
							clase.setId_Rol("2");
						} else {
							if (ventana.cbxRol.getSelectedItem().toString().equals("Alumno")) {
								clase.setId_Rol("3");
							} else {
								if (ventana.cbxRol.getSelectedItem().toString().equals("Secretaria")) {
									clase.setId_Rol("4");
								} else {

								}

							}

						}

					}

					if (consulta.insertar(clase)) {
						JOptionPane.showMessageDialog(null, "Usuario Registrado!");
						limpiar();
						ventana.construirTabla();
						ventana.obtenerUltimoId();
					} else {
						JOptionPane.showMessageDialog(null, "Error! Usuario no registrado");
						limpiar();
					}
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

					conexion conex = new conexion();
					try {

						Statement estatuto = conex.getConexion().createStatement();
						ResultSet rs = estatuto.executeQuery("Select Nombre_Rol from Roles where Id_Rol='" + rol + "'");
						while (rs.next()) {
							nombreRol = rs.getString("Nombre_Rol");
						}
					} catch (SQLException ex) {
						Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
						JOptionPane.showMessageDialog(null, ex);
					}

					ventana.cbxRol.setSelectedItem(nombreRol);

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
					conexion conex = new conexion();
					try {

						Statement estatuto = conex.getConexion().createStatement();
						ResultSet rs = estatuto.executeQuery("Select Nombre_Rol from Roles where Id_Rol='" + rol + "'");
						while (rs.next()) {
							nombreRol = rs.getString("Nombre_Rol");
						}
					} catch (SQLException ex) {
						Logger.getLogger(registro_usuarios.class.getName()).log(Level.SEVERE, null, ex);
						JOptionPane.showMessageDialog(null, ex);
					}

					ventana.cbxRol.setSelectedItem(nombreRol);

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

		if (e.getSource() == ventana.btnActualizar_Usuario) {

			if (ventana.txtIdentidad.getText().isEmpty() || ventana.txtContraseña.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el usuario!");

			} else {

				clase.setRNE_Empleado(ventana.txtIdentidad.getText().toString());
				clase.setPassword(ventana.txtContraseña.getText().toString());
				if (ventana.cbxRol.getSelectedItem().toString().equals("Administrador")) {
					clase.setId_Rol("1");
				} else {
					if (ventana.cbxRol.getSelectedItem().toString().equals("Docente")) {
						clase.setId_Rol("2");
					} else {
						if (ventana.cbxRol.getSelectedItem().toString().equals("Alumno")) {
							clase.setId_Rol("3");
						} else {
							if (ventana.cbxRol.getSelectedItem().toString().equals("Secretaria")) {
								clase.setId_Rol("4");
							} else {

							}

						}

					}

				}
				clase.setId(Integer.parseInt(ventana.lblID.getText().toString()));

				if (consulta.actualizar(clase)) {
					JOptionPane.showMessageDialog(null, "Usuario Actualizado!");
					limpiar();
					ventana.construirTabla();
					ventana.obtenerUltimoId();
				} else {
					JOptionPane.showMessageDialog(null, "Error! Usuario no actualizado");
					limpiar();
				}

			}
		}

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
					ventana.construirTabla();

					ventana.txtIdentidad.setEditable(false);
					ventana.txtContraseña.setEditable(false);
					ventana.btnActualizar_Usuario.setVisible(false);

				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al Eliminar usuario");
				System.out.println(ex.toString());
			}
		}

		if (e.getSource() == ventana.btnAceptar) {
			limpiar();
			ventana.obtenerUltimoId();
			ventana.btnBorrar.setVisible(false);
			ventana.btnGuardar.setVisible(true);
			ventana.btnActualizar.setVisible(true);
			ventana.txtIdentidad.setEditable(true);
			ventana.txtContraseña.setEditable(true);
			ventana.btnActualizar_Usuario.setVisible(false);
			ventana.btnVer.setVisible(true);
			ventana.btnAceptar.setVisible(false);
			ventana.construirTabla();
		}

	}

	public void limpiar() {
		ventana.cbxRol.setSelectedItem(0);
		ventana.lblID.setText(null);
		ventana.txtIdentidad.setText(null);
		ventana.txtContraseña.setText(null);
	}

	public static String Encriptar(String texto) {

		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(contraseña.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

}
