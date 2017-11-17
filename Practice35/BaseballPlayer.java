public class BaseballPlayer {
     /*** TODO: Declare a String instance variable, first, a String instance variable, last,
                an integer instance variable, position, and a double instance variable,
                battingAverage ***/

    String first;
    String last;
    int position;
    double battingAverage;


     public BaseballPlayer(String first, String last, int position, double ba) {
          this.first = first;
          this.last = last;
          this.position = position;
          this.battingAverage = ba;
     }

     public String toString() {
          String s = "";
          switch(this.position) {
              case 1: s = s + "P  "; break;
              case 2: s = s + "C  "; break;
              case 3: s = s + "1B "; break;
              case 4: s = s + "2B "; break;
              case 5: s = s + "3B "; break;
              case 6: s = s + "SS "; break;
              case 7: s = s + "LF "; break;
              case 8: s = s + "CF "; break;
              case 9: s = s + "RF "; break;
              default: s = s + "U  ";
          }
          s = s + this.first + " " + this.last + "(" + this.battingAverage + ")";
          return s;
     }

     public static void main(String[] args) {
          BaseballPlayer bp1 = new BaseballPlayer("Jose","Altuve",4,0.346);
          System.out.println(bp1);
          BaseballPlayer bp2 = new BaseballPlayer("Clayton","Kershaw",1,0.184);
          System.out.println(bp2);
     }
}
