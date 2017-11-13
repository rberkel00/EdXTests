public class Practice {
     public static void main(String[] args) {
          char[][] ltrs = new char[4][13];
          int start = 65;

          /*** TODO: Write a for-loop using r that iterates over all the rows in the 2d array ***/
          for (int r = 0; r < ltrs.length; r++) {
               /*** TODO: Write a for-loop using c that iterates over each element in the row ***/
               for (int c = 0; c < ltrs[r].length; c++){
                    ltrs[r][c] = (char)(start + (r*13) + c);
               }
               if (r == 1) start += 6;
          }
          System.out.println(ltrs[0][3] + "id this run correctl" + ltrs[3][11] + "?");
     }
}
