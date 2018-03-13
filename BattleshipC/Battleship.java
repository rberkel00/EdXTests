public class Battleship extends Boat implements Attacker {

	public Battleship(int team, Coordinates location, int direction) {
		super(team, location, direction, 4, 3, 1);
	}

	public String getID() {
		return "B" + getTeam();
	}

	public String getActions() {
		return "Choose any of the following actions for the Battleship:\n" +
			"1. Move\n2. Turn left\n3. Turn right\n4. Attack";
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
		Boat o;
		if (w.isLocationOccupied(w.getAdjacentLocation(getLocation(), getDirection()))) {
			o = w.getOccupant(w.getAdjacentLocation(getLocation(), getDirection()));
			return "Fire cannons!\n" + o.takeHit(getStrength()) + "\n" + o.takeHit(getStrength());
		}
		return "There are no boats in range currently.";
	}
}