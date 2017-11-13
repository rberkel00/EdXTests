public class Practice {
     public static void main(String[] args) {
          /*** TODO: Write the declaration for a 4x6 2-dimensional array of integers, mtrx ***/
          int[][] mtrx = new int[4][6];

          mtrx[0][0] = 15;
          mtrx[3][5] = 45;

          System.out.println("The first row, first column entry is " + mtrx[0][0]);
          System.out.println("The last row, last column entry is " + mtrx[3][5]);
     }
}
