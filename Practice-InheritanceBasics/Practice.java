 class BaseClass {
     protected int x;
     protected int y;

     public BaseClass(int x, int y) {
          this.x = x;
          this.y = y;
     }

     public String toString() {return "x: " + x + "; y: " + y;}
}

/*** TODO: Write the header for the ChildClass class, which inherits from BaseClass ***/

{
     /*** TODO: Write the header for the ChildClass constructor, taking two inputs, x and y ***/

     {
          super(x,y);
          this.x = 2 * x;
          this.y = 2 * y;
     }
}

public class Practice {

     public static void main(String[] args) {
          /*** TODO: Create a new ChildClass object with inputs x = 4, y = 3, and store it in a
                     ChildClass reference, c ***/
                    

          System.out.println(c);
     }
}
