import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static void quick(ArrayList<Comparable> data, int start, int end) throws NullPointerException {
          int dataSize = end - start;
          /*** TODO: Write a selection statement that is true when the size of data is
                     less than 1 ***/
          {
                 return;
          }
          int pivot = start, l = start + 1, r = end;
          /*** TODO: Write a while loop that continues while l is less than r ***/
          {
               /*** TODO: Write a while loop that continues while the element of data
                          referenced by l is less than or equal to the element of data
                          referenced by pivot ***/
               {l++;}
               /*** TODO: Write a while loop that continues while the element of data
                          referenced by r is greater than the element of data referenced
                          by pivot ***/
               {r--;}
               /*** TODO: Swap the element at l with the element at r ***/
          }
          /*** TODO: Swap the element at pivot with the element at r ***/
          /*** TODO: Write two recursive calls to quick, one with start the same and end
                     equal to r, and the other with start equal to r+1 and end the same ***/
          return;
     }
     public static void main(String[] args) {
          Random rnd = new Random(2018);
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long startTime, endTime;

          for (int i = 0; i < 20; i++) {
               myList.add(rnd.nextInt(100));
          }
          System.out.println(myList);

          try {
               startTime = System.nanoTime();
               quick(myList,0,myList.size()-1);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }
          System.out.println("The quick sort took " + (int)((endTime - startTime)/1000000) + " seconds to complete.");
     }
}
