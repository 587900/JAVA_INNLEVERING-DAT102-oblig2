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
	 * 
	 * @param element - Elementet du vil fjerne
	 * @return elementet som ble fjernet
	 */
	@Override
	public T fjern(T element) {

		// Check if empty
		if (erTom())
			throw new EmptyCollectionException("fjern: Mengden er tom!");

		// Sjekk startelement
		if (start.getElement().equals(element)) {
			T resultat = start.getElement();
			start = start.getNeste();
			antall--;
			return resultat;
		}

		// Sjekk resten av mengden
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

		// Fant ingen element
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
	 * Sjekker om input-objekt er identisk med dette objektet. Det m� v�re av typen
	 * MengdeADT av lik lengde og inneholde de samme elementer, i henhold til
	 * .equals.
	 * 
	 * Vi antar at rekkef�lge p� elementer er relevant (evt komme tilbake og godta
	 * duplikater)
	 * 
	 * @param m2 - Objektet som skal sammenliknes
	 * @return true hvis de er like, false hvis ikke
	 */
	@Override
	public boolean equals(Object m2) {

		if (!(m2 instanceof MengdeADT<?>))
			return false;
		MengdeADT<T> obj;
		try {
			obj = (MengdeADT<T>) m2;
		} catch (ClassCastException e) {
			return false;
		}

		if (antall() != obj.antall())
			return false;

		Iterator<T> iterator = oppramser();

		while (iterator.hasNext()) {
			if (!obj.inneholder(iterator.next()))
				return false;
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

	/**
	 * Matematisk union av 2 mengder (denne mengden og en gitt mengde) Hvis et
	 * element er i en av mengdene, skal den vere med i retur-mengden
	 * 
	 * @param m2 - Mengden � unionisere med
	 * @returns En unionisert mengde
	 */
	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {

		// vi kunne ogs�
		// MengdeADT<T> begge = new KjedetMengde<T>();
		// ((KjedetMengde<T>)begge).settInn(element);
		// men vi foretrekker f�lgende:

		KjedetMengde<T> begge = new KjedetMengde<T>();
		Iterator<T> iterator1 = oppramser();
		while (iterator1.hasNext())
			begge.leggTil(iterator1.next());

		Iterator<T> iterator2 = m2.oppramser();
		while (iterator2.hasNext()) {
			T element = iterator2.next();
			if (!this.inneholder(element))
				begge.settInn(element);
		}

		return begge;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		KjedetMengde<T> snittM = new KjedetMengde<T>();

		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (m2.inneholder(element))
				snittM.leggTil(element);
		}

		return snittM;
	}

	// O(n*m) n = antall element i m1, m = antall element i m2
	// mulig O(n) + O(m) med hashmap
	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		KjedetMengde<T> differensM = new KjedetMengde<T>();

		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (!m2.inneholder(element))
				differensM.leggTil(element);
		}

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			if (!m2.inneholder(iterator.next()))
				return false;
		}
		return true;
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

	@Override
	public String toString() {
		String resultat = "";
		Iterator<T> it = oppramser();
		while (it.hasNext()) {
			resultat += it.next().toString() + "\t";
		}
		return resultat;
	}

}// class
