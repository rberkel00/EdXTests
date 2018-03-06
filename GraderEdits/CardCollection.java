import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.*;

public class CardCollection {
  private String owner;
  private List<Card> myCollection;
  private String NEWVARIABLE;
  public double HELLO = 2.99;

  public CardCollection(String owner) {
    this.owner = owner;
    myCollection = new ArrayList<Card>();
  }

  public boolean addCard(Card c) {
    for (int i = 0; i < myCollection.size(); i++) {
      if (myCollection.get(i).compareTo(c) == 0) {
        return false;
      } else if (myCollection.get(i).compareTo(c) == 1) {
        myCollection.add(i, c);
        return true;
      }
    }
    myCollection.add(c);
    return true;
  }

  public void removeCard(int i) {
    myCollection.remove(i);
  }

  public int getSize() {
    return myCollection.size();
  }

  public List<Card> mergeCollections(CardCollection cc) {
    List<Card> dups = new ArrayList<Card>();
    for (int i = 0; i < cc.myCollection.size(); i++) {
      cc.removeCard(i);
      if (!addCard(cc.myCollection.get(i))) {
        dups.add(cc.myCollection.get(i));
      }
    }
    return dups;
  }

  public String toString() {
    String result = owner + "\n-----\n";
    for (Card c : myCollection) {
      result += c.toString() + "\n";
    }
    return result;
  }

}
