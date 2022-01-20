package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.conectapg.Conecta;
import br.com.farmacia.entidades.Compra;

public class CompraDAO {

	private static Connection con;

	public CompraDAO() throws SQLException, ClassNotFoundException {
		con = Conecta.criarConexao();
	}

	// INSERT
	public void cadastrarCompra(Compra item) {

		String sql = "INSERT INTO Compra(CpfCliente, CodRem, MetdPagmento, DataCompra) VALUES(?,?,?,?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setLong(1, item.getCpfCliente());
			preparador.setInt(2, item.getCodRem());
			preparador.setString(3, item.getMetdPagmento());
			preparador.setDate(4, new java.sql.Date(item.getDataCompra().getTime()));

			preparador.execute();
			preparador.close();

			System.out.println("Inserção realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
	// UPDATE
	public void updateCompra(Compra item) {
		
		String sql = "UPDATE Compra SET MetdPagmento = ? WHERE codCompra = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, item.getMetdPagmento());
			preparador.setInt(2, item.getCodCompra());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alteração realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: "+ e.getMessage());
		}
	}

	// DELETE
	public void deleteCompra(Compra item) {

		String sql = "DELETE FROM Compra WHERE codCompra = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setInt(1, item.getCodCompra());

			preparador.execute();
			preparador.close();

			System.out.println("Exclusão realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// SELECT ALL
	public static List<Compra> selectTodos() {
		
		String sql = "SELECT * FROM Compra";
		
		List<Compra> lista = new ArrayList<Compra>();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();
			
			while (resultados.next()) {
				
				Compra item = new Compra();

				item.setCodCompra(resultados.getInt("codcompra"));    
				item.setCpfCliente(resultados.getLong("cpfcliente")); 
				item.setCodRem(resultados.getInt("codrem"));
				item.setMetdPagmento(resultados.getString("MetdPagmento"));
				item.setDataCompra(resultados.getDate("datacompra"));
			
				lista.add(item);
			} 
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return lista; 
	}

	// SELECT ONE
    public static List<Compra> selectPorCodigo(int codigo) {
    	
        String sql = "SELECT * FROM Compra WHERE codcompra = ?"; 
        
		List<Compra> lista = new ArrayList<Compra>();
        
        try {
            PreparedStatement preparador = con.prepareStatement(sql);   
            preparador.setInt(1, codigo);
            
            ResultSet resultados = preparador.executeQuery();

            if(resultados.next()) {
            	
				Compra item = new Compra();
				
				item.setCodCompra(resultados.getInt("codcompra"));    
				item.setCpfCliente(resultados.getLong("cpfcliente")); 
				item.setCodRem(resultados.getInt("codrem"));
				item.setMetdPagmento(resultados.getString("MetdPagmento"));
				item.setDataCompra(resultados.getDate("datacompra"));
				
				lista.add(item);
            }
        } catch (SQLException e){
			System.out.println("Erro: " + e.getMessage());
        }
        
        return lista;
    }
}