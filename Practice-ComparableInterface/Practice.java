import java.lang.*;

public class Practice implements Comparable<Practice> {

     private int a, b;

     public Practice(int a, int b) {
          this.a = a;
          this.b = b;
     }

     /*** TODO: Write a method header for the required method of the Practice class ***/
     
     {
          /*** TODO: Write the method definition for this method such that it compares this
                      Practice object to the Practice parameter by following these rules:
                      Compare the a values. If this > other, return 1; if this < other, return -1;
                       otherwise compare b values. If this > other, return 1; if this < other, return -1;
                       otherwise return 0 ***/

     }

     public static void main(String[] args) {
          Practice p1 = new Practice(2,3);
          Practice p2 = new Practice(4,1);
          Practice p3 = new Practice(2,3);
          System.out.println(p1.compareTo(p2));
          System.out.println(p1.compareTo(p3));
          System.out.println(p2.compareTo(p3));
     }
}
