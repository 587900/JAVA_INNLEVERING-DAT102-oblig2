package no.hvl.dat102.datasystem;
import java.util.Scanner;
import no.hvl.dat102.mengde.kjedet.*;

public class Tekstgrensesnitt {
	// Klasse for inn/ut terminal
	// leser opplysningene om et medlem fra tastatur
	public Medlem lesMedlem() {
		KjedetMengde<Hobby> hobbyer = new KjedetMengde<Hobby>();
		Scanner tastatur = new Scanner(System.in);
		System.out.println("Oppgi navn");
		String navn = tastatur.nextLine();

		
		String hobby;
		do {
			System.out.println("Oppgi hobbyer, avslutt med zzz");
			hobby = tastatur.nextLine();
			Hobby h = new Hobby(hobby);
			hobbyer.leggTil(h);
		} while (!(hobby.equals("zzz"))) ;

		Medlem medlem = new Medlem(navn, hobbyer, -1);
		System.out.println("Medlem er lagt til");
		tastatur.close();
		return medlem;
			}

	// f.eks. bruke Scanner.
	// skriver ut hobbylisten for et medlem
	public void skrivHobbyListe(Medlem medlem) {
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	public void skrivParListe(Datakontakt arkiv) {
		for (int i = 0; i < arkiv.getAntallMedlemmer(); i++) {
			Medlem[] tab = arkiv.getMedlemstabell();
			if (arkiv.finnMedlemIndeks(tab[i].getNavn(null)) != -1) {
				int m = arkiv.finnPartnerFor(tab[i].getNavn(null));
				System.out.print(tab[i].getNavn(null) + " og ");
				System.out.print(tab[m].getNavn(null));
				System.out.print("   " + tab[i].getHobbyer().toString());
			}
		}
	}

}

