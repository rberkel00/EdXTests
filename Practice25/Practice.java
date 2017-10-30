public class Practice {
     public static void main(String[] args) {
          float logApprox = 0, x;
          int n = 7;

          // To approximate a log for n, solve n = (1 + x)/(1 - x)
          // Then you can complete the series: 2*(x + x^3/3 + x^5/5 ...)

          x = (float)(n - 1) / (n + 1);

          /*** TODO: Write a for loop from i = 1 to 100, incrementing by 2 ***/
          for (int i = 1; i <= 100; i +=2){
               logApprox += Math.pow(x,i);
          }
          logApprox *= 2;

          System.out.println("The approximate log(" + n + ") = " + logApprox);
     }
}
