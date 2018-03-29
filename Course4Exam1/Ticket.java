import java.util.Random;

public abstract class Ticket {

    private int serialNumber; // unique ticket id number

    public Ticket() {
       serialNumber = getNextSerialNumber();
    }

    /**
    *    @return the price for this ticket
    */

    public abstract double getPrice();

    /**
    *    @return a string with information about the ticket
    */

    public String toString() {
       return "Number: " + serialNumber + ", Price: " + getPrice();
    }

    /**
    *   @return a new unique serial number
    */
   private static int getNextSerialNumber() {
       /* implementation not shown */
       Random r = new Random();
       return r.nextInt(9000) + 1000;
   }

}
