public class BusCommuter extends PayCommuter {
	final double MONTHLY_RATE;
	boolean busPass;

	public BusCommuter(String name, double fare, double monthly_rate) {
		super(name, fare);
		MONTHLY_RATE = monthly_rate;
		busPass = false;
	}

	public void buyBusPass(double amount) {
		if (amount > MONTHLY_RATE) {
			busPass = true;
		}
	}

	public void addMiles(int miles) {
		if (busPass) {
			super.payFare(super.getFare());
			super.addMiles(miles);
		}
	}

	public String toString() {
		return super.toString() + "- Monthly pass: $" + MONTHLY_RATE;
	}

	public static void main(String[] args) {
		BusCommuter bc = new BusCommuter("Orange", 1.55, 23.45);
		bc.buyBusPass(22.0);
		bc.addMiles(10);
		System.out.println("check1: " + bc.getMiles());
		bc.buyBusPass(24.0);
		bc.addMiles(10);
		System.out.println("check2: " + bc.getMiles());
	}
}
