import java.util.Scanner;

public class Prime {

	static boolean isPrime(int number) {
		if (number < 1) return false;
		for(int counter = 2; counter < number; counter++) {
			if(number % counter == 0) {
				return false;
			}
		
