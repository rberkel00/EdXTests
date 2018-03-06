public abstract class Boat {
 private int team;
 private Coordinates location;
 private int direction;
 private int health;
 private int strength;
 private int vision;

 public Boat(int team, Coordinates location, int direction, int health, int strength, int vision) {
   this.team = team;
   this.location = location;
   this.direction = direction;
   this.health = health;
   this.strength = strength;
   this.vision = vision;
 }

 public int getTeam() {
   return team;
 }

 public Coordinates getLocation() {
   return location;
 }

 public int getDirection() {
   return direction;
 }

 public int getHealth() {
   return health;
 }

 public int getStrength() {
   return strength;
 }

 public int getVision() {
   return vision;
 }

 public abstract String getID();

 public abstract String act(int[] choices, World world);

 public abstract String getActions();

 public String move(World w) {
   if (w.getAdjacentLocation(location, direction) == null) {
     return getID() + " cannot move off the map.";
   }
   if (w.isLocationOccupied(w.getAdjacentLocation(location, direction))) {
     return getID() + " cannot move to " + w.getAdjacentLocation(location, direction).toString() + " as it is occupied.";
   }
   Coordinates temp = location;
   location = w.getAdjacentLocation(location, direction);
   w.empty(temp);
   w.setOccupant(this, location);
   return (getID() + " moves from " + temp.toString() + " to " + location.toString() + ".");
 }

 public String turn(int dir) {
   String str = getID() + " turned ";
   if (dir == 1) str += "right, now facing ";
   if (dir == -1) str += "left, now facing ";
   direction += dir;
   if (direction == -1) direction = 7;
   if (direction == 8) direction = 0;
   if (direction == 0) str += "N";
   if (direction == 1) str += "NE";
   if (direction == 2) str += "E";
   if (direction == 3) str += "SE";
   if (direction == 4) str += "S";
   if (direction == 5) str += "SW";
   if (direction == 6) str += "W";
   if (direction == 7) str += "NW";
   return str;
 }

 public String takeHit(int hit) {
   health -= hit;
   if (health <= 0) {
     health = 0;
     return getID() + " has been sunk!";
   }
   return getID() + " takes " + hit + " damage.";
 }

 public void setLocation(Coordinates location) {
   this.location = location;
 }

 public String toString() {
   return getID();
 }
}
