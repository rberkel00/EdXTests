public class Card implements Comparable<Card> {
  private String name, nationality;
  private int yearBorn, yearDied;

  public Card (String name, String nationality, int yearBorn, int yearDied) {
    this.name = name;
    this.nationality = nationality;
    this.yearBorn = yearBorn;
    this.yearDied = yearDied;
  }

  public int compareTo(Card c) {
    return this.name.compareTo(c.name);
  }

  public String toString() {
    return name + "(" + yearBorn + "-" + ((yearDied==-1) ? "present" : yearDied) + ") - " + nationality;
  }

  public static void main(String[] args) {
    Card a = new Card("A" ,"B", 2000, 2009);
    Card b = new Card("B", "T", 2001, 2005);
    Card c = new Card("B", "T", 2001, 2005);
    System.out.println(a.compareTo(b) + " " + b.compareTo(c) + " " + c.compareTo(a));
  }

}
