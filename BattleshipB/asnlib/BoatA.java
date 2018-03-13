public class BoatA extends Boat{
  public BoatA(int team, Coordinates location, int direction, int health, int strength, int vision) {
    super(team, location, direction, health, strength, vision);
  }

  public String getID() {
    return "BA";
  }

  public String act(int[] choices, World world){
    return "blah";
  }

  public String getActions(){
    return "blah";
  }

  public static void main(String[] args) {
    Coordinates c = new Coordinates(2, 3);
    Coordinates d = new Coordinates();
    if (d.getX() == 0 && d.getY() == 0) System.out.println("Pass 20");
    if (c.toString().equals("D3")) System.out.println("Pass 31");
    World w = new World(2, 5);
    if (w.getHeight() == 5) System.out.println("Pass 30");
    if (w.getWidth() == 4) System.out.println("Pass 30");
    if (w.isLocationValid(new Coordinates(0,0))) System.out.println("Pass 40");
    if (!w.isLocationValid(new Coordinates(2,6))) System.out.println("Pass 40");
    if (!w.isLocationValid(new Coordinates(5,2))) System.out.println("Pass 40");
    BoatA a = new BoatA(1, new Coordinates(0,0), 0, 9, 5, 6);
    if (!w.setOccupant(a, new Coordinates(0,7))) System.out.println("Pass 50");
    if (w.setOccupant(a, new Coordinates(0,0))) System.out.println("Pass 50");
    if (!w.setOccupant(a, new Coordinates(0,0))) System.out.println("Pass 50");
    Coordinates e = w.getAdjacentLocation(new Coordinates(0,0), w.SOUTHEAST);
    if (e.getX() == 1 && e.getY() == 1) System.out.println("Pass 60");
    Coordinates f = w.getAdjacentLocation(new Coordinates(0,0), w.NORTHWEST);
    if (f == null) System.out.println("Pass 70");
    BoatA[] barray = new BoatA[2];
    barray[0] = new BoatA(1, new Coordinates(3,3), 2, 9, 5, 6);
    barray[1] = new BoatA(1, new Coordinates(2,2), 2, 9, 5, 6);
    String map1 = w.drawTeamMap(barray, 1);
    String map2 = w.drawTeamMap(barray, 2);
    String map3 = w.drawTeamMap(barray, 3);
    if (map1.matches("(?s).*############.*############.*############.*############.*############.*")) System.out.println("Pass 80");
    if (map2.matches("(?s).*.BA~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*")) System.out.println("Pass 90");
    if (map3.matches("(?s).*.BA~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*~~~~~~~~~~~~.*")) System.out.println("Pass 21");
    if (a.move(w).matches("(?s).*cannot.*off.*map.*")) System.out.println("Pass 41");
    BoatA b = new BoatA(1, new Coordinates(0,1), w.NORTH, 9, 5, 6);
    w.setOccupant(b, new Coordinates(0,1));
    if (b.move(w).matches("(?s).*cannot.*occupied.*")) System.out.println("Pass 51");
    BoatA z = new BoatA(1, new Coordinates(3,3), w.NORTHWEST, 9, 5, 6);
    if (z.move(w).matches("(?s).*moves.*from.*")) System.out.println("Pass 61");
    z.turn(1);
    if (z.getDirection() == 0) System.out.println("Pass 71");
    if (z.takeHit(1).matches("(?s).*takes.*1.*damage.*")) System.out.println("Pass 81");
    if (z.takeHit(10).matches("(?s).*sunk.*")) System.out.println("Pass 81");
  }
}
