package no.hvl.dat102.datasystem;
import java.util.Scanner;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class Tekstgrensesnitt {
	
	private Scanner tastatur;
	
	public Tekstgrensesnitt(Scanner tastatur) {
		this.tastatur = tastatur;
	}
	// Klasse for inn/ut terminal
	// leser opplysningene om et medlem fra tastatur
	public Medlem lesMedlem() {
		KjedetMengde<Hobby> hobbyer = new KjedetMengde<Hobby>();
		System.out.println("Oppgi navn");
		String navn = tastatur.nextLine();

		
		String hobby;
		do {
			System.out.println("Oppgi hobbyer, avslutt med zzz");
			hobby = tastatur.nextLine();
			if (hobby.equals("zzz")) break;
			Hobby h = new Hobby(hobby);
			hobbyer.leggTil(h);
		} while (true);

		Medlem medlem = new Medlem(navn, hobbyer, -1);
		System.out.println("Medlem er lagt til");
		return medlem;
			}

	// f.eks. bruke Scanner.
	// skriver ut hobbylisten for et medlem
	public void skrivHobbyListe(Medlem medlem) {
		System.out.println("Alle hobbyene ");
		System.out.println(medlem.getHobbyer().toString());
	}

	public void skrivParListe(Datakontakt arkiv) {
		Medlem[] tab = arkiv.getMedlemstabell();
		MengdeADT<Integer> listet = new TabellMengde<>();
		
		for (int i = 0; i < arkiv.getAntallMedlemmer(); i++) {
			if (listet.inneholder(i)) continue;	//har allerede skreve ut denne
			
			Medlem m = tab[i];
			int partner = m.getStatusIndeks();
			if (partner == -1) continue;		//viss ingen partner, fortsett
			
			//found partner
			listet.leggTil(i);
			listet.leggTil(partner);
			
			System.out.println(m.getNavn() + " og " + tab[partner].getNavn()
					+ "\t" + m.getHobbyer().toString());
		}
	}

}

