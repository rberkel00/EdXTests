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
		PayCommuter pc;
		BusCommuter bc;
		System.out.println((pc = new PayCommuter("Banana", 3.50)));
		System.out.println((bc = new BusCommuter("Orange", 1.55, 23.45)));
		System.out.println(pc.getPaid());
		System.out.println(pc.getFare());
		pc.addMiles(10);
		System.out.println(pc.toString());
		pc.payFare(5.50);
		pc.addMiles(10);
		System.out.println(pc.toString());
		System.out.println(bc.getPaid());
		bc.buyBusPass(22.0);
		bc.addMiles(10);
		System.out.println(bc.toString());
		bc.buyBusPass(24.0);
		bc.addMiles(10);
		System.out.println(bc.toString());
	}
}