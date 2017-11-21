import java.util.Random;

public class Pirate {
     String name;
     int health;
     int swordsmanship;
     int agility;
     boolean hasParrot;
     boolean hasPegLeg;
     int doubloons;

     static int numberOfPirates;

     public Pirate() {
          this.name = "Blackbeard";
          this.health = this.swordsmanship = this.agility = 10;
          this.hasParrot = this.hasPegLeg = true;
          this.doubloons = 25;
     }

     /*** TODO: Write a method header for a method named "swashbuckle" that takes a
                Pirate "p" as a parameter and returns a String ***/
     String swashbuckle(Pirate p) {
          Random r = new Random();
          if (p.agility - (p.hasPegLeg?1:0) > r.nextInt(20)) {
               return p.name + " dodged yar attack!";
          } else {
               int damage = r.nextInt(this.swordsmanship + (p.hasParrot?1:0)) + 1;
               p.health -= damage;
               if (p.isNapping()) {
                    p.health = 0;
                    this.doubloons += p.doubloons;
                    numberOfPirates--;
               }
               return this.name + " swabs the deck with " + p.name + " for " + damage + "!";
          }
     }

     public boolean isNapping() {
          return this.health <= 0;
     }

     public static void main(String[] args) {
          Pirate p1 = new Pirate();
          Pirate p2 = new Pirate();
          System.out.println(p1.swashbuckle(p2));
     }
}
