import java.util.Scanner;

public class Associative{

  int x;
  int y;
  int z;
  
  public Associative (int x, int y, int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int firstTwo(){
    int first = (x+y)*z;
    return first;
  }
  
  public int lastTwo(){
    int second = x+(y*z);
    return second;
  }
  
  public static void main(String[] args){
    System.out.println("Please provide three integers: ");
    Scanner in = new Scanner(System.in);
    int x = in.nextInt();
    int y = in.nextInt();
    int z = in.nextInt();
    
    Associative one = new Associative(x,y,z);
    System.out.println("Grouping the first two terms, (" + x + " + " + y + ") * "
                         + z + " = " + one.firstTwo());
    System.out.println("Grouping the last two terms, " + x + " + (" + y + " * " + z + ") = " + one.lastTwo());
  }
}