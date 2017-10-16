public class Practice {
     public static void main(String[] args) {
          System.out.println("Rhode Island is in the " + getRegion("RI") + " region");
          System.out.println("Winterfell is in the " + getRegion("WF") + " region");
     }
     public static String getRegion(String state) {
          String s = "";
          /*** TODO: Write a switch statement to serve as the header for the conditional block
                      below. ***/
          switch (state) {
               /*** TODO: Write a case statement that includes "RI", and any other code needed
                           to end the case ***/
              case "RI" :
                    s = "New England";
                    break;
               /*** TODO: Write the default case statement, and any other code needed to end
                           the case ***/
              default :
                    s = "Unknown";
                    break;
          }
          return s;
     }
}
