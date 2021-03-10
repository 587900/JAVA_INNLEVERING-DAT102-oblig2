package no.hvl.dat102.mengde.tabell;

import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;

public class TabellMengde<T> implements MengdeADT<T> {
	// ADT-en Mengde implementert som tabell

	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() { // Konstruktør 1
		this(STDK);
	}

	public TabellMengde(int start) { // Konstruktør 2
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = tilf.nextInt(antall);
		svar = tab[indeks];
		tab[indeks] = tab[antall - 1];
		antall--;

		return svar;
	}

	/**
	 * Søker etter og fjerner element. Returnerer null-ref ved ikke-funn
	 * 
	 * @param element - Elementet du vil fjerne
	 * @return elementet som ble fjernet
	 */
	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("fjern: Mengden er tom!");

		for (int i = 0; i < antall; i++) {
			if (tab[i].equals(element)) {
				T svar = tab[i];
				tab[i] = tab[antall - 1];
				antall--;
				return svar;
			}
		}

		return null;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}

	/**
	 * Sjekker om input-objekt er identisk med dette objektet. Det m� v�re av typen
	 * MengdeADT av lik lengde og inneholde de samme elementer, i henhold til
	 * .equals.
	 * 
	 * @param m2 - Objektet som skal sammenliknes
	 * @return true hvis de er like, false hvis ikke
	 */
	@Override
	public boolean equals(Object m2) {
		if (!(m2 instanceof MengdeADT<?>)) return false;
		MengdeADT<T> obj;
		try { obj = (MengdeADT<T>) m2; } catch (ClassCastException e) { return false; }

		if (antall() != obj.antall())
			return false;

		Iterator<T> iterator = oppramser();

		while (iterator.hasNext()) {
			if (!obj.inneholder(iterator.next())) return false;
		}

		return true;
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	/*
	 * Unioniser nokre mengder
	 */
	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {

		TabellMengde<T> begge = new TabellMengde<T>();
		Iterator<T> iterator1 = oppramser();
		while (iterator1.hasNext()) begge.leggTil(iterator1.next());
		
		Iterator<T> iterator2 = m2.oppramser();
		while (iterator2.hasNext()) {
			T element = iterator2.next();
			if (!this.inneholder(element)) begge.settInn(element);
		}

		return begge;
	}
	
	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		TabellMengde<T> snittM = new TabellMengde<T>();
		
		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (m2.inneholder(element)) snittM.leggTil(element);
		}
		
		return snittM;
	}
	
	//O(n*m) n = antall element i m1, m = antall element i m2
	//mulig O(n) + O(m) med hashmap
	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		TabellMengde<T> differensM = new TabellMengde<T>();
		
		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			T element = iterator.next();
			if (!m2.inneholder(element)) differensM.leggTil(element);
		}
		
		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		Iterator<T> iterator = oppramser();
		while (iterator.hasNext()) {
			if (!m2.inneholder(iterator.next())) return false;
		}
		return true;
	}

	@Override
	public Iterator<T> oppramser() {
		return new TabellIterator<T>(tab, antall);
	}

	private void settInn(T element) {
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
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
