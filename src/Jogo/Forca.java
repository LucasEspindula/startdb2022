package Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Forca {

	static Condicao estado = Condicao.AGUARDANDO_CHUTE;
	static boolean verificaVitoria = false;

	static String palavra = "abacaxi";

	static int vidas = 6;
	static String letra;
	static List<String> letrasChutadas = new ArrayList<String>();

	static Scanner in = new Scanner(System.in);
	static char[] letrasAcertadas = new char[palavra.length()];

	public static void main(String[] args) {

		adicionarAnderline();

		while (estado == Condicao.AGUARDANDO_CHUTE) {

			buscarDadosDoJogo();

			chutar();

			controleDeAcerto();
		}
	}

	public static void adicionarAnderline() {
		for (int i = 0; i < palavra.length(); i++) {
			System.out.print(" __ ");
		}
		System.out.println("\n\n Dica: Rei das Frutas");
	}

	public static void buscarDadosDoJogo() {
		System.out.println("\n\nVoce tem " + vidas + " vidas restantes" 
				+ "\nLetras chutadas: " + letrasChutadas.toString().toUpperCase()
				+ "\nAguardando chute:");
	}

	@SuppressWarnings({ "resource" })
	public static void chutar() {
		letra = new Scanner(System.in).next().toLowerCase();

		if (letra.matches("[a-z]")) {
			letrasChutadas.add(letra);
		}

//		if (letrasChutadas.isEmpty() == false) {
//			for (int i = 0; i < letrasChutadas.size(); i++) {
//				if (letrasChutadas.toString().equals(letra.to)) {
//					System.out.println(">>>>>>>>>>>");
//				}
//			}
//		}
	}

	public static void controleDeAcerto() {

		boolean perdeVida = true;
		verificaVitoria = true;

		for (int i = 0; i < palavra.length(); i++) {
			if (palavra.charAt(i) == letra.charAt(0)) {
				letrasAcertadas[i] = 1;
				perdeVida = false;
			}
		}
		
		if (!letra.matches(".?") || letra.matches("[0-9]")) {
			perdeVida = false;
		}
		
		if (perdeVida) {
			if (vidas == 0) {
				estado = Condicao.PERDEU;
				System.out.println("\n Voce perdeu!");
			}
			vidas--;
		}

		if (letra.equals(palavra)) {
			estado = Condicao.GANHOU;
			System.out.println("\n Voce venceu!");
		}

		for (int i = 0; i < palavra.length(); i++) {
			if (estado == Condicao.AGUARDANDO_CHUTE) {
				if (letrasAcertadas[i] == 0) {
					System.out.print(" __ ");
					verificaVitoria = false;
				} else {
					System.out.print(" " + palavra.charAt(i) + " ");
				}
			} else {
				System.out.println("A palavra era: " + palavra.toUpperCase());
				System.exit(0);
			}
		}

		if (verificaVitoria == true && vidas > 0) {
			estado = Condicao.GANHOU;
			System.out.println("\n Voce venceu!");
		}
	}
}
