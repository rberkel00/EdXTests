public class A {
     private int a, b, c;

     public A() {
         /*** TODO: Call the parameterized constructor with the values a = 1, b = 2, c = 3 ***/
         this(1,2,3);

     }

     public A(int a, int b, int c) {
          this.a = a;
          this.b = b;
          this.c = c;
     }

     public String toString() {
          return "" + a + ", " + b + ", " + c;
     }
}
