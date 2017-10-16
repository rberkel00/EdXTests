import java.lang.Math;

public class Practice {
     public static void main(String[] args) {
          double b = 1.3333;
          int c = 4, d = 3;
          final double TOLERANCE = 0.001;

          /*** TODO: Write an if statement to serve as the header for the conditional block below.
                     Execute the first block if the floating point outcome of c divided by d is
                      within the given TOLERANCE of b. ***/
          if ((Math.abs(((double)c)/d) - b) > TOLERANCE) {
               System.out.println("Value within the tolerance level!");
          } else {
               System.out.println("Value outside the tolerance level!");
          }
     }
}
