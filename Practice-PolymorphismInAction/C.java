public class C extends A {
     public C(int x) {super(x % 4);}

     public String doSomething() {
          String s = "";
          if (x == 1) s += "meh!";
          else if (x == 2) s += "bleh!";
          else s += "eh!";
          return s;
     }
}
