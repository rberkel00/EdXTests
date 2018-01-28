public class Practice {

     public static int mathy(int a) {
          /*** TODO: Write a recursively defined function that behaves as follows:
                     base case: a is 0; return 1
                     recursive case: return the sum of the recursive call, passing
                      a divided by 2 and the recursive call passing the remainder
                      of the quantity a - 1 divided by 2 ***/

          //base case
          if (a == 0){
            return 1;
          }
          //recursive case
          return mathy(a/2)+ mathy((a-1)/2);
     }

     public static void main(String[] args) {
          System.out.println(mathy(12));
     }
}
