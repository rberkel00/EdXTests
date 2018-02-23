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
		paid = false;
	}

	public String toString() {
		return super.toString() + "- Monthly pass: $" + MONTHLY_RATE;
	}

}
