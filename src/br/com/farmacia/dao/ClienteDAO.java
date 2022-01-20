package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.farmacia.conectapg.Conecta;
import br.com.farmacia.entidades.Cliente;

public class ClienteDAO {

	private static Connection con;

	public ClienteDAO() throws SQLException, ClassNotFoundException {
		con = Conecta.criarConexao();
	}

	// INSERT
	public void cadastrarCliente(Cliente cliente) {

		String sql = "INSERT INTO Cliente(Cpf, Nome, Rua, Numero, Bairro, Cidade) VALUES(?,?,?,?,?,?)";

		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setLong(1, cliente.getCpf());
			preparador.setString(2, cliente.getNome());
			preparador.setString(3, cliente.getRua());
			preparador.setString(4, cliente.getNumero());
			preparador.setString(5, cliente.getBairro());
			preparador.setString(6, cliente.getCidade());

			preparador.execute();
			preparador.close();

			System.out.println("Inserção realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// UPDATE
	public void updateCliente(Cliente cliente) {

		String sql = "UPDATE Cliente SET Rua = ?, Numero = ?, Bairro = ?, Cidade = ? WHERE Cpf = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setString(1, cliente.getRua());
			preparador.setString(2, cliente.getNumero());
			preparador.setString(3, cliente.getBairro());
			preparador.setString(4, cliente.getCidade());
			preparador.setLong(5, cliente.getCpf());

			preparador.execute();
			preparador.close();

			System.out.println("Alteração realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// DELETE
	public void deleteCliente(Cliente cliente) {

		String sql = "DELETE FROM Cliente WHERE cpf = ?";
		try {
			PreparedStatement preparador = con.prepareStatement(sql);

			preparador.setLong(1, cliente.getCpf());

			preparador.execute();
			preparador.close();

			System.out.println("Exclusão realizada!\n");
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

	// SELECT ALL
	public static List<Cliente> selectTodos() {

		String sql = "SELECT * FROM Cliente";

		List<Cliente> lista = new ArrayList<Cliente>();

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet resultados = preparador.executeQuery();

			while (resultados.next()) {

				Cliente cliente = new Cliente();

				cliente.setCpf(resultados.getLong("Cpf"));
				cliente.setNome(resultados.getString("Nome"));
				cliente.setRua(resultados.getString("Rua"));
				cliente.setNumero(resultados.getString("Numero"));
				cliente.setBairro(resultados.getString("Bairro"));
				cliente.setCidade(resultados.getString("Cidade"));

				lista.add(cliente);
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return lista;
	}

	// SELECT ONE
	public static List<Cliente> selectPorId(Long cpf) {

		String sql = "SELECT * FROM Cliente WHERE cpf = ?";

		List<Cliente> lista = new ArrayList<Cliente>();

		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setLong(1, cpf);

			ResultSet resultados = preparador.executeQuery();

			if (resultados.next()) {

				Cliente cliente = new Cliente();

				cliente.setCpf(resultados.getLong("cpf"));
				cliente.setNome(resultados.getString("nome"));
				cliente.setRua(resultados.getString("rua"));
				cliente.setNumero(resultados.getString("numero"));
				cliente.setBairro(resultados.getString("bairro"));
				cliente.setCidade(resultados.getString("cidade"));

				lista.add(cliente);
			}

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return lista;
	}
}
