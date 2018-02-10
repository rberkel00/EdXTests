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

}
