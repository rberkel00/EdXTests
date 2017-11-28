public class ParkingLot {

	ParkingSpace[] parkingLot;
	int numSpaces;
	int numFloors;

	ParkingLot(int ns, int nf) {
		numSpaces = ns;
		numFloors = nf;
		parkingLot = new ParkingSpace[numSpaces*numFloors];
		int count = 0;
		for (int i = 0; i < numFloors; i++) {
			for (int j = 0; j < numSpaces; j++) {
				boolean compact = false;
				if (j < 4) compact = true;
				parkingLot[count] = new ParkingSpace(j, (char)(i +
					'A'), compact);
				count++;
			}
		}
	}

	ParkingSpace enterLot(boolean compact) {
		for (int i = 0; i < numSpaces * numFloors; i++) {
			if (!parkingLot[i].getOccupied()) {
				if (parkingLot[i].getCompact() && compact) {
					parkingLot[i].setOccupied(true);
					return parkingLot[i];
				} else if (!parkingLot[i].getCompact()) {
					parkingLot[i].setOccupied(true);
					return parkingLot[i];
				}
			}
		}
		return null;
	}

	void leaveLot(ParkingSpace ps) {
		parkingLot[ps.getSpaceNum() + numSpaces*(ps.getFloor()
			- 'A')].setOccupied(false);
	}

	public static void main(String[] args) {
		ParkingLot pl = new ParkingLot(5, 5);
		System.out.println("1 " + pl.parkingLot.length + "\n2 ");
		System.out.print("spaces ");
		for (int i = 0; i < pl.parkingLot.length; i++) {
			if (pl.parkingLot[i].getCompact()) {
				System.out.print(i + " ");
			}
		}
		System.out.println(" are compact");

		ParkingSpace ps1 = pl.enterLot(false);
		ParkingSpace ps2 = pl.enterLot(true);
		ParkingSpace ps3 = pl.enterLot(false);
		ParkingSpace ps4 = pl.enterLot(false);

		System.out.println("3 " + ps1.toString());
		System.out.println(ps2.toString());
		System.out.println(ps3.toString());
		System.out.println(ps4.toString());

		System.out.println("4");
		pl.leaveLot(ps1);
		System.out.println(ps1);



	}
}
