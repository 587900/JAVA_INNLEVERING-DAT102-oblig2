package no.hvl.dat102.mengde;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.tabell.TabellMengde;

public class TabellTest extends MengdeTest {

	@Override
	protected MengdeADT<Integer> getNewMengde() {
		return new TabellMengde<>();
	}
	

}
