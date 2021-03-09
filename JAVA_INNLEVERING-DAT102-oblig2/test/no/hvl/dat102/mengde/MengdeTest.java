package no.hvl.dat102.mengde;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import no.hvl.dat102.mengde.adt.MengdeADT;

public abstract class MengdeTest {

	private MengdeADT<Integer> m1;
	private MengdeADT<Integer> m2;

	protected abstract MengdeADT<Integer> getNewMengde();

	@BeforeEach
	public final void setup() {

		m1 = getNewMengde();
		m2 = getNewMengde();

		for (int i = 3; i <= 8; i++) m1.leggTil(i);

	}

	@Test
	public final void unionLikTest() {
		
		for (int i = 3; i <= 8; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		for (int i = 3; i <= 8; i++) fasit.leggTil(i);
		
		assertTrue(fasit.equals(m1.union(m2)));
		assertTrue(fasit.equals(m2.union(m1)));
	}

	@Test
	public final void unionUlikTest1() {

		for (int i = 1; i <= 5; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		for (int i = 3; i <= 8; i++) fasit.leggTil(i);
		
		assertFalse(fasit.equals(m1.union(m2)));
		assertFalse(fasit.equals(m2.union(m1)));

	}

	@Test
	public final void unionUlikTest2() {

		for (int i = 1; i <= 5; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		for (int i = 1; i <= 8; i++) fasit.leggTil(i);
		
		assertTrue(fasit.equals(m1.union(m2)));
		assertTrue(fasit.equals(m2.union(m1)));

	}

	
	@Test
	public final void snittLikTest() {
		for (int i = 3; i <= 8; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		for (int i = 3; i <= 8; i++) fasit.leggTil(i);

		assertTrue(fasit.equals(m1.snitt(m2)));
		assertTrue(fasit.equals(m2.snitt(m1)));

	}

	@Test
	public final void snittUlikTest1() {
		for (int i = 9; i <= 15; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();

		assertTrue(fasit.equals(m1.snitt(m2)));
		assertTrue(fasit.equals(m2.snitt(m1)));
		assertTrue(m1.snitt(m2).erTom());
		assertTrue(m2.snitt(m1).erTom());

	}

	@Test
	public final void snittUlikTest2() {
		for (int i = 1; i <= 5; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		for (int i = 3; i <= 5; i++) fasit.leggTil(i);
		
		assertTrue(fasit.equals(m1.snitt(m2)));
		assertTrue(fasit.equals(m2.snitt(m1)));

	}
	
	@Test
	public final void differensLikTest() {
		for (int i = 3; i <= 8; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit = getNewMengde();
		
		assertTrue(fasit.equals(m1.differens(m2)));
		assertTrue(fasit.equals(m2.differens(m1)));
		assertTrue(m1.differens(m2).erTom());
		assertTrue(m2.differens(m1).erTom());

	}

	@Test
	public final void differensUlikTest1() {
		for (int i = 9; i <= 15; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit1 = getNewMengde();
		for (int i = 3; i <= 8; i++) fasit1.leggTil(i);
		
		MengdeADT<Integer> fasit2 = getNewMengde();
		for (int i = 9; i <= 15; i++) fasit2.leggTil(i);
		
		assertTrue(fasit1.equals(m1.differens(m2)));
		assertTrue(fasit2.equals(m2.differens(m1)));
		
	}
	
	@Test
	public final void differensUlikTest2() {
		for (int i = 1; i <= 5; i++) m2.leggTil(i);

		MengdeADT<Integer> fasit1 = getNewMengde();
		for (int i = 6; i <= 8; i++) fasit1.leggTil(i);
		
		MengdeADT<Integer> fasit2 = getNewMengde();
		for (int i = 1; i <= 2; i++) fasit2.leggTil(i);
		
		assertTrue(fasit1.equals(m1.differens(m2)));
		assertTrue(fasit2.equals(m2.differens(m1)));
		
	}

}
