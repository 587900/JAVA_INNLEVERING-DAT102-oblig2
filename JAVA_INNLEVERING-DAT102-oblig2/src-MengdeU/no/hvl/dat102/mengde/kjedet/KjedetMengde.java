package no.hvl.dat102.mengde.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.*;
import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.*;

public class KjedetMengde<T> implements MengdeADT<T> {
	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) { // Hvis elementet ikke finnes i mengden,
			LinearNode<T> node = new LinearNode<T>(element); // legges elementet inn
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat = null;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}

	/**
	 * Søker etter og fjerner element. Returnerer null-ref ved ikke-funn
	 * @param element - Elementet du vil fjerne
	 * @return elementet som ble fjernet
	 */
	@Override
	public T fjern(T element) {
		
		//Check if empty
		if (erTom())
			throw new EmptyCollectionException("fjern: Mengden er tom!");
		
		//Sjekk startelement
		if (start.getElement().equals(element)) {
			T resultat = start.getElement();
			start = start.getNeste();
			antall--;
			return resultat;
		}
		
		//Sjekk resten av mengden
		LinearNode<T> forgjenger, aktuell;
		forgjenger = start;
		aktuell = start.getNeste();
		for (int sok = 2; sok <= antall; sok++) {
			if (aktuell.getElement().equals(element)) {
				forgjenger.setNeste(aktuell.getNeste());
				antall--;
				return aktuell.getElement();
			}
			
			forgjenger = aktuell;
			aktuell = aktuell.getNeste();
		}
		
		//Fant ingen element
		return null;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}
	
	/**
	 * Sjekker om input-objekt er identisk med dette objektet.
	 * Det må være av typen MengdeADT
	 * av lik lengde
	 * og inneholde de samme elementer, i henhold til .equals.
	 * @param m2 - Objektet som skal sammenliknes
	 * @return true hvis de er like, false hvis ikke
	 */
	@Override
	public boolean equals(Object m2) {
		if (!(m2 instanceof MengdeADT)) return false;
		MengdeADT<?> obj = (MengdeADT<?>) m2;
		
		if(antall() != obj.antall()) return false;
		
		Iterator<T> iterator1 = oppramser();
		Iterator<?> iterator2 = obj.oppramser();
		
		while (iterator1.hasNext()) {
			if (!(iterator1.next().equals(iterator2.next()))) return false;
		}
		
		return true;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<T>();
		LinearNode<T> aktuell = start;
		T element = null;

		// *Start* Kode skrevet i forelesning
		while (aktuell != null) {// Ubetinget innsetting
			((KjedetMengde<T>) begge).settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();
		}

		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!this.inneholder(element)) {// Tester mot "konstant" mengde
				((KjedetMengde<T>) begge).settInn(element);
			}
		}
		// *Slutt* Kode skrevet i forelesning

		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<T>();
		T element;
		/*
		 * Fyll ut...
		 * 
		 * if (this.inneholder(element)) ((KjedetMengde<T>) snittM).settInn(element);
		 */
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<T>();
		T element;
		/*
		 * Fyll ut
		 * 
		 */

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		boolean erUnderMengde = true;
		// Fyll ut
		return erUnderMengde;
	}

	@Override
	public Iterator<T> oppramser() {
		return new KjedetIterator<T>(start);
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<T>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

}// class
