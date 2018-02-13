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

public class B inherits A {
     private int d;

     public B(int a, int b, int c, int d) {
          this.d = d;
          /*** TODO: Call the parameterized A constructor such that a, b, and c
                     are set appropriately ***/
                     

     }

     public String toString() {
          return super.toString() + ", " + d;
     }
}

public class Practice {
     public static void main(String[] args) {
          A a1 = new A();
          A a2 = new A(2,20,2018);
          B b = new B(2,4,6,8);
          System.out.println(a1);
          System.out.println(a2);
          System.out.println(b);
     }
}
