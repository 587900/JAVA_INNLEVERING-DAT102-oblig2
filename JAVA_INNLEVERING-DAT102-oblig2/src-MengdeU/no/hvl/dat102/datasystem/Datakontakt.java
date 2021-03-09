package no.hvl.dat102.datasystem;

public class Datakontakt {
	private Medlem[] medlemstabell;
	private int antallMedlemmer;
	
	public Datakontakt() {
		antallMedlemmer = 0;
	}
	
	public void leggTilMedlem(Medlem person) {
		medlemstabell[antallMedlemmer] = person;
		antallMedlemmer++;
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
		boolean funnet = false;
		int indeks = -1;
		
		for(int i = 0; i < antallMedlemmer && !funnet; i++) {
			if(medlemsnavn.equals(medlemstabell[i].getNavn(null))) {
				indeks = i;
				funnet = true;
			}
		}
		return indeks;
	}
	public int finnPartnerFor(String medlemsnavn) {
		int indeksp = -1;
		int indeksm = finnMedlemIndeks(medlemsnavn);
		boolean funnet = false;
		if (indeksm != -1) {
			for(int i = 0; indeksp < antallMedlemmer && !funnet; i++) {
				if (medlemstabell[i].getStatusIndeks() == -1 && medlemstabell[indeksm].passerTil(medlemstabell[i])) {
					medlemstabell[indeksm].setStatusIndeks(i);
					medlemstabell[i].setStatusIndeks(indeksm);
					indeksp = i;
					funnet = true;
				}
			}
		}
		return indeksp;
	}
	public void tilbakestillStatusIndeks(String medlemsnavn) {
		int m = finnMedlemIndeks(medlemsnavn);
		int i = medlemstabell[m].getStatusIndeks();
		if (i != -1)
			medlemstabell[i].setStatusIndeks(-1);
		medlemstabell[m].setStatusIndeks(-1);
		
		}
	
}

