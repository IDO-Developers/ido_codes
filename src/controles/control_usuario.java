package controles;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.HashData;
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
	public static String contraseñaEncriptada;
	public static String contraseñaDesencriptada;

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
					contraseña = ventana.txtContraseña.getText().toString();
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					contraseñaEncriptada = passwordEncoder.encode(contraseña);
					clase.setPassword(contraseñaEncriptada);
					clase.setName(ventana.txtNombre.getText().toString());

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
		
		if (e.getSource() == ventana.btnActualizar_Usuario) {

			if (ventana.txtIdentidad.getText().isEmpty() || ventana.txtContraseña.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Porfavor llene los campos para actualizar el usuario!");

			} else {

				clase.setRNE_Empleado(ventana.txtIdentidad.getText().toString());
				contraseña = ventana.txtContraseña.getText().toString();

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				contraseñaEncriptada = passwordEncoder.encode(contraseña);
				clase.setPassword(contraseñaEncriptada);

				clase.setName(ventana.txtNombre.getText().toString());

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

		if (e.getSource() == ventana.btnActualizar) {
			int filaseleccionada;
			try {
				filaseleccionada = ventana.tabla.getSelectedRow();
				if (filaseleccionada == -1) {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
				} else {
					String codigo = ventana.tabla.getValueAt(filaseleccionada, 0).toString();
					String nombre = ventana.tabla.getValueAt(filaseleccionada, 1).toString();
					String identidad = ventana.tabla.getValueAt(filaseleccionada, 2).toString();
					String contraseña = ventana.tabla.getValueAt(filaseleccionada, 3).toString();
					String rol = ventana.tabla.getValueAt(filaseleccionada, 4).toString();
					

					ventana.lblID.setText(codigo);
					ventana.txtIdentidad.setText(identidad);
					ventana.txtContraseña.setText(contraseña);
					ventana.txtNombre.setText(nombre);

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
					ventana.txtNombre.setForeground(Color.BLACK);
					ventana.cbxRol.setForeground(Color.BLACK);

					ventana.btnBorrar.setVisible(true);
					ventana.btnGuardar.setVisible(false);
					ventana.btnActualizar.setVisible(true);
					ventana.btnActualizar_Usuario.setVisible(true);
					ventana.btnVer.setVisible(false);
					ventana.btnAceptar.setText("Cancelar");
					ventana.btnAceptar.setVisible(true);

					ventana.txtNombre.requestFocusInWindow();

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
					String nombre = ventana.tabla.getValueAt(filaseleccionada, 1).toString();
					String identidad = ventana.tabla.getValueAt(filaseleccionada, 2).toString();
					String contraseña = ventana.tabla.getValueAt(filaseleccionada, 3).toString();
					String rol = ventana.tabla.getValueAt(filaseleccionada, 4).toString();

					ventana.lblID.setText(codigo);
					ventana.txtIdentidad.setText(identidad);
					ventana.txtContraseña.setText(contraseña);
					ventana.txtNombre.setText(nombre);
					
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
					ventana.txtNombre.setForeground(Color.BLACK);

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
		ventana.txtNombre.setText(null);
	}
	

}
