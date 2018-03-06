public class Practice {

    public static String scrambler(String s) {
        /*** TODO: Write a recursively defined function that behaves as follows:
                     base case 1: length of s is 0; return an empty String
                     base case 2: length of s is 1, return s
                     base case 2: length of s is 2, return s reversed (i.e., "ab" -> "ba")
                     recursive case: return the concatenation of the last character of s,
                      the outcome of a recursive call passing letters 2 up to but not
                      including the midpoint of s, the outcome of a recursive call passing
                      letters midpoint of s to the length of s minus 1, and the first
                      character of s (i.e. scrambler("computers") -> "s" + scrambler("omp") +
                      scrambler("uter") + "c") ***/
        if (s.length() == 0) {
            return "";
        } else if (s.length() == 1) {
            return s;
        } else if (s.length() == 2) {
            return s.charAt(1) + "" + s.charAt(0);
        } else {
            return s.charAt(s.length() - 1) + scrambler(s.substring(1, s.length() / 2)) + scrambler(s.substring(s.length() / 2, s.length() - 1)) + s.charAt(0);
        }
    }

    public static void main(String[] args) {
        System.out.println(" 1" + scrambler("") + "2 ");
        System.out.println(" " + scrambler("a") + " ");
        System.out.println(" " + scrambler("bc") + " ");
        System.out.println(" " + scrambler("fish") + " ");
        System.out.println(" " + scrambler("pterodctyl") + " ");
        System.out.println(" " + scrambler(scrambler("purdue")) + " ");
    }
}
