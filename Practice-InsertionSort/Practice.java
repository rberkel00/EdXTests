import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static long insertion(ArrayList<Comparable> data) throws NullPointerException {
          Comparable temp;
          long startTime, endTime;
          startTime = System.nanoTime();

          for (int x = 1; x < data.size(); x++) {
               /*** TODO: Write the inner loop that walks from the current value of the
                          outer for-loop until that value is greater than the value in
                          the position one less than it ***/
               {
                   /*** TODO: Swap the value referenced by the inner loop with the value in
                              the position one less than it ***/
               }
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
               /*** TODO: Write a function call to the insertion method, passing myList
                          as a parameter and storing the result in timeTaken ***/
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }

          System.out.println(myList);
          System.out.println("The insertion sort took " + (int)(timeTaken/1000000) + " seconds to complete.");
     }
}
