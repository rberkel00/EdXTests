import java.util.Scanner;

/**
 * Created by Phil Sands on 11/30/2016.
 */
public class HolidayRunner {
    public static void main(String[] args) {
        DeliveryMap d = new DeliveryMap("maps/mapA.txt");
        ScannerClaus sc = new ScannerClaus(d);
        Scanner s = new Scanner(System.in);
        int playMode;
        char choice;

        System.out.println("Automate (1) or Play (2): ");
        playMode = s.nextInt();

        if (playMode == 1) {
            do {
                System.out.println(d.printMap());
                sc.move(d, sc.chooseMove(d));
            } while (sc.getNumCarrots() > 0 && sc.getNumPresents() > 0);
        } else {
            do {
                System.out.println(d.printMap());
                System.out.println(sc);
                choice = s.next().toUpperCase().charAt(0);
                sc.move(d, choice);
            } while (choice != 'Q' && sc.getNumCarrots() > 0 && sc.getNumPresents() > 0);
        }
        if (sc.getNumCarrots() == 0) {
            System.out.println("Your reindeer ran out of carrots and refuse to keep going!");
            System.out.println(sc.getNumPresents() + " presents left to deliver.");
          }
        else if (sc.getNumPresents()==0)
            System.out.println("You delivered all of the presents! Ho ho!");
        System.out.println(sc.getPath() + " ");
    }
}
