public class Practice {

     public static int oneMoreTime(int a, int b) {
          /*** TODO: Write the base case such that the recursion will cease and return the
                     value 0 when a is less than 2 and b is less than 3 ***/
          if(a < 2 && b < 3){
            return 0;
          }

          return oneMoreTime(1,a) + b;
     }

     public static void main(String[] args) {
          System.out.println(oneMoreTime(4,5));
          System.out.println(oneMoreTime(10,15));
          System.out.println(oneMoreTime(1, 2));
     }
}
