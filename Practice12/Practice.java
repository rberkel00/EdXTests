import java.lang.Math;

public class Practice {
     public static void main(String[] args) {
          int b = -20, c = 19;
          boolean d = true, f = false;

          /*** TODO: Write an if statement to serve as the header for the conditional block below.
                     Execute the first block if the absolute value of b is greater than c and
                      either d or f is true. ***/
          if (Math.abs(b) > c && (d || f)) {
               System.out.println("It must be true!");
          } else {
               System.out.println("It is certainly false!");
          }
     }
}
