import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static void merge(ArrayList<Comparable> data) throws NullPointerException {
          int dataSize;
          /*** TODO: Write a selection statement that is true when the size of data is 1 ***/
          {
                 return;
          }
          ArrayList<Comparable> leftData = new ArrayList<Comparable>();
          ArrayList<Comparable> rightData = new ArrayList<Comparable>();
          /*** TODO: Write loops that add elements 0 to dataSize/2  to leftData, and
                     dataSize/2 + 1 to dataSize to rightData ***/
          /*** TODO: Make two recursive calls to merge, one passing leftData and the
                     other passing rightData ***/
          int l = 0, r = 0;
          /*** TODO: Write a while loop that repeats until either l is greater than or equal
                     to the size of leftData, or r is greater than or equal to the size of
                     rightData ***/
          {
               /*** TODO: Write a selection statement that is true when the element in
                          leftData referenced by l is less than the element in rightData
                          referenced by r ***/
               {
                    dataSet(l+r,leftData.get(l));
                    l++;
               } else {
                    dataSet(l+r,rightData.get(r));
                    r++;
               }
          }
          /*** TODO: Write a loop that repeats until l is greater than or equal to
                     the size of leftData ***/
          {
               /*** TODO: Set the element of data referenced by l+r to the element
                          of leftData referenced by l, and increment l ***/
          }
          /*** TODO: Write a loop that repeats until r is greater than or equal to
                     the size of rightData ***/
          {
               /*** TODO: Set the element of data referenced by l+r to the element
                          of rightData referenced by r, and increment r ***/
          }
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
               merge(myList);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               System.err.println(e.printStackTrace());
          }
          System.out.println("The merge sort took " + (int)((endTime - startTime)/1000) + " seconds to complete.");
     }
}
