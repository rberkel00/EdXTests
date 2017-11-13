public class Practice {
     public static void main(String[] args) {
          boolean[][] nbrs = {{true,false,false,true},
                             {false,false,true,true},
                             {true,true,false,true},
                             {false,true,false,false}};

          System.out.println(howManyNeighbors(nbrs,0,2)); // 3
          System.out.println(howManyNeighbors(nbrs,1,1)); // 4
          System.out.println(howManyNeighbors(nbrs,3,3)); // 1
     }

     public static int howManyNeighbors(boolean[][] n, int row, int col) {
          int startR, startC, endR, endC;
          int numNbrs = 0;

          /*** TODO: Set the values of startR, startC, endR, and endC to include the row
                     above and below the given input row, and the column above and below
                     the given input col, checking and adjusting for invalid array indices ***/
          startR = row - 1; //-1
          startC = col - 1; //1
          endR = row + 1; //1
          endC = col + 1; //3

          for (int r = startR; r <= endR; r++)
          {
               for (int c = startC; c <= endC; c++)
               {
                    /*** TODO: Check the current array position, and if it is (1) true, and
                               (2) not the input row and col, execute the following block ***/
                    if ((r != row || c != col) && r >= 0 && c >= 0 && r < n.length && c < n[0].length && n[r][c]){
                         numNbrs++;
                    }
               }
          }
          return numNbrs;
     }
}
