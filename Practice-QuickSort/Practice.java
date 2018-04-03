import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static void quick(ArrayList<Comparable> data, int start, int end) throws NullPointerException {
          int dataSize = end - start;
          /*** TODO: Write a selection statement that is true when the size of data is
                     less than 1 ***/
          if(data.size() < 1)
          {
                 return;
          }
          int pivot = start, l = start + 1, r = end;
          /*** TODO: Write a while loop that continues while l is less than r ***/
          while(l < r)
          {
               /*** TODO: Write a while loop that continues while the element of data
                          referenced by l is less than or equal to the element of data
                          referenced by pivot ***/
                          while (data.get(l).compareTo(data.get(pivot)) <= 0)
               {l++;}
               /*** TODO: Write a while loop that continues while the element of data
                          referenced by r is greater than the element of data referenced
                          by pivot ***/
                          while(data.get(r).compareTo(data.get(pivot)) > 0)
               {r--;}
               /*** TODO: Swap the element at l with the element at r ***/
                    Comparable temp = data.get(l);
                    data.set(l, data.get(r));
                    data.set(r, temp);
          }
          /*** TODO: Swap the element at pivot with the element at r ***/
                    Comparable temp = data.get(pivot);
                    data.set(pivot, data.get(r));
                    data.set(r, temp);
          /*** TODO: Write two recursive calls to quick, one with start the same and end
                     equal to r, and the other with start equal to r+1 and end the same ***/
                     quick(data, start, r);
                     quick(data, r+l, end);
          return;
     }
     public static void main(String[] args) {
          Random rnd = new Random(2018);
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long startTime = 0;
          long endTime = 0;

          for (int i = 0; i < 20; i++) {
               myList.add(rnd.nextInt(100));
          }
          System.out.println(myList);

          try {
               startTime = System.nanoTime();
               quick(myList,0,myList.size()-1);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               e.printStackTrace();
          }
          System.out.println("The quick sort took " + (int)((endTime - startTime)/1000000) + " seconds to complete.");
     }
}
