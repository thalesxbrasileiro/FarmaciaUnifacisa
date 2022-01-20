package br.com.farmacia.entidades;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.farmacia.dao.CompraDAO;

public class Compra {
	
	private int codCompra;
	private Long cpfCliente;
	private int codRem;
	private String metdPagmento;
	private Date dataCompra;
	
	public Compra() {
	}

	public Compra(int codCompra, Long cpfCliente, int codRem, String metdPagmento, Date dataCompra) {
		this.codCompra = codCompra;
		this.cpfCliente = cpfCliente;
		this.codRem = codRem;
		this.metdPagmento = metdPagmento;
		this.dataCompra = dataCompra;
	}

	public static void menu(Scanner scanner) {

		int opcao = -1;
		while (opcao != 6) {

			System.out.println("========== Farmácia Unifacisa ==========\n");
			System.out.println("[1] Adicionar compra");
			System.out.println("[2] Atualizar compra no sistema");
			System.out.println("[3] Excluir compra");
			System.out.println("[4] Listar compras");
			System.out.println("[5] Procurar compra\n");
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
			} 
			else {
				System.out.println("Opção inválida, tente novamente.\n");
			}
		}
	}		
	
	public static void insert(Scanner scanner) {
		
		Compra compra = new Compra();

		System.out.println("\nADICIONAR COMPRA");
		
		scanner.nextLine();
		System.out.print("CPF do cliente: ");
		compra.setCpfCliente(scanner.nextLong());
		
		System.out.print("Código do remédio: ");
		compra.setCodRem(scanner.nextInt());
		scanner.nextLine();
		
		System.out.print("Método de pagamento: ");
		compra.setMetdPagmento(scanner.nextLine());
		
		Date dataAtual = new Date();        
		compra.setDataCompra(dataAtual);

		System.out.println();

		CompraDAO compraDAO = null;

		try {
			compraDAO = new CompraDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		compraDAO.cadastrarCompra(compra);
	}		
	
	public static void update(Scanner scanner) { 
		
		Compra compra = new Compra();
		
		System.out.println("\nATUALIZAÇÃO DO MÉTODO DE PAGAMENTO DA COMPRA");
		
		System.out.print("Digite o código da compra: ");
		int codCompra = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("\nInforme o novo método de pagamento: ");
		String novoMetdPag = scanner.nextLine();

		System.out.println();
		
		compra.setCodCompra(codCompra); 
		compra.setMetdPagmento(novoMetdPag);
		
		CompraDAO compraDAO = null;
		
		try {
			compraDAO = new CompraDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		compraDAO.updateCompra(compra);
	}
	
	public static void delete(Scanner scanner) {

		Compra compra = new Compra();

		CompraDAO compraDAO = null;

		System.out.println("\nEXCLUIR COMPRA");
		System.out.print("Informe o código da compra que deseja excluir: ");
		int codigoDaCompra = scanner.nextInt();
		System.out.print("\nTem certeza que deseja excluir a compra do sistema (S/N)? ");
		char deletar = scanner.next().charAt(0);
		if (deletar == 'S' || deletar == 's') {
			compra.setCodCompra(codigoDaCompra);
			System.out.println();
			
			try {
				compraDAO = new CompraDAO();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			compraDAO.deleteCompra(compra);
		}
		System.out.println();
	}
	
	public static void SelectAll() {
		
		@SuppressWarnings("unused")
		CompraDAO compraDAO = null;
		
		try {
			compraDAO = new CompraDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Compra> listaResultado = CompraDAO.selectTodos();
		
		System.out.println("\nLISTA DE COMPRAS:");

		for(Compra c:listaResultado) { 
			System.out.println("-> [Código da Compra]: " + c.getCodCompra() 
								+ " [CPF do cliente]: " + c.getCpfCliente()
								+ " [Código do remédio]: " + c.getCodRem()
								+ " [Método de pagamento]: " + c.getMetdPagmento() 
								+ " [Data da compra]: " + c.getDataCompra());
		}
		
		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}	
	
	public static void SelectOne(Scanner scanner) {
		
		@SuppressWarnings("unused")
		CompraDAO compraDAO = null;
		
		System.out.println("\nBUSCA DE COMPRA POR CÓDIGO");
		System.out.print("Digite o código da compra: ");
		int codigoCompra = scanner.nextInt();
		System.out.println();
		System.out.print("RESULTADO DA COMPRA DE CÓDIGO ");

		try {
			compraDAO = new CompraDAO();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Compra> listaResultado = CompraDAO.selectPorCodigo(codigoCompra); 

		for(Compra c:listaResultado) { 
			System.out.println(codigoCompra + ":\n" 
								+ "-> [CPF do cliente]: " + c.getCpfCliente() 
								+ " [Código do remédio]: " + c.getCodRem()
								+ " [Método de pagamento]: " + c.getMetdPagmento() 
								+ " [Data da compra]: " + c.getDataCompra());
		}

		System.out.println();
		System.out.println("Busca realizada!");
		System.out.println();
	}

	public int getCodCompra() {
		return codCompra;
	}

	public void setCodCompra(int codCompra) {
		this.codCompra = codCompra;
	}

	public Long getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(Long cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public int getCodRem() {
		return codRem;
	}

	public void setCodRem(int codRem) {
		this.codRem = codRem;
	}

	public String getMetdPagmento() {
		return metdPagmento;
	}

	public void setMetdPagmento(String metdPagmento) {
		this.metdPagmento = metdPagmento;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
}
