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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.HashData;
import clases.pines;
import clases.usuarios;
import conexion.conexion;
import consultas.consultas_usuario;
import ventanas.ventana_usuarios;

public class control_pines implements ActionListener {

	public pines clase;

	public control_pines(pines clase) {
		this.clase = clase;

	}

	public static ArrayList<pines> buscarUsuariosConMatriz() {
		conexion conex = new conexion();
		ArrayList<pines> miLista = new ArrayList<pines>();
		pines pines;
		try {
			Statement estatuto = conex.getConexion().createStatement();
			ResultSet rs = estatuto.executeQuery(
					"select * from Prematriculas, users, Roles, Modalidades, Grupos where Nombre_Rol= 'Alumno'");

			while (rs.next()) {
				pines = new pines();
				pines.setNombre(rs.getString("name"));
				pines.setGrado(rs.getString("Grado"));
				pines.setIdentidad(rs.getString("RNE_Alumno"));
				pines.setModalidad(rs.getString("Nombre_Modalidad"));
				pines.setUsuario(rs.getString("RNE_Alumno"));
				pines.setContraseña(rs.getString("password"));
				miLista.add(pines);
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
		ArrayList<pines> miLista = buscarUsuariosConMatriz();
		String matrizInfo[][] = new String[miLista.size()][6];
		for (int i = 0; i < miLista.size(); i++) {
			matrizInfo[i][0] = miLista.get(i).getNombre() + "";
			matrizInfo[i][1] = miLista.get(i).getGrado() + "";
			matrizInfo[i][2] = miLista.get(i).getIdentidad() + "";
			matrizInfo[i][3] = miLista.get(i).getModalidad() + "";
			matrizInfo[i][4] = miLista.get(i).getUsuario() + "";
			matrizInfo[i][5] = miLista.get(i).getContraseña() + "";
		}
		return matrizInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
