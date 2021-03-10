package no.hvl.dat102.datasystem;

public class Hobby {
	
	private String hobbyNavn;

	public Hobby(String hobby) {
		hobbyNavn = hobby;
	}

	public String getHobbyNavn() {
		return hobbyNavn;
	}

	public void setHobbyNavn(String hobbyNavn) {
		this.hobbyNavn = hobbyNavn;
	}

	@Override
	public boolean equals(Object hobby2) {
		if (!(hobby2 instanceof Hobby)) return false;
		
		Hobby hobbyDenAndre = (Hobby) hobby2;
		return (hobbyNavn.equals(hobbyDenAndre.getHobbyNavn()));
	}
	
	@Override
	public String toString() {
		return "<" + hobbyNavn + ">";
	}

}
