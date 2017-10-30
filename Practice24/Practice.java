public class Practice {
     public static void main(String[] args) {
          String doubles = "I'll hurry to kitty, Jess";

          /*** TODO: Write a for loop that looks at every pair of letters in
                     the String and prints out if there are repeated
                     letters  ***/
          for (int x = 0; x < doubles.length()-1; x++) {
               /*** TODO: Write an if-statement that checks if the current
                          letter is equal to the next letter ***/
               if (doubles.charAt(x) == doubles.charAt(x+1)){
                    System.out.println("Double " + doubles.charAt(x) + "'s");
               }
          }
     }
}
