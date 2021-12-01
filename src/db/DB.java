package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	
	//METODO PARA CONECTAR COM O BANCO DE DADOS
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			if (conn == null) {
				Properties props = loadProperties(); //<- Método para logar no BD.
				String url = props.getProperty("dburl"); //<- Busca a url do BD.
				conn = DriverManager.getConnection(url, props); //<- está localizando o BD através da url e colocando as informações de login para acessar o BD.
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return conn;
	}
	
	//METODO PARA ENCERRAR A CONEXÃO COM O BD
	public static void closeConection() {
		if (conn !=null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement (Statement st) {
		if (st != null) {
			try {
				st.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	public static void closeResultSet (ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
		
		
	//METODO PARA CARREGAR O ARQUIVO d.properties COM AS INFORMAÇOES DE LOGIN DO BD.
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
}
