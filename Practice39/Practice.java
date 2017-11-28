public class Practice {
     public static void main(String[] args) {
          int[] data = {3,-2,-1,0,2,8};
          /*** TODO: Write a static method call to positiveCounter from the Counter
                     class, storing the return value in an integer variable, n ***/
                     int n = Counter.positiveCounter(data);

          System.out.println("There are " + n + " positive numbers in the data.");
     }
}
