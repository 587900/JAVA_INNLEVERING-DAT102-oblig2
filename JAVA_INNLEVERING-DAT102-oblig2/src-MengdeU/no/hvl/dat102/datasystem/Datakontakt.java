package no.hvl.dat102.datasystem;

public class Datakontakt {

	private Medlem[] medlemstabell;
	private int antallMedlemmer;

	public Datakontakt() {
		this(0);
	}

	public Datakontakt(int antall) {
		medlemstabell = new Medlem[antall];
		antallMedlemmer = 0;
	}

	public void leggTilMedlem(Medlem person) {
		if (antallMedlemmer >= medlemstabell.length) 
			utvidTabell((int)Math.max(1, medlemstabell.length * 1.1)); 
		medlemstabell[antallMedlemmer++] = person;
	}

	public Medlem[] getMedlemstabell() {
		return medlemstabell;
	}

	public void setMedlemstabell(Medlem[] medlemstabell) {
		this.medlemstabell = medlemstabell;

	}

	public int getAntallMedlemmer() {
		return antallMedlemmer;

	}

	public void setAntallMedlemmer(int antallMedlemmer) {
		this.antallMedlemmer = antallMedlemmer;
	}

	public int finnMedlemIndeks(String medlemsnavn) {
		for (int i = 0; i < antallMedlemmer; i++) {
			if (medlemstabell[i].getNavn().equals(medlemsnavn))
				return i;
		}
		return -1;
	}

	public int finnPartnerFor(String medlemsnavn) {
		int indeks = finnMedlemIndeks(medlemsnavn);
		if (indeks == -1) return -1;
		
		Medlem oss = medlemstabell[indeks];
		if (oss.getStatusIndeks() != -1) return oss.getStatusIndeks();
		
		for (int i = 0; i < antallMedlemmer; i++) {
			if (i == indeks) continue; // Skipper oss selv
			if (medlemstabell[i].getStatusIndeks() != -1) continue; // Skipper allerede matchede
			
			if (oss.passerTil(medlemstabell[i])) {
				oss.setStatusIndeks(i);
				medlemstabell[i].setStatusIndeks(indeks);
				return i;
			}
		}
		
		return -1;
	}

	public void tilbakestillStatusIndeks(String medlemsnavn) {
		
		int indeks = finnMedlemIndeks(medlemsnavn);
		if (indeks == -1) return;
		
		int match = medlemstabell[indeks].getStatusIndeks();
		if (match == -1) return;
		
		medlemstabell[indeks].setStatusIndeks(-1);
		medlemstabell[match].setStatusIndeks(-1);

	}
	
	public void utvidTabell(int med) {
		if (med <= 0) return;
		Medlem[] temp = new Medlem[medlemstabell.length+med];
		for (int i = 0; i < medlemstabell.length; i++) temp[i] = medlemstabell[i];
		medlemstabell = temp;
	}

}
