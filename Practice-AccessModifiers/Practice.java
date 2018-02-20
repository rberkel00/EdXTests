public class Practice
{
     /*** TODO: Declare three fields, two integers (a and b) and one double (c) such that
                they are accessible only by methods of this class and no child classes ***/
                private int a;
                private int b;
                private double c;

     public Practice(int a, int b, double c) {
          this.a = a;
          this.b = b;
          this.c = c;
     }

     /*** TODO: Write an accessor method header for the field, a ***/
     private int getA()
     {
          return a;
     }

     public static void main(String[] args) {
          Practice p = new Practice(1,2,3.45);
          System.out.println(p.getA());
          /*** NOTE: If you have done the above tasks correctly, compiling with
                     the line below uncommented will lead to an error ***/
          // System.out.println(p.a);
     }
}
