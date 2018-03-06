public class B extends A {
     public B(int x) {super(x + x,'B');}

     public String toString() {
          return "Child Class " + getC() + ", " + getX();
     }
}
