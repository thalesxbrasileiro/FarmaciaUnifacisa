package br.com.farmacia.entidades;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.farmacia.dao.RemedioDAO;

public class Remedio {
	
	private int codigo;
	private String descricao;
	private double valor;
	private int quantidade;
	private Date validade;

	public Remedio() {
	}

	public Remedio(int codigo, String descricao, double valor, int quantidade, Date validade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.validade = validade;
	}

	public static void menu(Scanner scanner) {

		int opcao = -1;
		while (opcao != 6) {

			System.out.println("========== Farmácia Unifacisa ==========\n");
			System.out.println("[1] Cadastrar remédio");
			System.out.println("[2] Atualizar cadastro de remédio");
			System.out.println("[3] Excluir remédio");
			System.out.println("[4] Listar remédios");
			System.out.println("[5] Procurar remédio\n");
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

		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		Remedio remedio = new Remedio();
		
		scanner.nextLine();
		System.out.println("\nCADASTRO DO REMÉDIO");
		System.out.print("Descrição: ");
		remedio.setDescricao(scanner.nextLine());
		
		System.out.print("Valor: ");
		remedio.setValor(scanner.nextDouble());
		
		System.out.print("Quantidade: ");
		remedio.setQuantidade(scanner.nextInt());
		
		System.out.print("Validade: ");
		try {
			remedio.setValidade(data.parse(scanner.next()));
			System.out.println();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		RemedioDAO remedioDAO = null;

		try {
			remedioDAO = new RemedioDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		remedioDAO.cadastrarRemedio(remedio);
	}
	
	public static void update(Scanner scanner) {
		
		Remedio remedio = new Remedio();
		
		System.out.println("\nATUALIZAÇÃO DA QUANTIDADE DE REMÉDIOS");
		
		System.out.print("Digite o código do produto: ");
		int codigo = scanner.nextInt();
		
		System.out.print("\nInforme a nova quantidade de remédios que deseja atualizar: ");
		int quantidade = scanner.nextInt();
		
		System.out.println();
		
		remedio.setCodigo(codigo);
		remedio.setQuantidade(quantidade);
		
		RemedioDAO remedioDAO = null;
		
		try {
			remedioDAO = new RemedioDAO();
		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
				e.printStackTrace(); } 
		remedioDAO.updateRemedio(remedio);
	} 

	public static void delete(Scanner scanner) {

		Remedio remedio = new Remedio();

		RemedioDAO remedioDAO = null;

		System.out.println("\nEXCLUSÃO DO REMÉDIO");
		System.out.print("Informe o código do remédio que deseja excluir: ");
		int codigoRemedio = scanner.nextInt();
		System.out.print("\nTem certeza que deseja excluir o remédio do sistema (S/N)? ");
		
		char deletar = scanner.next().charAt(0);
		
		if (deletar == 'S' || deletar == 's') {
			remedio.setCodigo(codigoRemedio);
			System.out.println();
		
			try {
				remedioDAO = new RemedioDAO();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			remedioDAO.deleteRemedio(remedio);
		}
	}
	
	public static void SelectAll() {
		
		@SuppressWarnings("unused")
		RemedioDAO remedioDAO = null;
		
		try {
			remedioDAO = new RemedioDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Remedio> listaResultado = RemedioDAO.selectTodos();
		
		System.out.println("\nLISTA DE REMÉDIOS NO SISTEMA:");

		for(Remedio r:listaResultado) { 
			System.out.println("-> [Código]: " + r.getCodigo() 
								+ " [Descrição]: " + r.getDescricao() 
								+ " [Valor]: " + r.getValor() 
								+ " [Quantidade]: " + r.getQuantidade()
								+ " [Validade]: " + r.getValidade());
		}
		
		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}

	public static void SelectOne(Scanner scanner) {
		
		@SuppressWarnings("unused")
		RemedioDAO remedioDAO = null;
		
		System.out.println("\nBUSCA DE REMÉDIO POR CÓDIGO");
		System.out.print("Digite o código do remédio: ");
		int codigo = scanner.nextInt();
		System.out.println();
		System.out.print("RESULTADO DO REMÉDIO DE CÓDIGO DE Nº ");

		try {
			remedioDAO = new RemedioDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Remedio> listaResultado = RemedioDAO.selectPorCodigo(codigo); 

		for(Remedio r:listaResultado) { 
			System.out.println(codigo + ":\n" 
								+ "-> [Descrição]: " + r.getDescricao() 
								+ " [Valor]: " + r.getValor() 
								+ " [Quantidade]: " + r.getQuantidade()
								+ " [Validade]: " + r.getValidade());
		}
		
		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}
}
