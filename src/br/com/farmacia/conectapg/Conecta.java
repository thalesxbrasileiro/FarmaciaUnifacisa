package br.com.farmacia.conectapg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conecta {

	static final String URL = "jdbc:postgresql://localhost:5432/Farmacia";
	static final String USER = "postgres";
	static final String PASSWORD = "12345";

	public static Connection criarConexao() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver"); 
		
		Connection conecta = DriverManager.getConnection(URL, USER, PASSWORD); 

		if (conecta != null) { 
			//System.out.print ("Conexão efetuada com sucesso... ");
			return conecta; 
	    }
		
		return null; 
	}
}
