package no.hvl.dat102.oppg2;

import java.util.Scanner;

public class DobbeltKjedetListeKlient<T extends Comparable<T>> {
	
	static Scanner tastatur;

	public static void main(String[] args) {
		
		tastatur = new Scanner(System.in);
		
		while (true) {
			System.out.println("Velkommen! Hvilken type data ønsker du å handtere? Støttede typer: STRING, INTEGER");
			String choice = tastatur.nextLine().trim().toUpperCase();
			String min, max;
			switch (choice) {
				case "INTEGER":
					System.out.println("Du kan skrive MIN_VALUE eller MAX_VALUE for å få Integer.MIN_VALUE og Integer.MAX_VALUE");
					new DobbeltKjedetListeKlient<Integer>(new IntegerParser());
					break;
				case "STRING":
					new DobbeltKjedetListeKlient<String>(new StringParser());
					break;
				default:
					System.out.println("Invalid type: " + choice);
					break;
			}
		}
		

	}
	
	private DobbeltKjedetListeKlient(Parser<T> parser) {
		
		DobbelKjedetListeS<T> list;
		
		while (true) {
			T min = null;
			T max = null;
			while (min == null) {
				System.out.println("Vennligst skriv inn MINIMUM verdi: ");
				min = parser.parse(tastatur.nextLine());
			}
			while (max == null) {
				System.out.println("Vennligst skriv inn MAKSIMUM verdi: ");
				max = parser.parse(tastatur.nextLine());
			}
			
			try {
				list = new DobbelKjedetListeS<>(min, max);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("Ulovelige verdier. Min må vere mindre enn Maks!");
			}
		}
		
		
		while (true) {
			
			System.out.println("Skriv inn en kommando:");
			System.out.println("leggTil <verdi>, finnes <verdi>, count, fjern <verdi>, showList");
			String text = tastatur.nextLine();
			String[] split = text.split(" ", 2);
			
			String topic = split[0].toUpperCase();
			
			switch (topic) {
				case "LEGGTIL":
					if (split.length == 1) { System.out.println("leggTil treng en verdi! Bruk: leggTil <verdi>"); break; }
					try { list.add(parser.parse(split[1])); } catch (IllegalArgumentException e) { System.out.println("Ulovelig verdi. Utenfor MIN/MAKS. La IKKE til elementet"); }
					break;
				case "FINNES":
					if (split.length == 1) { System.out.println("finnes treng en verdi! Bruk: finnes <verdi>"); break; }
					if (list.exists(parser.parse(split[1]))) System.out.println("Elementet finnes!"); else System.out.println("Elementet finnes IKKE!");
					break;
				case "COUNT":
					System.out.println("Elementer i listen: " + list.count());
					break;
				case "FJERN":
					if (split.length == 1) { System.out.println("fjern treng en verdi! Bruk: fjern <verdi>"); break; }
					T r = list.remove(parser.parse(split[1]));
					if (r == null) System.out.println("Fann ikke elementet i listen. Fjernet ingenting."); else System.out.println("Fjernet elementet!");
					break;
				case "SHOWLIST":
					System.out.println("Skriver ut listen: ");
					list.showList();
					break;
				default:
					System.out.println("Ukjent kommando: " + split[0]);
					break;
			}
			System.out.println();
			
			
		}
	}
	
	private static void test() {
		
		DobbelKjedetListeS<Integer> liste = new DobbelKjedetListeS<>(Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		for(int i = 0; i < 100; i+=9) liste.add(i);
		
		liste.showList();
		
		System.out.println("25 exists: " + liste.exists(25));
		System.out.println("27 exists: " + liste.exists(27));
		
		System.out.println("Count: " + liste.count());
		
		System.out.println("Result from removing 25: " + liste.remove(25));
		System.out.println("Result from removing 27: " + liste.remove(27));
		
		System.out.println("25 exists: " + liste.exists(25));
		System.out.println("27 exists: " + liste.exists(27));
		
		System.out.println("Count: " + liste.count());
		
		liste.showList();
		
	}
	
	private static interface Parser<E> {
		public E parse(String s);	//return null if no value
	}
	
	private static class IntegerParser implements Parser<Integer> {
		public Integer parse(String s) {
			if (s.toUpperCase().equals("MIN_VALUE")) return Integer.MIN_VALUE;
			if (s.toUpperCase().equals("MAX_VALUE")) return Integer.MAX_VALUE;
			try { return Integer.parseInt(s); } catch (NumberFormatException e) { return null; }
		}
	}
	
	private static class StringParser implements Parser<String> {
		public String parse(String s) { return s; }
	}

}
