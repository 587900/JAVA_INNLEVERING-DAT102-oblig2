package no.hvl.dat102;

public class Oppgave1 {
	
	public static int a(int n) {
		//if (n <= 0) return 0;
		if (n == 1) return 1;
		return a(n-1) + n;
	}

	public static int b(int n) {
		//if (n < 0) return 0;
		if (n == 0) return 2;
		if (n == 1) return 5;
		
		return 5*b(n-1) - 6*b(n-2) + 2;
	}
	
	//ekstra, ubrukt
	public static int c(int n) {
		//if (n <= 0) return 0;
		if (n == 1) return 1;
		return 2*c(n-1)+1;
	}

	public static void main(String[] args) {
		//oppg a
		System.out.println("oppg a): " + a(100));
		
		//oppg b
		for (int i = 0; i<10; i++) {
			System.out.println("oppg b_" + i + "): " + b(i));
		}
		
		//oppg c
		for (int i = 28; i < 32; i++) {
			//Tallverdier skal doble seg, men grunnet OS-caching, etc,
			//kan verdier vere urepresentative av virkelig tidsforbruk.
			//I testing individuelt: 28: 478ms, 29: 757ms, 30: 1860ms, 31: 3069ms
			System.out.println("oppg c_" + i + "): " + TowersOfHanoi.measureTime(i) + "ms.");
		}
		
		System.out.println("Program finished");

	}
}
