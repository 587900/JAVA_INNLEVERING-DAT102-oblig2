package no.hvl.dat102.mengde;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class KjedetTest extends MengdeTest {

	@Override
	protected MengdeADT<Integer> getNewMengde() {
		return new KjedetMengde<>();
	}

}
