import java.lang.*;

/*** TODO: Write the interface header for the Practical interface ***/

{
     public String includeMe();
}

public class Practice implements Practical {

     /*** TODO: Write a method header for the required method of the Practice class ***/

     {
          return "A practical method";
     }

     public static void main(String[] args) {
          String s = "Didn't do it correctly";
          Practice p = new Practice();
          /*** TODO: Write a method call to includeMe and store the output in the String s ***/
          
          System.out.println(s);
     }
}
