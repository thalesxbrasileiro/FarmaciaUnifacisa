package br.com.farmacia.aplicacao;

import java.util.Scanner;

import br.com.farmacia.entidades.Cliente;
import br.com.farmacia.entidades.Compra;
import br.com.farmacia.entidades.Remedio;

public class Aplicacao {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int opcao = -1;
		while (opcao != 9) {
			System.out.println("========== Farmácia Unifacisa ==========\n");
			System.out.println("[1] Para Compras");
			System.out.println("[2] Para Cliente");
			System.out.println("[3] Para Remédio\n");
			System.out.println("[9] SAIR\n");
			System.out.print("Digite a Opção: ");

			opcao = scanner.nextInt();
			
			System.out.println();

			if (opcao == 1) {
				Compra.menu(scanner);
			} else if (opcao == 2) {
				Cliente.menu(scanner);
			} else if (opcao == 3) {
			 	Remedio.menu(scanner);  
			} else if (opcao == 9) {
				opcao = SairDoPrograma(scanner, opcao);
			} else {
				System.out.println("Opção inválida, tente novamente.\n");
			}
		}

		scanner.close();
	}

	private static int SairDoPrograma(Scanner scanner, int opcao) {
		System.out.print("Tem certeza que deseja sair do sistema (S/N)? ");
		char sair = scanner.next().charAt(0);
		if (sair == 'N' || sair == 'n') {
			opcao = -1;
			System.out.println();
		}
		System.out.println("\nVocê saiu do sistema!");
		return opcao;
	}
}
