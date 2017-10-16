import java.util.Scanner;

public class EmailGenerator {
  public static String makeUserName(String first, String last) {
    return (first.charAt(0) + last).toLowerCase();
  }

  public static String makeEmail(String userName, String address) {
    return userName + "@" + address;
  }

  public static void main(String[] args) {
     Scanner read = new Scanner(System.in);
     System.out.println("Enter your first name: ");
     String first = read.nextLine();
     System.out.println("Enter your last name: ");
     String last = read.nextLine();
     String userName = makeUserName(first, last);
     System.out.println("This user's e-mail is: " + makeEmail(userName, "purdue.edu"));
  }
}
