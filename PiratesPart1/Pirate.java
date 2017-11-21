public class Pirate {
     /*** TODO: Declare seven instance variables:
                a String, name
                an integer, health
                an integer, swordsmanship
                an integer, agility
                a boolean, hasParrot
                a boolean, hasPegLeg
                an integer, doubloons ***/
                String name;
                int health;
                int swordsmanship;
                int agility;
                boolean hasParrot;
                boolean hasPegLeg;
                int doubloons;

     /*** TODO: Declare a static integer variable, numberOfPirates, to keep track of the current
                number of pirates ***/
                static int numberOfPirates;

     public Pirate() {
          this.name = "Blackbeard";
          this.health = this.swordsmanship = this.agility = 10;
          this.hasParrot = this.hasPegLeg = true;
          this.doubloons = 25;
     }

     public static void main(String[] args) {
          Pirate p = new Pirate();
          System.out.println(p.name + "! Arrrrrrr!");
     }
   }
