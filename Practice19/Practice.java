public class Practice {
     public static void main(String[] args) {
          int x = 0, howMuch = 0;

          /*** TODO: Write a while loop that runs until x is 6 ***/
          while (x < 6) {
               howMuch = howMuch + x;
               x++;
          }
          System.out.println("The final value of x is " + x + " and howMuch is " + howMuch);
     }
}
