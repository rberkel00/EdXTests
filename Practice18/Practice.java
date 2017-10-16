public class Practice {
     public static void main(String[] args) {
          howManyPizzas(1);
          howManyPizzas(10);
          howManyPizzas(0);
     }
     public static void howManyPizzas(int pizzas) {
          String plural;
          /*** TODO: Write a ternary statement that assigns the value 's' to plural if
                      the number of pizzas is not 1.***/

          plural = (pizzas > 1) ? "s" : "";
          System.out.println("I have " + pizzas + " pizza" + plural + ".");
     }
}
