/*** TODO: Write a header for the Pizza class which inherits from Object ***/

{
     private String[] toppings;

     public Pizza(String a, String b, String c) {
          toppings = new String[3];
          toppings[0] = a;
          toppings[1] = b;
          toppings[2] = c;
     }

     /*** TODO: Write the toString method header to override the Object class toString method ***/

     {
          String s = "Pizza with ";
          for (String t : toppings) {s = s + t + " ";}
          return s;
     }
}
