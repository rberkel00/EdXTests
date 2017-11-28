public class Counter {
     /*** TODO: Write the method header for the static method positiveCounter that takes
                an integer array, myAry, as input and returns an integer as output. ***/
     static int positiveCounter(int[] myAry){
          int howMany = 0;
          for (int x : myAry) {
               if (x > 0) {
                    howMany++;
               }
          }
          return howMany;
     }
}
