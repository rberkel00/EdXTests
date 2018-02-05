import java.lang.*;

public class Practice implements Comparable<Practice> {

     private int a, b;

     public Practice(int a, int b) {
          this.a = a;
          this.b = b;
     }

     /*** TODO: Write a method header for the required method of the Practice class ***/

     public int compareTo(Practice p) {
          /*** TODO: Write the method definition for this method such that it compares this
                      Practice object to the Practice parameter by following these rules:
                      Compare the a values. If this > other, return 1; if this < other, return -1;
                       otherwise compare b values. If this > other, return 1; if this < other, return -1;
                       otherwise return 0 ***/
                      if (a > p.a) {
                        return 1; //done
                      } else if (a < p.a) {
                        return -1; //done
                      } else if (b > p.b) {
                        return 1;
                      } else if (b < p.b) {
                        return -1;
                      } else return 0; //done

     }

     public static void main(String[] args) {
          Practice p1 = new Practice(2,3);
          Practice p2 = new Practice(4,1);
          Practice p3 = new Practice(2,3);
          Practice p4 = new Practice(2,4);
          Practice p5 = new Practice(2,1);
          System.out.println(p1.compareTo(p2)); //-1
          System.out.println(p1.compareTo(p3)); //0
          System.out.println(p2.compareTo(p3)); //1
          System.out.println(p1.compareTo(p4)); //-1
          System.out.println(p1.compareTo(p5)); //1
     }
}
