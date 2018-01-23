public class Practice {

     public static int repeatMe(int a, int b) {
       /*** TODO: Write a recursive call to repeatMe, passing 3 plus the remainder of b divided
                  by a as the first parameter, and b minus a as the second parameter, storing the
                  result in the variable, c ***/

          if (a > b) return b;
          int c;
          c = repeatMe(3+b%a, b-a);
          return a + c;
     }

     public static void main(String[] args) {
          System.out.println(repeatMe(2,10));
          System.out.println(repeatMe(3,24));
     }
}
