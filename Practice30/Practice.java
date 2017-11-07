public class Practice {
     public static void main(String[] args) {
          boolean[] roomClean = {true, false, false, true, false, true, true, false};

          /*** TODO: Write the header for a for-loop that iterates through
                     roomClean, using "i" as the counter variable ***/
          for (int i = 0; i < roomClean.length; i++){
               /*** TODO: Write an if-statement that will execute the block if
                          the current room needs to be cleaned ***/
               if (roomClean[i]){
                    System.out.println("Room " + i + " needs cleaning");
               }
          }
     }
}
