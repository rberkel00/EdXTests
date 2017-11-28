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
}
