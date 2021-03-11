package no.hvl.dat102.oppg2;

public class DobbeltKjedetListeKlient {

	public static void main(String[] args) {
		
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

}
