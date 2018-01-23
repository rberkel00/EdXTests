public class Practice {

     public static int greatestCommonDivisor(int a, int b) {
       /*** TODO: Write a recursively defined function that behaves as follows:
                  base case: a is evenly divided by b; return b
                  recursive case: return the outcome of the recursive call,
                   passing b as the first parameter, and a modulus b as the
                   second parameter ***/
       if ( a % b == 0){
         return b;
       }

       return greatestCommonDivisor (b, a%b);
     }

     public static void main(String[] args) {
          System.out.println(greatestCommonDivisor(8,24));
          System.out.println(greatestCommonDivisor(13,102));
          System.out.println(greatestCommonDivisor(100,80));
     }
}
