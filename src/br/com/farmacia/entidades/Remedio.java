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

			System.out.println("========== Farm�cia Unifacisa ==========\n");
			System.out.println("[1] Cadastrar rem�dio");
			System.out.println("[2] Atualizar cadastro de rem�dio");
			System.out.println("[3] Excluir rem�dio");
			System.out.println("[4] Listar rem�dios");
			System.out.println("[5] Procurar rem�dio\n");
			System.out.println("[6] MENU Principal");
			System.out.println("[9] SAIR\n");
			System.out.print("Digite a Op��o: ");

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
					System.out.println("\nVoc� saiu do sistema!");
					System.exit(0);
				}
				System.out.println();
			} else {
				System.out.println("Op��o inv�lida, tente novamente.\n");
			}
		}
	}
	
	public static void insert(Scanner scanner) {

		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
		Remedio remedio = new Remedio();
		
		scanner.nextLine();
		System.out.println("\nCADASTRO DO REM�DIO");
		System.out.print("Descri��o: ");
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
		
		System.out.println("\nATUALIZA��O DA QUANTIDADE DE REM�DIOS");
		
		System.out.print("Digite o c�digo do produto: ");
		int codigo = scanner.nextInt();
		
		System.out.print("\nInforme a nova quantidade de rem�dios que deseja atualizar: ");
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

		System.out.println("\nEXCLUS�O DO REM�DIO");
		System.out.print("Informe o c�digo do rem�dio que deseja excluir: ");
		int codigoRemedio = scanner.nextInt();
		System.out.print("\nTem certeza que deseja excluir o rem�dio do sistema (S/N)? ");
		
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
		
		System.out.println("\nLISTA DE REM�DIOS NO SISTEMA:");

		for(Remedio r:listaResultado) { 
			System.out.println("-> [C�digo]: " + r.getCodigo() 
								+ " [Descri��o]: " + r.getDescricao() 
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
		
		System.out.println("\nBUSCA DE REM�DIO POR C�DIGO");
		System.out.print("Digite o c�digo do rem�dio: ");
		int codigo = scanner.nextInt();
		System.out.println();
		System.out.print("RESULTADO DO REM�DIO DE C�DIGO DE N� ");

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
								+ "-> [Descri��o]: " + r.getDescricao() 
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
