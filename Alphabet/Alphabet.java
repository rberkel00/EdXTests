import java.util.Scanner;
public class Alphabet {

    public static String popFrom(String poppee, String popper) {
        if (poppee.contains(popper)) {
            int toRemove = poppee.indexOf(popper);
            return poppee.substring(0, toRemove) + poppee.substring(toRemove+1);
        } else {
            return poppee;
        }
    }

    public static String whatsMissing (String sentence) {
        String ans = "abcdefghijklmnopqrstuvwxyz";
        String mySentence = sentence.toLowerCase();
        for (int i = 0; i < mySentence.length(); i++) {
            String myString = mySentence.charAt(i) + "";
            if (ans.contains(myString)) {
                ans = popFrom(ans, myString);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Please type in a sentence, then I will print all the letters that aren't in the sentence.");
        String myWords = s.nextLine();
        String printMe = "The missing letters are: %s";
        System.out.printf(printMe, whatsMissing(myWords));
    }

}
