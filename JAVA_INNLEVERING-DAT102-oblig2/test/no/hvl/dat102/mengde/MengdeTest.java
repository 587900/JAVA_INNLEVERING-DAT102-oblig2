package no.hvl.dat102.mengde;
import static org.junit.Assert.*;
import org.junit.Test;

import no.hvl.dat102.mengde.kjedet.*;
import no.hvl.dat102.mengde.tabell.*;
import org.junit.Before;

public class MengdeTest {
	
	private TabellMengde<Integer> m1;

	@Before
	public final void setup() {
		m1 = new TabellMengde<Integer>();
		m1.leggTil(1);
		m1.leggTil(2);
        m1.leggTil(3);
		m1.leggTil(4);
	}

	@Test
	public final void unionTest() {

		TabellMengde<Integer> m2 = new TabellMengde<Integer>();
		m2.leggTil(5);
		m2.leggTil(6);

		TabellMengde<Integer> f = new TabellMengde<Integer>();
		for (int i = 1; i < 7; i++)
			f.leggTil(i);

		assertTrue(f.equals(m2.union(m1)));
	}

	@Test
	public final void snittTest() {
		TabellMengde<Integer> m2 = new TabellMengde<Integer>();
		m2.leggTil(1);
		m2.leggTil(6);

		TabellMengde<Integer> f = new TabellMengde<Integer>();
		f.leggTil(1);

		assertTrue(f.equals(m2.snitt(m1)));

	}

	@Test
	public final void differensTest() {
		TabellMengde<Integer> m2 = new TabellMengde<Integer>();
		m2.leggTil(1);
		m2.leggTil(2);

		TabellMengde<Integer> f = new TabellMengde<Integer>();
		f.leggTil(4);
		f.leggTil(3);

		assertTrue(f.equals(m2.differens(m1)));
	}

private KjedetMengde<Integer> m1;

@Before
public final void setup() {
	m1 = new KjedetMengde<Integer>();
	m1.leggTil(1);
	m1.leggTil(2);
	m1.leggTil(3);
	m1.leggTil(4);
}

@Test
public final void unionTest() {

	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(5);
	m2.leggTil(6);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();
	for (int i = 1; i < 7; i++)
		f.leggTil(i);

	assertTrue(f.equals(m2.union(m1)));
}

@Test
public final void unionTestFelles() {

	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(1);
	m2.leggTil(2);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();
	for (int i = 1; i < 5; i++)
		f.leggTil(i);

	assertTrue(f.equals(m2.union(m1)));
}

@Test
public final void snittTest() {
	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(1);
	m2.leggTil(6);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();
	f.leggTil(1);

	assertTrue(f.equals(m2.snitt(m1)));

}

@Test
public final void snittTestDis() {
	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(5);
	m2.leggTil(6);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();

	assertTrue(f.equals(m2.snitt(m1)));

}

@Test
public final void differensTest() {
	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(1);
	m2.leggTil(2);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();
	f.leggTil(4);
	f.leggTil(3);

	assertTrue(f.equals(m2.differens(m1)));
}

@Test
public final void differensTestDis() {
	KjedetMengde<Integer> m2 = new KjedetMengde<Integer>();
	m2.leggTil(5);
	m2.leggTil(6);

	KjedetMengde<Integer> f = new KjedetMengde<Integer>();
	f.leggTilAlle(m1);

	assertTrue(f.equals(m2.differens(m1)));
}
		
	}
