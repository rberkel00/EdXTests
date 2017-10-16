import java.util.Scanner;
public class Practice {
     public static void main(String[] args) {
          Scanner scn = new Scanner(System.in);
          int x = 0, howMuch = 0;

          /*** TODO: Write a while loop that runs until the user enters a non-integer ***/
          while (scn.hasNextInt()) {
               x = scn.nextInt();
               howMuch = howMuch + x;
          }
          System.out.println("The final value of howMuch is " + howMuch);
     }
}
