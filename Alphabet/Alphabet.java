import java.util.Scanner;

public class Alphabet {

	public static String whatsMissing(String str) {
		str = str.toLowerCase();
		String result = "";
		for (int i = 'a'; i <= 'z'; i++) {
			if (str.indexOf(i) == -1) {
				result += (char)i;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		String line = read.nextLine();
		System.out.println("The missing letters are: " + whatsMissing(line));
	}

}
