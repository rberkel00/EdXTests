public class Practice {

     /*** TODO: Write the method header for a method named doItAgain, which takes one
                integer parameter (x) and returns an integer result ***/
    public static int doItAgain(int x) {
          if (x <= 1) return 1;
          return x + doItAgain(x / 2);
     }

     public static void main(String[] args) {
          System.out.println(doItAgain(32));
          System.out.println(doItAgain(100));
     }
}
