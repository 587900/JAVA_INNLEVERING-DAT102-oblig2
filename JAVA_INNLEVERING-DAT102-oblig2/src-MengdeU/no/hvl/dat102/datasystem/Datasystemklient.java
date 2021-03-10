package no.hvl.dat102.datasystem;

import java.util.Scanner;

public class Datasystemklient {
	
	public static void main(String[] args) {
		Scanner tastatur = new Scanner(System.in);
		Datakontakt data = new Datakontakt();
		Tekstgrensesnitt tekst = new Tekstgrensesnitt(tastatur);
		int input;
		String navn;
		do {
			System.out.println("Legg til medlem (1) Finn partner (2) Tilbakestill medlem (3) "
					+ "Skriv ut parliste (4) Avslutt (0)");

			input = tastatur.nextInt();
			String t = tastatur.nextLine();

			switch (input) {
			case 1:
				data.leggTilMedlem(tekst.lesMedlem());
				break;
			case 2:
				System.out.println("Oppgi medlem som skal matches");
				navn = tastatur.nextLine();
				data.finnPartnerFor(navn);
				break;
			case 3:
				System.out.println("Oppgi medlem som skal tilbakestilles");
				navn = tastatur.nextLine();
				data.tilbakestillStatusIndeks(navn);
				break;
			case 4:
				tekst.skrivParListe(data);
			}
		} while (input != 0);
		tastatur.close();
	}
}
