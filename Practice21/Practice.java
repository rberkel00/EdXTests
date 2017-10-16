public class Practice {
     public static void main(String[] args) {
          int x = 0;

          /*** TODO: Write a do-while loop that runs until the value of x is divisible by 4 ***/
          do {
               x += 5;
          } while (x % 4 != 0);
          System.out.println("The final value of x is " + x);
     }
}
