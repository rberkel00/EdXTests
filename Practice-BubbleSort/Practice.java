import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static long bubble(ArrayList<Comparable> data) throws NullPointerException {
          Comparable temp;
          long startTime, endTime;
          startTime = System.nanoTime();

          /*** TODO: Write the outer for-loop that repeats once for every item in data ***/
          {
               /*** TODO: Write the inner for-loop that repeats once for every item in
                          data (NOTE: You can optimize this using the variable from the
                          outer for-loop if you wish) ***/
               {
                    /*** TODO: Write a conditional statement that returns true if the data
                               item at the position indicated by the current value of
                               the inner for-loop variable is greater than the next
                               position the inner for-loop will reference ***/
                    {
                         /*** TODO: Swap the current element with the next element in
                                    data without losing information ***/
                    }
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
               /*** TODO: Write a function call to the bubble method, passing myList
                          as a parameter and storing the result in timeTaken ***/
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }

          System.out.println(myList);
          System.out.println("The bubble sort took " + (int)(timeTaken/1000000) + " seconds to complete.");
     }
}
