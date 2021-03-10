package no.hvl.dat102.datasystem;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	
	private String navn;
	private int statusIndeks;
	private MengdeADT<Hobby> hobbyer;

	public Medlem() { this(""); }

	public Medlem(String navn) { this(navn, new KjedetMengde<>(), -1); }
	
	public Medlem(String navn, MengdeADT<Hobby> hobbyer, int status) {
		this.navn = navn;
		this.hobbyer = hobbyer;
		statusIndeks = status;

	}

	public boolean passerTil(Medlem medlem2) {
		return this.getHobbyer().equals(medlem2.getHobbyer());
	}

	public String getNavn(String navn) {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}

	public void setHobbyer(MengdeADT<Hobby> hobbyer) {
		this.hobbyer = hobbyer;
	}

	public int getStatusIndeks() {
		return statusIndeks;
	}

	public void setStatusIndeks(int statusIndeks) {
		this.statusIndeks = statusIndeks;
	}

	public void addHobby(Hobby hobby) {
		hobbyer.leggTil(hobby);
	}

	@Override
	public String toString() {
		return navn + " " + hobbyer.toString() + "StatusIndeks: " + statusIndeks;
	}
}
