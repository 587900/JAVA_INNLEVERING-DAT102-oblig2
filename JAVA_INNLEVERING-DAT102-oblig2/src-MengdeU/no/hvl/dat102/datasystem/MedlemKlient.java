package no.hvl.dat102.datasystem;

public class MedlemKlient {

	public static void main(String[] args) {
		
		Medlem lars = new Medlem("Lars");
		Medlem bodil = new Medlem("Bodil");
		Medlem kari = new Medlem("Kari");
		Medlem xavier = new Medlem("Xavier");
		
		Hobby h1 = new Hobby("Gaming");
		Hobby h2 = new Hobby("Polo");
		Hobby h3 = new Hobby("Frimerkesleiking");
		Hobby h4 = new Hobby("Gresskjemming");
		Hobby h5 = new Hobby("Vindusvasking");
		Hobby h6 = new Hobby("Pandaklapping");
		Hobby h7 = new Hobby("Stoltesting");
		
		lars.addHobby(h1);
		lars.addHobby(h2);
		lars.addHobby(h6);

		bodil.addHobby(h3);
		bodil.addHobby(h5);
		bodil.addHobby(h6);
		
		kari.addHobby(h4);
		kari.addHobby(h7);
		
		xavier.addHobby(h3);
		xavier.addHobby(h5);
		xavier.addHobby(h6);
		
		System.out.println(lars.toString());
		System.out.println(bodil.toString());
		System.out.println(kari.toString());
		System.out.println(bodil.toString());
		System.out.println("******");
		
		System.out.println(bodil.passerTil(xavier));
		System.out.println(kari.passerTil(lars));
		System.out.println(xavier.passerTil(bodil));
	}

}
