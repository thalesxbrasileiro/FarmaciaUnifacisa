package br.com.farmacia.conectapg;

import java.sql.Connection;
import java.sql.SQLException;

public class Teste {

	public static void main(String[] args) {
		
		try {
			@SuppressWarnings("unused")
			Connection con = Conecta.criarConexao();
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
