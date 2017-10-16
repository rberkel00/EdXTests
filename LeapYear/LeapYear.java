import java.util.Scanner;

public class LeapYear {

	public static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				if (year % 400 == 0) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		System.out.println("Enter a year: ");
		int year = read.nextInt();
		if (isLeapYear(year)) {
			System.out.println("The year " + year + " is a leap year.");
		} else {
			System.out.println("The year " + year + " is not a leap year.");
		}
	}
}
