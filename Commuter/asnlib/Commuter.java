public class Commuter {
	private String name;
	private int miles;

	public Commuter(String name) {
		this.name = name;
		miles = 0;
	}

	public int getMiles() {
		return miles;
	}

	public void addMiles(int miles) {
		this.miles += miles;
	}

	public String toString() {
		return name + ": " + miles;
	}
}