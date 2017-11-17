import java.util.Random;

public class Pirate {
	String name;
	int health;
	int swordsmanship;
	int agility;
	boolean	hasParrot;
	boolean	hasPegLeg;
	int doubloons;

	static int numberOfPirates = 0;

	public Pirate (String name, int swordsmanship, int agility, boolean hasParrot, boolean hasPegLeg) {
		this.name = name;
		this.swordsmanship = swordsmanship;
		this.agility = agility;
		this.hasParrot = hasParrot;
		this.hasPegLeg = hasPegLeg;
		health = 10;
		doubloons = 25;
		numberOfPirates++;
	}

	public Pirate (String name) {
		this.name = name;
		Random r = new Random();
		this.swordsmanship = r.nextInt(10) + 1;
		this.agility = r.nextInt(10) + 1;
		health = r.nextInt(10) + 1;
		doubloons = r.nextInt(10) + 1;
		this.hasParrot = (r.nextDouble() < 0.25);
		this.hasPegLeg = (r.nextDouble() < 0.10);
		numberOfPirates++;
	}

	public boolean isNapping() {
		if (health <= 0) return true;
		return false;
	}

	public String toString() {
		String pegleg = "does not have";
		String parrot = "does not have";
		if (hasPegLeg) pegleg = "has";
		if (hasParrot) parrot = "has";
		return name + " (H: " + health + ", S: " + swordsmanship + ", A: " + agility + ", $" + doubloons +
			")\nThis pirate " + parrot + " a parrot and " + pegleg + " a pegleg.";
	}

	public static void main(String[] args) {
		Pirate p = new Pirate("Robyn");
		System.out.println(p.toString());
    System.out.println(p.isNapping());
	}
}
