package br.com.farmacia.entidades;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.farmacia.dao.ClienteDAO;

public class Cliente {

	private Long cpf;
	private String nome;
	private String rua ;
	private String numero;
	private String bairro;
	private String cidade;

	public Cliente() {
	}

	public Cliente(Long cpf, String nome, String rua, String numero, String bairro, String cidade) {
		this.cpf = cpf;
		this.nome = nome;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}
	
	public static void menu(Scanner scanner) {

		int opcao = -1;
		while (opcao != 6) {

			System.out.println("========== Farmácia Unifacisa ==========\n");
			System.out.println("[1] Cadastrar cliente");
			System.out.println("[2] Atualizar cadastro de cliente");
			System.out.println("[3] Excluir cliente");
			System.out.println("[4] Listar clientes");
			System.out.println("[5] Procurar cliente\n");
			System.out.println("[6] MENU Principal");
			System.out.println("[9] SAIR\n");
			System.out.print("Digite a Opção: ");

			opcao = scanner.nextInt();

			if (opcao == 1) {
				insert(scanner);
			} else if (opcao == 2) {
				update(scanner);
			} else if (opcao == 3) {
				delete(scanner);
			} else if (opcao == 4) {
				SelectAll();
			} else if (opcao == 5) {
				SelectOne(scanner);
			} else if (opcao == 6) {
				System.out.println();
			} else if (opcao == 9) {
				
				System.out.print("Tem certeza que deseja sair do sistema (S/N)? ");
				char sair = scanner.next().charAt(0);
				if (sair == 'S' || sair == 's') {
					System.out.println("\nVocê saiu do sistema!");
					System.exit(0);
				}
				System.out.println();
			} else {
				System.out.println("Opção inválida, tente novamente.\n");
			}
		}
	}	
	
	public static void insert(Scanner scanner) {

		Cliente cliente = new Cliente();

		System.out.println("\nCADASTRO DO CLIENTE");
		
		scanner.nextLine();
		System.out.print("Nome: ");
		cliente.setNome(scanner.nextLine());
		
		System.out.print("Cpf: ");
		cliente.setCpf(scanner.nextLong());
		scanner.nextLine();

		System.out.print("Rua: ");
		cliente.setRua(scanner.nextLine());
		
		System.out.print("Numero: ");
		cliente.setNumero(scanner.nextLine()); 

		System.out.print("Bairro: ");
		cliente.setBairro(scanner.nextLine());

		System.out.print("Cidade: ");
		cliente.setCidade(scanner.nextLine());

		System.out.println();

		ClienteDAO clienteDAO = null;

		try {
			clienteDAO = new ClienteDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		clienteDAO.cadastrarCliente(cliente);
	}	
	
	public static void update(Scanner scanner) {
		
		Cliente cliente = new Cliente();
		
		System.out.println("\nATUALIZAÇÃO DO ENDEREÇO DO CLIENTE");
		
		System.out.print("Digite o CPF do cliente: ");
		Long cpf = scanner.nextLong();
		scanner.nextLine();

		System.out.print("Digite o nome da nova rua: ");
		String rua = scanner.nextLine();
		
		System.out.print("Digite o novo número do endereço: ");
		String numero = scanner.nextLine();
		
		System.out.print("Digite o nome do novo bairro: ");
		String bairro = scanner.nextLine();
		
		System.out.print("Digite o nome da cidade: ");
		String cidade = scanner.nextLine();
		
		System.out.println();
		
		cliente.setCpf(cpf); 
		cliente.setRua(rua); 
		cliente.setNumero(numero);
		cliente.setBairro(bairro);
		cliente.setCidade(cidade);
		
		ClienteDAO clienteDAO = null;
		
		try {
			clienteDAO = new ClienteDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		clienteDAO.updateCliente(cliente);
	}

	public static void delete(Scanner scanner) {

		Cliente cliente = new Cliente();

		ClienteDAO clienteDAO = null;

		System.out.println("\nEXCLUSÃO DE CLIENTE");
		System.out.print("Informe o CPF do cliente que deseja excluir: ");
		Long cpfCliente = scanner.nextLong();
		System.out.print("\nTem certeza que deseja excluir o cliente do sistema (S/N)? ");
		char deletar = scanner.next().charAt(0);
		if (deletar == 'S' || deletar == 's') {
			cliente.setCpf(cpfCliente);
			System.out.println();
			
			try {
				clienteDAO = new ClienteDAO();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			clienteDAO.deleteCliente(cliente);
		}
		System.out.println();
	}
	
	public static void SelectAll() {
		
		@SuppressWarnings("unused")
		ClienteDAO clienteDAO = null;
		
		try {
			clienteDAO = new ClienteDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Cliente> listaResultado = ClienteDAO.selectTodos();
		
		System.out.println("\nLISTA DE CLIENTES:");

		for(Cliente c:listaResultado) { 
			System.out.println("-> [Cpf]: " + c.getCpf() 
								+ " [Nome]: " + c.getNome() 
								+ " [Rua]: " + c.getRua() 
								+ " [Número]: " + c.getNumero()
								+ " [Bairro]: " + c.getBairro()
								+ " [Cidade]: " + c.getCidade());
		}
		
		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}
	
	public static void SelectOne(Scanner scanner) {
		
		@SuppressWarnings("unused")
		ClienteDAO clienteDAO = null;
		
		System.out.println("\nBUSCA DE CLIENTE POR CPF");
		System.out.print("Digite o CPF do cliente: ");
		Long cpf = scanner.nextLong();
		System.out.println();
		System.out.print("RESULTADO DO CLIENTE DE CPF Nº ");

		try {
			clienteDAO = new ClienteDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Cliente> listaResultado = ClienteDAO.selectPorId(cpf); 

		for(Cliente c:listaResultado) { 
			System.out.println(cpf + ":\n" 
								+ "-> [Nome]: " + c.getNome() 
								+ " [Rua]: " + c.getRua() 
								+ " [Número]: " + c.getNumero()
								+ " [Bairro]: " + c.getBairro()
								+ " [Cidade]: " + c.getCidade());
		}

		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
