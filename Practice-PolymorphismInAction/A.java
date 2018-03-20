public class A {
     protected int x;

     public A(int x) {
          this.x = x;
     }

     public String doSomething() {
          String s = "";
          for (int i = 0; i < x; i++) {
               s += "yah!";
          }
          return s;
     }
}
