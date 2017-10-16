public class Practice {
     public static void main(String[] args) {
          String s1 = "", s2 = "alacazam", s3 = "abracadabra";
          firstLastCheck(s1);
          firstLastCheck(s2);
          firstLastCheck(s3);
     }
     public static void firstLastCheck(String s) {
          /*** TODO: Write an if statement to serve as the header for the conditional block below.
                     Execute the first block if the length of String s is greater than 0 AND
                       if the last letter of s is the same as the first letter of s. ***/
          if (s.length() > 0 && s.charAt(0) == s.charAt(s.length() - 1)) {
               System.out.println(s + ": Same start and finish letter!");
          }
     }
}
