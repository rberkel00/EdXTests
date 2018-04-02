import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static int linear(ArrayList<Comparable> data, Comparable findMe) throws NullPointerException {

          /*** TODO: Write a loop that iterates over data or until the element is found ***/
          {
               /*** TODO: Write a selection statement that compares findMe to the current
                          element ***/
               {
                    /*** TODO: Return the index of the current element ***/
               }
          }

          return -1;
     }

     public static void main(String[] args) {
          Random rnd = new Random();
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long startTime, endTime;

          for (int i = 0; i < 1000; i++) {
               myList.add(rnd.nextInt(100));
          }
          System.out.println(myList);

          try {
               startTime = System.nanoTime();
               index = linear(myList,new Integer(42));
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }

          System.out.print("The linear search took ")
          System.out.print((int)((endTime-startTime)/1000000) + " seconds to complete ");
          if (index > -1) {
               System.out.println("and found the element at position " + index + " of our list.")
          } else {
               System.out.println("and did not find the element.");
          }
     }
}
