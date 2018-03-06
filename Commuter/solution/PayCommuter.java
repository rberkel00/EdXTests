public class PayCommuter extends Commuter {
	final double FARE;
	boolean paid;

	public PayCommuter(String name, double fare) {
		super(name);
		FARE = fare;
	}

	public boolean getPaid() {
		return paid;
	}

	public double getFare() {
		return FARE;
	}

	public void payFare(double amount) {
		if (amount >= FARE) {
			paid = true;
		}
	}

	public void addMiles(int miles) {
		if (paid) {
			super.addMiles(miles);
		}
		paid = false;
	}

	public String toString() {
		return super.toString() + "- Single ride: $" + FARE;
	}

}
