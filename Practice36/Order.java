public class Order {
     String menuChoice;
     /*** TODO: Declare a static variable, orders, to keep track of how many current
                orders a restaurant has open ***/
     static int orders = 0;

     public Order(String menuChoice) {
          this.menuChoice = menuChoice;
          /*** TODO: Increment the static variable, orders ***/
          orders++;
     }

     public void makeFood() {
          System.out.println(menuChoice + "! Order up!");
          /*** TODO: Decrement the static variable, orders ***/
          orders--;
     }

     public String toString() {
          return "There are " + orders + " open orders, and this order is for a " + menuChoice;
     }

     public static void main(String[] args) {
          Order o1 = new Order("Burrito");
          Order o2 = new Order("Salad");
          o2.makeFood();
          Order o3 = new Order("Pizza");
          System.out.println(o3);
     }
}
