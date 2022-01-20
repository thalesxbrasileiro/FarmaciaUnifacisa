package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.conectapg.Conecta;
import br.com.farmacia.entidades.Remedio;

public class RemedioDAO {

	private static Connection con;

	public RemedioDAO() throws SQLException, ClassNotFoundException {
		con = Conecta.criarConexao();
	}

	// INSERT
	public void cadastrarRemedio(Remedio item) {

		String sql = "INSERT INTO Remedio(Descricao, Valor, Quantidade, Validade) VALUES(?,?,?,?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setString(1, item.getDescricao());
			preparador.setDouble(2, item.getValor());
			preparador.setInt(3, item.getQuantidade());
			preparador.setDate(4, new java.sql.Date(item.getValidade().getTime()));

			preparador.execute();
			preparador.close();

			System.out.println("Inserção realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	// UPDATE
	public void updateRemedio(Remedio item) {
		
		String sql = "UPDATE Remedio SET Quantidade = ? WHERE Codigo = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, item.getQuantidade());
			preparador.setInt(2, item.getCodigo());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alteração realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: "+ e.getMessage());
		}
	}

	// DELETE
	public void deleteRemedio(Remedio item) {

		String sql = "DELETE FROM Remedio WHERE codigo = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, item.getCodigo());

			preparador.execute();
			preparador.close();

			System.out.println("Exclusão realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// SELECT ALL
	public static List<Remedio> selectTodos() {
		
		String sql = "SELECT * FROM Remedio";
		
		List<Remedio> lista = new ArrayList<Remedio>();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();
			
			while (resultados.next()) {
				
				Remedio item = new Remedio();

				item.setCodigo(resultados.getInt("codigo"));   
				item.setDescricao(resultados.getString("descricao"));
				item.setValor(resultados.getDouble("valor"));
				item.setQuantidade(resultados.getInt("quantidade"));
				item.setValidade(resultados.getDate("validade"));
			
				lista.add(item);
			} 
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return lista; 
	}

	// SELECT ONE
    public static List<Remedio> selectPorCodigo(int codigo) {
    	
        String sql = "SELECT * FROM Remedio WHERE Codigo = ?"; 
        
		List<Remedio> lista = new ArrayList<Remedio>();
        
        try {
            PreparedStatement preparador = con.prepareStatement(sql);   
            preparador.setInt(1, codigo);
            
            ResultSet resultados = preparador.executeQuery();

            if(resultados.next()) {
            	
				Remedio item = new Remedio();
				
				item.setCodigo(resultados.getInt("codigo"));
				item.setDescricao(resultados.getString("descricao"));
				item.setValor(resultados.getDouble("valor"));
				item.setQuantidade(resultados.getInt("quantidade"));
				item.setValidade(resultados.getDate("validade"));
				
				lista.add(item);
            }
        } catch (SQLException e){
			System.out.println("Erro: " + e.getMessage());
        }
        
        return lista;
    }
}