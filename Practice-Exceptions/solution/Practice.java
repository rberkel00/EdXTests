import java.util.Scanner;

public class Practice {
     public static void main(String[] args) {
          int x, y, z;
          Scanner scan = new Scanner(System.in);

          /*** TODO: Write the header for the try-catch block ***/
          try
          {
               System.out.println("Enter two integers: ");
               x = scan.nextInt();
               y = scan.nextInt();

               z = x / y;
               System.out.printf("The value %d divides %d %d times",x,y,z);
          }
          /*** TODO: Write the header for the block that catches the
                     ArithmeticException. ***/
          catch(ArithmeticException e)
          {
               System.err.print("Divided by zero!");
          }
     }
}
