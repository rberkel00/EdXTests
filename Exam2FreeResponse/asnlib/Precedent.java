public class Precedent {
  //TODO: put static method here
  public static String whatsBefore(String a, String b) {
    return a.substring(0, a.indexOf(b));
  }
  public static void main(String[] args) {
    System.out.println(whatsBefore("hello goodbye", "ood"));
  }
}
