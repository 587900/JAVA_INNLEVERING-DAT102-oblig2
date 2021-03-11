package no.hvl.dat102;

/**
 * Transcribed from 
 * 'Java Software Structures - Designing and Using Data Structures, Fourth Edition by John Lewis & Joseph Chase'
 * Chapter 8.4, pages 231-232
 */
public class TowersOfHanoi {

	public static void main(String[] args) {
		TowersOfHanoi towers = new TowersOfHanoi(28);

		long start = System.currentTimeMillis();
		towers.solve();
		long end = System.currentTimeMillis();
		
		System.out.println("Solution took: " + (end - start) + "ms.");
	}
	
	public static long measureTime(int num) {
		TowersOfHanoi towers = new TowersOfHanoi(num);
		
		long start = System.currentTimeMillis();
		towers.solve();
		long end = System.currentTimeMillis();
		
		return end - start;
	}
	
	private int totalDisks;
	
	public TowersOfHanoi(int disks) {
		totalDisks = disks;
	}
	
	public void solve() {
		moveTower(totalDisks, 1, 3, 2);
	}
	
	private void moveTower(int numDisks, int start, int end, int temp) {
		if (numDisks == 1) moveOneDisk(start, end); else {
			moveTower(numDisks-1, start, temp, end);
			moveOneDisk(start, end);
			moveTower(numDisks-1, temp, end, start);
		}
	}
	
	private void moveOneDisk(int start, int end) {
		//System.out.println("Move one disk from " + start + " to " + end);
	}
	
}
