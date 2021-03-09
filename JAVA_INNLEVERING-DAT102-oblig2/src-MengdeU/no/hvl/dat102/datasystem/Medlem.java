package no.hvl.dat102.datasystem;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
private String navn;
private int statusIndeks;
private KjedetMengde<Hobby> hobbyer;


public Medlem() {
	statusIndeks = -1;
	navn = "";
	hobbyer = new KjedetMengde<Hobby>();
}

public Medlem(String navn, KjedetMengde<Hobby> hobbyer, int status) {
	this.navn = navn;
	this.hobbyer=hobbyer;
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
public KjedetMengde<Hobby> getHobbyer() {
	return hobbyer; 
}
public void setHobbyer(KjedetMengde<Hobby> hobbyer) {
	this.hobbyer = hobbyer;
}
public int getStatusIndeks() {
	return statusIndeks;
}
public void setStatusIndeks(int statusIndeks) {
	this.statusIndeks = statusIndeks;
}
public String toString() {
	return navn+ " " + hobbyer.toString() + "StatusIndeks: " + statusIndeks;
}
}

