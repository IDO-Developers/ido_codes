package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clases.usuarios;
import conexion.conexion;

public class consultas_usuario extends conexion {
	public static String rol = null;
	
	public boolean insertar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql = "INSERT INTO dbo.users (RNE_Empleado, password, Id_Rol) VALUES(?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Empleado());
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

	public boolean actualizar(usuarios usuario) {
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "UPDATE dbo.users SET RNE_Empleado=?, password=?, Id_Rol=? WHERE id=? ";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getRNE_Empleado());
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
				rol = rs.getString("Id_Rol");
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
