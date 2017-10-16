import java.util.Scanner;
import java.lang.Math;

public class Practice {
     public static void main(String[] args) {
          Scanner s = new Scanner(System.in);
          int a = s.nextInt();

          /*** TODO: Write a statement that calculates the square root of "a" and stores the result in "z" ***/
          double z = Math.sqrt(a);

          System.out.println("The answer is " + z);
     }
}
