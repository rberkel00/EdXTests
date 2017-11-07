public class Practice {
     public static void main(String[] args) {
          double[] distance = {35.3, 17.8, 21.3, 104.0, 55.9, 59.7, 44.3};
          double totalDistance = 0;

          /*** TODO: Write the header for a for-each loop that iterates
                     through distance using "d" as the temporary variable ***/
          for (double d : distance) {
               totalDistance += d;
          }

          System.out.println("The total distance was " + totalDistance);
     }
}
