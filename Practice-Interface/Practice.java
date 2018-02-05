import java.lang.*;

/*** TODO: Write the interface header for the Practical interface ***/
interface Practical
{
     public String includeMe();
}

public class Practice implements Practical {

     /*** TODO: Write a method header for the required method of the Practice class ***/
     public String includeMe()
     {
          return "A practical method";
     }

     public static void main(String[] args) {
          String s = "Didn't do it correctly";
          Practice p = new Practice();
          /*** TODO: Write a method call to includeMe and store the output in the String s ***/
          s = p.includeMe();
          System.out.println(s);
     }
}
