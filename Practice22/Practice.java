public class Practice {
     public static void main(String[] args) {
          /*** TODO: Write a for-loop that repeats using a variable i to store
                     the values 1 to 24, incrementing i by 1 each repetition. ***/
          int x = 0;
          for (int i = 1; i < 24; i++) {
               if (i % 12 == 0) {
                    x++;
                    System.out.println("Time to start over again! Repetition number : " + x);
               }
          }
     }
}
