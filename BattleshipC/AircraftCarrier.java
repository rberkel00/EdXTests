import java.util.ArrayList;
public class AircraftCarrier extends Boat implements Attacker{
	private boolean hasPlanes;

	public AircraftCarrier(int team, Coordinates location, int direction) {
		super(team, location, direction, 5, 1, 1);
		hasPlanes = true;
	}

	public String getID() {
		return "A" + getTeam();
	}

	public String getActions() {
		return "Choose any of the following actions for the Aircraft Carrier:\n" +
			"1. Move\n2. Turn left\n3. Turn right" + 
			(hasPlanes ? "\n4. Launch planes" : "");
	}

	public String act(int[] choices, World w) {
		String results = "";
		for (int i = 0; i < choices.length; i++) {
			if (choices[i] == 1) {
				results += move(w) + "\n";
			} else if (choices[i] == 2) {
				results += turn(-1) + "\n";
			} else if (choices[i] == 3) {
				results += turn(1) + "\n";
			} else {
				results += attack(w) + "\n";
			}
		}
		return results;
	}

	public String attack(World w) {
		ArrayList<Boat> b = new ArrayList<Boat>();
		Coordinates c;
		String results = "";
		for (int i = getLocation().getX() - getVision(); i <= getLocation().getX() + getVision(); i++) {
			for (int j = getLocation().getY() - getVision(); j <= getLocation().getY() + getVision(); j++) {
				c = new Coordinates(i, j);
				if (w.isLocationValid(c)) {
					if (w.isLocationOccupied(c)) {
						b.add(w.getOccupant(c));
					}
				}
			}
		}
		if (!hasPlanes) {
			return results + "\n" + getID() + " has no planes remaining.";
		}
		if (b.size() == 0) {
			return "There are no boats in range currently.";
		}
		while (hasPlanes && b.size() > 0) {
			results += "Air raid! " + b.get(0).takeHit(getStrength()) + "\n";
			b.remove(0);
			if (Math.random() > 0.8) hasPlanes = false;
		}
		if (!hasPlanes) {
			return results + "\nThe planes have been destroyed.";
		}
		return results;
	}
}