public class ParkingSpace {
	int spaceNum;
	char floor;
	boolean isCompact;
	boolean isOccupied;

	ParkingSpace(int sn, char f, boolean ic) {
		spaceNum = sn;
		floor = f;
		isCompact = ic;
		isOccupied = false;
	}

	public int getSpaceNum() {
		return spaceNum;
	}

	public char getFloor() {
		return floor;
	}

	public boolean getCompact() {
		return isCompact;
	}

	public boolean getOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean io) {
		isOccupied = io;
	}

	public String toString() {
		return floor + "" + spaceNum + " (" + (isCompact ? "C" : "N") +
			" " + (isOccupied ? "X" : "O") + ")";
	}

	public static void main(String[] args) {
		ParkingSpace ps = new ParkingSpace(2, 'A', true);
		System.out.println("1 " + ps.getOccupied());
		ps.setOccupied(true);
		System.out.println("2 " + ps.getOccupied());
		System.out.println("3 " + ps.getSpaceNum());
		System.out.println("4 " + ps.getFloor());
		System.out.println("5 " + ps.getCompact());
		System.out.println("6 " + ps.toString());
	}
}
