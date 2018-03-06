public class B extends A {
     private int d;

     public B(int a, int b, int c, int d) {
       /*** TODO: Call the parameterized A constructor such that a, b, and c
                  are set appropriately ***/
          super(a,b,c);
          this.d = d;


     }

     public String toString() {
          return super.toString() + ", " + d;
     }
}
