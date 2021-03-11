package no.hvl.dat102.oppg2;

public class DobbelNode<T> {
	private T element;
	private DobbelNode<T> neste;
	private DobbelNode<T> forrige;
	
	public DobbelNode(T element) {
		this.element = element;
		neste = null;
		forrige = null;
	}
	
	public T getElement() {
		return element;
	}
	
	public DobbelNode<T> getNext() {
		return neste;
	}
	
	public void setNext(DobbelNode <T> node) {
		neste = node;
	}
	
	public DobbelNode<T> getPrevious() {
		return forrige;
	}
	
	public void setPrevious(DobbelNode <T> node) {
		forrige = node;
	}

}