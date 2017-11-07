public class Practice {
     public static void main(String[] args) {

          /*** TODO: Declare a new array, myAry, that can store 12 integers ***/
          int[] myAry = new int[6 + 6];
          for (int i = 0; i < 12; i++) {
               myAry[i] = i % 6;
               System.out.println("myAry[" + i + "] stores " + myAry[i]);
          }
     }
}
