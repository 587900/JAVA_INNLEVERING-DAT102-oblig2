package no.hvl.dat102.oppgave4EKS;

import java.util.Random;

public class Sortering {
	private static final int MIN = 10;

	public static <T extends Comparable<T>> void utvalgSortering(T[] data) {
		int minste;
		T temp;
		for (int neste = 0; neste < data.length - 1; neste++) {
			minste = neste;
			for (int sok = neste + 1; sok < data.length; sok++) {
				if (data[sok].compareTo(data[minste]) < 0) {
					minste = sok;
				}
			}

			temp = data[minste];
			data[minste] = data[neste];
			data[neste] = temp;
		}
	}

	public static <T extends Comparable<T>> void fletteSort(T[] tabell, int forste, int siste) {
		if (forste < siste) {// minst to elementer
			int midten = (forste + siste) / 2;
			// Sorter venstre halvdel tabell[f�rste,midten];
			fletteSort(tabell, forste, midten);
			// Sorter h�yre halvdel tabell[midten+1,siste]
			fletteSort(tabell, midten + 1, siste);
			// Fletter de to halvdelene
			flette(tabell, forste, midten, siste);
		}
	}

	private static <T extends Comparable<T>> void flette(T[] tabell, int forste, int midten, int siste) {
		int stor = siste - forste + 1;
		@SuppressWarnings("unchecked")
		T[] hjelpeTabell = (T[]) (new Comparable[stor]);
		// Initierer lokale indekser
		// start og slutt p� venstre deltabell
		int forste1 = forste;
		int siste1 = midten;
		// start og slutt p� h�yre deltabell
		int forste2 = midten + 1;
		int siste2 = siste;
		int indeks = 0;
		while ((forste1 <= siste1) && (forste2 <= siste2)) {
			if (tabell[forste1].compareTo(tabell[forste2]) <= 0) {
				hjelpeTabell[indeks] = tabell[forste1];
				forste1++;
			} else {
				hjelpeTabell[indeks] = tabell[forste2];
				forste2++;
			}
			indeks++;
		}
		while (forste1 <= siste1) {
			hjelpeTabell[indeks] = tabell[forste1];
			forste1++;
			indeks++;
		} // while
			// kopiere resten av h�yre del (kan v�re tom)
		while (forste2 <= siste2) {
			hjelpeTabell[indeks] = tabell[forste2];
			forste2++;
			indeks++;
		}
		int h = 0;
		for (indeks = forste; indeks <= siste; indeks++) {
			tabell[indeks] = hjelpeTabell[h];
			h++;
		}
	}

	public static <T extends Comparable<T>> void InnsettingSortering(T[] data) {
		for (int i = 1; i < data.length; i++) {
			T nokkel = data[i];
			int p = i;

			while (p > 0 && data[p - 1].compareTo(nokkel) > 0) {
				data[p] = data[p - 1];
				p--;
			}
			data[p] = nokkel;
		}
	}

	public static <T extends Comparable<T>> void BobleSortering(T[] data) {
		int p, sok;
		T temp;
		for (p = data.length - 1; p >= 0; p--) {
			for (sok = 0; sok <= p - 1; sok++) {
				if (data[sok].compareTo(data[sok + 1]) > 0) {
					temp = data[sok];
					data[sok] = data[sok + 1];
					data[sok + 1] = temp;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void kvikkSort(T[] data) {
		kvikkSort(data, 0, data.length - 1);
	}

	private static <T extends Comparable<T>> void kvikkSort(T[] data, int min, int maks) {
		int posPartisjon;
		if (min < maks) {

			/** Lager partisjon */
			posPartisjon = finnPartisjon(data, min, maks);
			/** Sorterer venstre side */
			kvikkSort(data, min, posPartisjon - 1);
			/** Sorterer h�yre side */
			kvikkSort(data, posPartisjon + 1, maks);
		}
	}

	private static <T extends Comparable<T>> int finnPartisjon(T[] data, int min, int maks) {
		int venstre, hoyre;
		T temp, pivot;

		pivot = data[min];
		venstre = min;
		hoyre = maks;
		while (venstre < hoyre) {
			while( venstre < hoyre && data[venstre].compareTo(pivot)<=0)
				venstre++;
			while (data[hoyre].compareTo(pivot) > 0) 
				hoyre--;
			
			if (venstre < hoyre) {
				temp = data[venstre];
				data[venstre] = data[hoyre];
				data[hoyre] = temp;
			}
		}
		temp = data[min];
		data[min] = data[hoyre];
		data[hoyre] = temp;
		return hoyre;
	}

	public static <T extends Comparable<T>> void kvikkSortNy(T[] data, int min, int maks) {
		kvikkSort2(data, min, maks);
		sorteringVedInnsetting2(data, min, maks);
	}

	public static <T extends Comparable<T>> void kvikkSort2(T[] data, int min, int maks) {
		int posPartisjon;
		if (maks - min + 1 > MIN) {// antall elementer > MIN ?
			/** Lager partisjon */
			posPartisjon = finnPartisjon(data, min, maks);
			/** Sorterer venstre side */
			kvikkSort(data, min, posPartisjon - 1);
			/** Sorterer h�yre side */
			kvikkSort(data, posPartisjon + 1, maks);
		}
	}// metode

	public static <T extends Comparable<T>> void sorteringVedInnsetting2(T[] data, int forste, int siste) {
		for (int indeks = forste + 1; indeks <= siste; indeks++) {
			T nokkel = data[indeks];
			int p = indeks;
			// Forskyv st�rre verdier mot h�yre
			while (p > 0 && data[p - 1].compareTo(nokkel) > 0) {
				data[p] = data[p - 1];
				p--;
			}
			data[p] = nokkel;
		} // ytre
	}// metode

	public static void main(String[] args) {
		// Til bruk ved sortering av store tabellar
		Random tilfeldig = new Random();
		int n = 16000;
		int antal = 10;

		/*
		 * Integer[][] a = new Integer[antal][n]; // set inn tilfeldige heiltal i alle
		 * rekker for (int i = 0; i < antal; i++) { for (int j = 0; j < n; j++) {
		 * a[i][j] = tilfeldig.nextInt(); } }
		 */
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = tilfeldig.nextInt();
		}
		// sorter kvar rekke
		long start = System.nanoTime();

		for (int i = 0; i < antal; i++) {
			kvikkSort(a);
		}
		long slutt = System.nanoTime();

		System.out.println(slutt - start);
	}
}

