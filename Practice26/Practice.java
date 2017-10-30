public class Practice {
     public static void main(String[] args) {

          /*** TODO: Write an outer for loop from i = 1 to 10,
                     incrementing by 1 ***/
          for (int i = 1; i <= 10; i++){
               /*** TODO: Write an inner for loop from j = 1 to 10,
                          incrementing by 1 ***/
               for (int j = 1; j <= 10; j++){
                    /*** TODO: Write an if statement comparing the
                               value of i to j and checking for equality ***/
                    if (j == i){
                         System.out.print("#");
                    } else {
                         System.out.print(" ");
                    }
               }
               System.out.print("\n");
          }
     }
}
