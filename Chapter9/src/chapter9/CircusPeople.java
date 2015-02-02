package chapter9;

public class CircusPeople implements Comparable<CircusPeople> {
	private int height;
	private int weight;
	
	public CircusPeople(int h, int w) {
		height = h;
		weight = w;
	}
	
	@Override
	public int compareTo(CircusPeople p) {
		if(this.height != p.height) {
			return ((Integer) this.height).compareTo(p.height);
		} else {
			return ((Integer) this.weight).compareTo(p.weight);
		}
	}
	
	public String toString() {
		return "(" + height + ", " + weight + ")";
	}
	
	public boolean isBefore(CircusPeople p) {
		return this.height < p.height && this.weight < p.weight;
	}
	
}
