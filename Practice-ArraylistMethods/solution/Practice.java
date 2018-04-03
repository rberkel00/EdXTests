import java.util.*;
public class Practice {
     public static void main(String[] args) {
          /*** TODO: Declare and create an ArrayList, myList, that can store String types. ***/
          ArrayList<String> myList = new ArrayList<String>();
          /*** TODO: Add the following data to myList in this order: "Mallory", "Saturn", "Mars",
                     "Jupiter", "Uranus", "Pluto" ***/
                     myList.add("Mallory");
                     myList.add("Saturn");
                     myList.add("Mars");
                     myList.add("Jupiter");
                     myList.add("Uranus");
                     myList.add("Pluto");

          System.out.println(myList);
          /*** TODO: Set the element "Mallory" equal to "Mercury" ***/
          myList.set(0,"Mercury");

          System.out.println(myList);
          /*** TODO: Set the element "Saturn" equal to "Venus" and store the output into a
                     new String, s ***/
                     String s = myList.set(1, "Venus");
          System.out.println(myList);
          System.out.println(s);
          /*** TODO: Add the String, s, into myList in between "Jupiter" and "Uranus" ***/
          myList.add(4, s);
          System.out.println(myList);
          /*** TODO: Remove "Pluto" from the list ***/
          myList.remove(6);
          System.out.println(myList);
          /*** TODO: Add "Earth" and "Neptune" into myList. "Earth" should appear between
                     "Venus" and "Mars", and "Neptune" should appear at the end. ***/
                     myList.add(2, "Earth");
                     myList.add(7, "Neptune");

          System.out.println(myList);
     }
}
