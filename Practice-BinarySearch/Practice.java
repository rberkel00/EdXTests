import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;


public class Practice {
     public static int binary(ArrayList<Comparable> data, Comparable findMe, int start, int end) throws NullPointerException {
          /*** TODO: Write a selection statement that is true when start is greater than or
                     equal to end ***/


          if (start >= end)
          {
                 return -1;
          }
          int midpt;
          /*** TODO: Calculate the midpoint between start and end and store in midpt (NOTE:
                     Don't forget to account for where you started in your calculation) ***/
                     midpt = (start + end) / 2;

          /*** TODO: Write a selection statement with the following conditions and outcomes
                      If the element referenced by midpt is findMe, return midpt
                      If the element referenced by midpt is greater than findMe, return
                       the result of a recursive call to binary with midpt passed as the
                       new end and start the same
                      If the element referenced by midpt is less than findMe, return the
                       result of a recursive call the binary with midpt passed as the new
                       start and end the same ***/

                       if (findMe.compareTo(midpt)==1) {
                          return midpt;
                       }

                       if (findMe.compareTo(midpt) < 0) {
                         return binary(data, findMe, start, midpt);
                       }
                       if (findMe.compareTo(midpt) > 0) {
                         return binary(data, findMe, midpt, end);
                       }

              return 0;         
     }

     public static void main(String[] args) {
          int index = 0;
          Random rnd = new Random();
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long startTime = 0;
          long endTime = 0;

          for (int i = 0; i < 1000; i++) {
               myList.add(rnd.nextInt(100));
          }
          /*** TODO: Sort the data using any method you wish ***/
          Collections.sort(myList);
          System.out.println(myList);

          try {
               startTime = System.nanoTime();
               index = binary(myList,new Integer(42),0,myList.size()-1);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
              e.printStackTrace();
          }

          System.out.print("The binary search took ");
          System.out.print((int)((endTime-startTime)/1000000) + " seconds to complete ");
          if (index > -1) {
               System.out.println("and found the element at position " + index + " of our list.");
          } else {
               System.out.println("and did not find the element.");
          }
     }
}
