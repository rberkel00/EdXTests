public class Practice {
     public static void main(String[] args) {
          int b = 1, c = 2, d = 3, f = 4;

          /*** TODO: Update the following variables using shortcut operators (++, +=, -=, *=).
                     Increment b by 1; add seven to c; subtract 3 from d; multiply f by 10 ***/

          ++b;
          c+=7;
          d=d-3;
          f=f*10;

          System.out.println("The result of postfix increment on b is " + b);
          System.out.println("The result of addition shortcut assignment on c is " + c);
          System.out.println("The result of subtraction shortcut assignment on d is " + d);
          System.out.println("The result of multiplication shortcut assignment on f is " + f);
     }
}
