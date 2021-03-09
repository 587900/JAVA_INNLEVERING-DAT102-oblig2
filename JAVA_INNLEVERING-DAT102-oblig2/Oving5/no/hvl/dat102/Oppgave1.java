package no.hvl.dat102;

public class Oppgave1 {
	public static int sum(int n) {
		int svar;
		if (n == 1) {
			svar = 1;
		} else {
			svar = n + sum(n - 1);
		}
		return svar;
	}

	public static int b(int n) {
		int svar;
		if (n == 0) {
			svar = 2;
		} else if (n == 1) {
			svar = 5;

		}else {
			svar = 5*b(n-1) - 6*b(n-2)+2;
		}
		return svar;
	}

	public static void main(String[] args) {
		System.out.println(sum(100));
		for (int i = 0; i<10; i++) {
			System.out.println(i + ": " + b(i));
		}

	}
}
