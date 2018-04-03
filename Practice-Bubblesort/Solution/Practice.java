import java.util.ArrayList;

public class Practice {

     public static void main(String[] args) {
          /*** TODO: Declare an ArrayList of Strings called customers ***/
            ArrayList<String> customers = new ArrayList<String>();

          /*** TODO: Add three Strings to customers in this order: Michael,
                      Alyssa, Pranav ***/
            customers.add("Michael");
            customers.add("Alyssa");
            customers.add("Pranav");

          /*** TODO: Add one String to the beginning of customers: LaKisha ***/
            customers.add(0, "LaKisha");
          /*** TODO: Remove Alyssa from customers ***/
            customers.remove("Alyssa");
          /*** TODO: Set Pranav to Grace ***/
            //customers.set(2, "Grace");
            customers.set(customers.indexOf("Pranav"), "Grace");

          String s;
          /*** TODO: Get the second element in the list and store in the
                      String s ***/
            s = customers.get(1);

          /*** TODO: Add the String s to the end of customers ***/
            customers.add(s);

          for (String customer : customers) {
              System.out.println(customer);
          }
          System.out.println(customers.size());
     }

}
