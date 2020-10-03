package consultas;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import clases.alumnos;
import clases.usuarios;
import conexion.conexion;
import ventanas.ventana_login;
import ventanas.ventana_usuarios;

public class consultas_alumnos extends conexion {

	public boolean insertarUserYpass(alumnos usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO users (RNE_Alumno, password, Id_Rol) VALUES(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Alumno());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getId_Rol());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public boolean actualizarUserYpass(alumnos usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE users SET RNE_Alumno=?, password=?, Id_Rol=? WHERE id=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Alumno());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getId_Rol());
			ps.setInt(4, usuario.getId());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}
	
	public boolean insertarRNEyGrupo(alumnos usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO Prematriculas (RNE_Alumno, Id_Grupo) VALUES(?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Alumno());
			ps.setString(2, usuario.getId_Grupo());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public boolean actualizarRNEyGrupo(alumnos usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE Prematriculas SET RNE_Alumno=?, Id_Grupo=? WHERE Id_Prematricula=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Alumno());
			ps.setString(2, usuario.getId_Grupo());
			ps.setInt(3, usuario.getId_Prematricula());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}

}
