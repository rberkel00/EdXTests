public abstract class ScoutBoat extends Boat{
	public ScoutBoat(int team, Coordinates location, int direction, int health, int vision) {
		super(team, location, direction, health, 1, vision);
	}

	public String takeHit(int damage) {
		int strength = 0;
		if (Math.random() < 0.25) {
			strength = damage;
		}
		if (strength == 0) {
			return getID() + " has avoided the attack!";
		}
		return super.takeHit(damage);
	}
}
