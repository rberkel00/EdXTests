public class Practice {
     public static void main(String[] args) {
          Payment p = new Payment("Phil","Colin","212.73");
          System.out.println("The amount to pay is " + p.getAmount());
          p.setAmount(-20);
          System.out.println("The amount to pay is " + p.getAmount());
          p.setAmount(185.11);
          System.out.println("The amount to pay is " + p.getAmount());
     }
}
