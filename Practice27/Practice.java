public class Practice {
     public static void main(String[] args) {

          /*** TODO: Write an outer for loop from i = 1 to 5,
                     incrementing by 1 ***/
          for (int i = 1; i <= 5; i++){
               /*** TODO: Write an inner for loop from j = 1 to 5,
                          incrementing by 1 ***/
               for (int j = 1; j <= 5; j++){
                    /*** TODO: Write an if statement that determines
                               whether i is equal to j or (6 - i) is
                               equal to j ***/
                    if (i == j ||  6 - i == j){
                         System.out.print("#");
                    } else {
                         System.out.print(" ");
                    }
               }
               System.out.print("\n");
          }
     }
}
