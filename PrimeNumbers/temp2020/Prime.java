import java.util.Scanner;

public class Prime {

    static boolean isPrime(int number) {
        if (number < 1)
            return false;
        for (int counter = 2; counter < number; counter++) {
            if (number % counter == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        System.out.println("Enter a positive integer: ");
        int num = read.nextInt();
        if (isPrime(num)) {
            System.out.println("The number " + num + " is prime.");
        } else {
            System.out.println("The number " + num + " is not prime.");
        }
    }
}
