public class Practice {
     public static void main(String[] args) {
          int x = 6, y = 10;
          float z;
          public static final double BLAH = 3.0;
          /*** TODO: Write an expression that calculates x divided by y and stores the result
                     in z without losing precision ***/

          z = x/y;
          z = (float)x/y;

          System.out.println("The result of " + x + " / " + y + " is " + z + BLAH);
     }
}
