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
     public int getA()
     {
          return a;
     }
}
