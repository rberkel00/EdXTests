public class A {
     private int x;
     private char c;

     public A(int x, char c) {
          this.x = x;
          this.c = c;
     }

     public int getX() {return x;}
     public char getC() {return c;}

     public String toString() {return "Parent Class, " + x;}
}
