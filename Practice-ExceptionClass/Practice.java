public class Practice {
     /*** TODO: Write a method header for the doSomething method, which should be
                declared static, take a single String input, s, and return a String,
                and also throws a MyException to the caller ***/

     {
          if (s.length() < 2) {
               /*** TODO: Write a throw statement for a MyException event ***/
            
          }
          return s.substring(0,2);
     }

     public static void main(String[] args) {
          String s = "CS";
          String t = "A";

          try
          {
               System.out.println(doSomething(s));
               System.out.println(doSomething(t));
          }
          catch (MyException e)
          {
              System.out.println("Too short!");
          }
     }
}
