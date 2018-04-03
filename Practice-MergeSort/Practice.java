import java.util.ArrayList;
import java.util.Random;

public class Practice {
     public static void merge(ArrayList<Comparable> data) throws NullPointerException {
          int dataSize;
          /*** TODO: Write a selection statement that is true when the size of data is 1 ***/
          if(data.size() == 1)
          {
                 return;
          }
          ArrayList<Comparable> leftData = new ArrayList<Comparable>();
          ArrayList<Comparable> rightData = new ArrayList<Comparable>();
          /*** TODO: Write loops that add elements 0 to dataSize/2  to leftData, and
                     dataSize/2 + 1 to dataSize to rightData ***/
                    for(int i = 0; i < data.size()/2; i++){
                      leftData.add(i);
                    }
                    for (int i = (data.size()/2)+1; i< data.size(); i++){
                      rightData.add(i);
                    }
          /*** TODO: Make two recursive calls to merge, one passing leftData and the
                     other passing rightData ***/
                     merge(leftData);
                     merge(rightData);
          int l = 0, r = 0;
          /*** TODO: Write a while loop that repeats until either l is greater than or equal
                     to the size of leftData, or r is greater than or equal to the size of
                     rightData ***/
          while(l < leftData.size() || r < rightData.size())
          {
               /*** TODO: Write a selection statement that is true when the element in
                          leftData referenced by l is less than the element in rightData
                          referenced by r ***/
               if(leftData.get(l).compareTo(rightData.get(r)) < 0 )
               {
                    data.set(l+r,leftData.get(l));
                    l++;
               } else {
                    data.set(l+r,rightData.get(r));
                    r++;
               }
          }
          /*** TODO: Write a loop that repeats until l is greater than or equal to
                     the size of leftData ***/
          while(l < leftData.size())
          {
               /*** TODO: Set the element of data referenced by l+r to the element
                          of leftData referenced by l, and increment l ***/
                          data.set(l+r,leftData.get(l));
                          l++;

          }
          /*** TODO: Write a loop that repeats until r is greater than or equal to
                   the size of rightData ***/
          while(r < rightData.size())
          {
               /*** TODO: Set the element of data referenced by l+r to the element
                          of rightData referenced by r, and increment r ***/
                          data.set(l+r,rightData.get(r));
                          r++;
          }
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
               merge(myList);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               e.printStackTrace();
          }
          System.out.println("The merge sort took " + (int)((endTime - startTime)/1000) + " seconds to complete.");
     }
}
