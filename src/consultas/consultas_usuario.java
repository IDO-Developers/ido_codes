package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.usuarios;
import conexion.conexion;

public class consultas_usuario extends conexion {
	public static String rol = null;
	public static String nombre = null;
	
	public boolean insertar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO dbo.users (RNE_Empleado, password, Id_Rol, name) VALUES(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Empleado());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getId_Rol());
			ps.setString(4, usuario.getName());

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

	public boolean actualizar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.users SET RNE_Empleado=?, password=?, Id_Rol=?, name=? WHERE id=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Empleado());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getId_Rol());
			ps.setString(4, usuario.getName());
			ps.setInt(5, usuario.getId());
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

	public boolean buscarUsuario(usuarios usuario) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT * FROM dbo.users WHERE RNE_Empleado=? and password=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Empleado());
			ps.setString(2, usuario.getPassword());
			rs = ps.executeQuery();

			if (rs.next()) {
				usuario.setRNE_Empleado(rs.getString("RNE_Empleado"));
				usuario.setPassword(rs.getString("password"));
				usuario.setId_Rol(rs.getString("Id_Rol"));
				usuario.setName(rs.getString("name"));
				rol = rs.getString("Id_Rol");
				nombre = rs.getString("name");
				return true;
			}
			return false;
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
