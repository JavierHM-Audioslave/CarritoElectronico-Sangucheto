package tallerweb.sangucheto.modelo;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class BaseDeDatos {
	
	public static Connection conexion;
	
	public void conectar()
	{
		String usuario = "Javo";
		String pass = "1234";
		String nombreDeBD = "BDSangucheto";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nombreDeBD+"?autoReconnect=true&useSSL=false", usuario, pass);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public boolean loguear(String dni, String password)
	{
		String query = "SELECT pass FROM USUARIOS WHERE dni=?";
		try
		{
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getString(1).equals(password))
				{
					return true;
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public boolean comprobarExistencia(String dni)
	{
		String query = "SELECT dni FROM USUARIOS";
		try
		{
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if(rs.getString(1).equals(dni))
				{
					rs.close();
					ps.close();
					return true;
				}
			}
			
			rs.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public boolean comprobarExistenciaYPass(String dni, String passVieja)
	{
		String query = "SELECT pass FROM USUARIOS WHERE dni=?";
		try
		{
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(passVieja.equals(rs.getString(1)))
			{
				rs.close();
				ps.close();
				return true;
			}
			
			rs.close();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return false;
	}
	
	public void darAlta(String dni, String pass)
	{
		String query = "INSERT INTO USUARIOS VALUES(?,?)";
		try
		{
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, dni);
			ps.setString(2, pass);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void actualizarPassword(String dni, String pass)
	{
		String query = "UPDATE USUARIOS SET pass=? WHERE dni=?";
		try
		{
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, pass);
			ps.setString(2, dni);
			ps.executeUpdate();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
