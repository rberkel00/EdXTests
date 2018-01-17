public class Practice {

     public static double geometric(int a) {
          /*** TODO: Write a recursively defined function that behaves as follows:
                     base case: a is 0; return 1
                     recursive case: return the sum of 1 divided by 2^a and the
                      outcome of the recursive call, passing a-1 ***/
          if (a == 0) {
            return 1;
          }
          return 1/Math.pow(2, a) + geometric(a-1);
     }

     public static void main(String[] args) {
          System.out.println("a = 0: " + geometric(0));
          System.out.println("a = 20: " + geometric(20));
     }
}
