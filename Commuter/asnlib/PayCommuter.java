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

	public static void main(String[] args) {
		PayCommuter pc = new PayCommuter("abc", 4.99);
		pc.payFare(4);
		pc.addMiles(5);
		System.out.println("check1: " + pc.getPaid() + " " + pc.getMiles());
		pc.payFare(5);
		System.out.println("check2: " + pc.getPaid() + " " + pc.getMiles());
		pc.addMiles(5);
		System.out.println("check3: " + pc.getPaid() + " " + pc.getMiles());
	}
}
