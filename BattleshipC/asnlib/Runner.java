public class Runner {
  public static void main(String[] args) {
    World w = new World(10,10);
    AircraftCarrier a = new AircraftCarrier(1, new Coordinates(1,1), 2);
    Battleship b = new Battleship(1, new Coordinates(2,2), 2);
    Cruiser c = new Cruiser(1, new Coordinates(3,3), 2);
    Destroyer d = new Destroyer(1, new Coordinates(4,4), 2);
    Submarine s = new Submarine(1, new Coordinates(5,5), 2, 3);

    if (a.getActions().matches("(?s).*Move.*urn .eft.*urn .ight.*aunch .lanes.*")) System.out.println("01 passed");
    int[] i = {1,2,3,4};
    if (a.act(i, w).matches("(?s).*.*")
      && a.getLocation().getX() == 2
      && a.getLocation().getY() == 1
      && a.getDirection() == 2) System.out.println("02 passed");
    if (a.getID().equals("A1")) System.out.println("03 passed");

    if (b.attack(w).matches("(?s).*no boats in range.*")) System.out.println("04 passed");
    int[] j = {1};
    c.act(j, w);
    if (c.getLocation().getX() == 5 && c.getLocation().getY() == 3) System.out.println("05 passed");
    
    if (d.attack(w).matches("(?s).*no boats in range.*")) System.out.println("06 passed");

    if (s.attack(w).matches("(?s).*no boats in range.*")) System.out.println("07 passed");

    if (s.submerge(w).matches("(?s).*moves from.*")) System.out.println("08 passed");
  }
}
