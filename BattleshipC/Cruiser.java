public class Cruiser extends ScoutBoat {
	public Cruiser(int team, Coordinates location, int direction) {
		super(team, location, direction, 3, 3);
	}

	public String getID() {
		return "C" + getTeam();
	}

	public String getActions() {
		return "Choose any of the following actions for the Cruiser:" +
		"\n1. Move\n2. Turn left\n3. Turn right";
	}

	public String act(int[] choices, World w) {
		String results = "";
		for (int i = 0; i < choices.length; i++) {
			if (choices[i] == 1) {
				results += move(w) + "\n";
				move(w);
			} else if (choices[i] == 2) {
				results += turn(-1) + "\n";
			} else {
				results += turn(1) + "\n";
			}
		}
		return results;
	}


}