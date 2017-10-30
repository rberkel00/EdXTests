public class Practice {
     public static void main(String[] args) {
          String buildMe = "";

          /*** TODO: Write a for loop that constructs a String using the
                     ASCII characters 'P' through 'U'  ***/
          for (int x = 'P'; x < 'U' + 1; x++){
               buildMe = buildMe + (char)x;
          }
          System.out.println("String: " + buildMe + " ");
     }
}
