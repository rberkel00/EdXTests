public class Payment {
     /*** TODO: Add access modifiers for each field to make them inaccessible
                outside of the class definition ***/
     private String payer;
     private String payee;
     private double amount;

     public Payment(String payer, String payee, double amount) {
          this.payer = payer;
          this.payee = payee;
          this.amount = amount;
     }

     /*** TODO: Add an accessor method for the amount field ***/
     public double getAmount() {
       return amount;
     }

     /*** TODO: Add a mutator method for the amount field, in which the amount is
                changed if and only if the input parameter is greater than zero ***/
                public void setAmount(double amount) {
                  if (amount > 0) {
                    this.amount = amount;
                  }
                }
}
