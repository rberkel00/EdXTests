import java.util.Random;

public class Submarine extends ScoutBoat implements Attacker {
	private int numOfTorpedoes;

	public Submarine(int team, Coordinates object, int direction, int num) {
		super(team, object, direction, 3, 2);
		numOfTorpedoes = num;
	}

	public String getID() {
		return "S" + getTeam();
	}

	public String getActions() {
		return "Choose any of the following actions for the Submarine:\n" +
			"1. Move\n2. Turn left\n3. Turn right\n4. Submerge" + (numOfTorpedoes == 0 ? "\n5. Fire torpedoes" : "");
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
			} else if (choices[i] == 4) {
				results += submerge(w) + "\n";
			} else {
				results += attack(w) + "\n";
			}
		}
		return results;
	}

	public String attack(World w) {
		Boat o;
		Random r = new Random();
		if(numOfTorpedoes > 0) {
			numOfTorpedoes--;
			if (w.isLocationOccupied(w.getAdjacentLocation(getLocation(), getDirection()))) {
				o = w.getOccupant(w.getAdjacentLocation(getLocation(), getDirection()));
				return "Fire torpedoes!\n" + o.takeHit(r.nextInt(o.getHealth() - 1) + 1);
			} else if (w.isLocationOccupied(w.getAdjacentLocation(w.getAdjacentLocation(getLocation(), getDirection()), getDirection()))) {
				o = w.getOccupant(w.getAdjacentLocation(w.getAdjacentLocation(getLocation(), getDirection()), getDirection()));
				return "Fire torpedoes!\n" + o.takeHit(r.nextInt(o.getHealth() - 1) + 1);
			} else {
				return "There are no boats in range currently";
			}

		}
		return getID() + " has no torpedoes remaining.";
	}

	public String submerge(World w) {
		int free = 0;
		Coordinates c;
		for (int i = getLocation().getX() - getVision(); i <= getLocation().getX() + getVision(); i++) {
			for (int j = getLocation().getY() - getVision(); j <= getLocation().getY() + getVision(); j++) {
				c = new Coordinates(i, j);
				if (w.isLocationValid(c)) {
					if (!w.isLocationOccupied(c)) {
						free++;
					}
				}
			}
		}
		Random r = new Random();
		free = r.nextInt(free);
		for (int i = getLocation().getX() - getVision(); i <= getLocation().getX() + getVision(); i++) {
			for (int j = getLocation().getY() - getVision(); j <= getLocation().getY() + getVision(); j++) {
				c = new Coordinates(i, j);
				if (w.isLocationValid(c)) {
					if (!w.isLocationOccupied(c)) {
						if (free == 0) {
							Coordinates temp = getLocation();
							setLocation(c);
							w.empty(temp);;
							w.setOccupant(this, getLocation());
							return (getID() + " moves from " + temp.toString() + " to " + getLocation().toString() + ".");
						}
						free--;
					}
				}
			}
		}
		return "No open locations.";
	}
}