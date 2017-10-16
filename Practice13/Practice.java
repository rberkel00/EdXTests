public class Practice {
     public static void main(String[] args) {
          String s = "How many letters do I have?";

          /*** TODO: Write an if statement to serve as the header for the conditional block below.
                     Execute the first block if the length of s is greater than 50 ***/

          if (s.length() > 50)
               System.out.println("More than 50 letters in \"" + s + "\"");
          /*** TODO: Write the else if clause to execute the block below if the length of s is
                      greater than 40 ***/


          else if (s.length() > 40)
               System.out.println("Between 41 and 50 letters in \"" + s + "\"");
          /*** TODO: Write the else if clause to execute the block below if the length of s is
                      greater than 30 ***/


          else if (s.length() > 30)
               System.out.println("Between 31 and 40 letters in \"" + s + "\"");
          /*** TODO: Write the else clause to execute the block below in all other cases ***/


          else
               System.out.println("30 or fewer letters in \"" + s + "\"");
     }
}
