public class Destroyer extends Boat implements Attacker {
	
	public Destroyer(int team, Coordinates location, int direction) {
		super(team, location, direction, 2, 2, 1);
	}

	public String getID() {
		return "D" + getTeam();
	}

	public String getActions() {
		return "Choose any of the following actions for the Destroyer:\n" +
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
				results += turn(1);
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
			return o.takeHit(getStrength());
		}
		return "There are no boats in range currently.";
	}

	public String takeHit(int damage) {
		if (Math.random() > 0.5) {
			return super.takeHit(damage);
		}
		return getID() + " avoids the attack!";
	}
}