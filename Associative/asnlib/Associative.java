import java.util.Scanner;

public class Associative {

    int x;
    int y;
    int z;

    Associative(int x, int y, int z) {
  this.x = x;
  this.y = y;
  this.z = z;
    }

    int firstTwo() {
  return (x + y) * z;
    }

    int lastTwo() {
  return x + (y * z);
    }

    public static void main(String[] args) {
  System.out.println("Type in three integers." );
  Scanner s = new Scanner(System.in);
  int x = s.nextInt();
  int y = s.nextInt();
  int z = s.nextInt();

  Associative a = new Associative(x, y, z);
  System.out.println("Grouping the first two terms, (" + x + " + " + y + ") * " + z + " = " +  a.firstTwo());
  System.out.println("Grouping the last two terms, " + x + " + (" + y + " * " + z + ") = " +  a.lastTwo());

    }


}
