package Jogo;

import java.util.Scanner;

public class Forca {

	static Condicao estado = Condicao.AGUARDANDO_CHUTE;
	static boolean verificaVitoria = false;

	static String palavra = "abacaxi";

	static int vidas = 6;
	static String letra;
	static String letrasChutadas = "";

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
	}

	public static void buscarDadosDoJogo() {
		System.out.println("\n" + "\nVoce tem " + vidas + " vidas restantes" + "\nLetras chutadas: " + letrasChutadas
				+ "\nAguardando chute:");
	}

	@SuppressWarnings({ "resource" })
	public static void chutar() {
		letra = new Scanner(System.in).next().toLowerCase();

		if (letra.matches("[a-z]")) {
			letrasChutadas += " " + letra;
		}

//		for (int i = 0; i < letrasChutadas.length(); i++) {
//			if (letra != letrasChutadas.contains(letra)) {
//				System.out.println("ssss");
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
