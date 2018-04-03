import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static long selection(ArrayList<Comparable> data) throws NullPointerException {
          Comparable temp, minimum;
          long startTime, endTime;
          startTime = System.nanoTime();

          for (int x = 0; x < data.size(); x++) {
               /*** TODO: Set minimum equal to the current value of the outer for-loop ***/
               for (int y = 0; y < data.size(); y++) {
                    /*** TODO: Write a conditional statement that returns true if the data
                               item at the position indicated by the current value of the
                               inner for-loop variable is less than the item referenced by
                               the current value of minimum ***/
                    {
                         /*** TODO: Set minimum equal to the current value of the inner
                                    for-loop ***/
                    }
               }
               /*** TODO: Swap the value referenced by minimum with the value referenced by
                          the outer for-loop ***/
          }

          endTime = System.nanoTime();
          return endTime - startTime;
     }

     public static void main(String[] args) {
          Random rnd = new Random(2018);
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long timeTaken;

          for (int i = 0; i < 20; i++) {
               myList.add(rnd.nextInt(100));
          }
          System.out.println(myList);

          try {
               /*** TODO: Write a function call to the selection method, passing myList
                          as a parameter and storing the result in timeTaken ***/
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }

          System.out.println(myList);
          System.out.println("The selection sort took " + (int)(timeTaken/1000000) + " seconds to complete.");
     }
}
