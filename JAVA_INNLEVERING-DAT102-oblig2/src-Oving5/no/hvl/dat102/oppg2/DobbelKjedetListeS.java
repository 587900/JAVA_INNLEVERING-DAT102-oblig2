package no.hvl.dat102.oppg2;

public class DobbelKjedetListeS<T extends Comparable<T>> {
	private DobbelNode<T> foerste;
	private DobbelNode<T> siste;
	private int antall;
	
	// Godkjenner ikke nullverdier
	public DobbelKjedetListeS(T minVerdi, T maksVerdi) {
		if (maksVerdi.compareTo(minVerdi) < 0) throw new IllegalArgumentException("minVerdi må vere mindre enn maksVerdi!");
		
		foerste = new DobbelNode<T>(minVerdi);
		siste = new DobbelNode<T>(maksVerdi);
		antall = 0;
		
		foerste.setNext(siste);
		siste.setPrevious(foerste);
	}

	public void add(T ny){
		if(ny == null) return;
		DobbelNode<T> current = foerste.getNext();
		while(current != null && ny.compareTo(current.getElement()) > 0) current = current.getNext();
		if(current == null) throw new IllegalArgumentException("Value greater than max.");
		
		DobbelNode<T> insert = new DobbelNode<>(ny);
		current.getPrevious().setNext(insert);
		insert.setPrevious(current.getPrevious());
		current.setPrevious(insert);
		insert.setNext(current);
		antall++;
	}

	public T remove(T x){
		DobbelNode<T> node = find(x);
		if(node == null) return null;
		
		DobbelNode<T> previous = node.getPrevious();
		DobbelNode<T> next = node.getNext();
		
		previous.setNext(next);
		next.setPrevious(previous);
		
		antall--;
		
		return node.getElement();
	}

	public boolean exists(T x){
		return find(x) != null;
	}
	
	public void showList(){
		String resultat = "";
		DobbelNode<T> current = foerste.getNext();
		while(current != null && current != siste) {
			resultat += current.getElement().toString() + "\t";
			current = current.getNext();
		}
		System.out.println(resultat);
	}
	
	public int count() {
		return antall;
	}
	
	private DobbelNode<T> find(T x){
		if (x == null) return null;
		if(count() == 0) return null;
		DobbelNode<T> current = foerste.getNext();
		while(current != null && current != siste) {
			if(x.equals(current.getElement())) return current;
			current = current.getNext();
		}
		return null;
	}
}