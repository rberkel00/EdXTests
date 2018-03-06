import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.util.*;

public class CardCollection {

    private String owner;

    private List<Card> myCollection;

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

    public static void main(String[] args) {
        Card a = new Card("R", "A", 1995, 2009);
        Card b = new Card("C", "A", 1994, 2009);
        Card c = new Card("B", "A", 1995, -1);
        CardCollection aa = new CardCollection("P");
        CardCollection bb = new CardCollection("T");
        aa.addCard(a);
        aa.addCard(b);
        aa.addCard(c);
        aa.addCard(b);
        if (aa.getSize() != 3 || !aa.myCollection.get(0).toString().matches(".*B.*") || !aa.myCollection.get(0).toString().matches(".*C.*") || !aa.myCollection.get(0).toString().matches(".*R.*"))
            System.out.println("addCard failed");
        aa.removeCard(a);
        if (aa.getSize() != 2 || !aa.myCollection.get(0).toString().matches(".*B.*") || !aa.myCollection.get(0).toString().matches(".*C.*"))
            System.out.println("removeCard failed");
        if (aa.getSize() != 2)
            System.out.println("getSize failed");
        bb.addCard(a);
        bb.addCard(b);
        List<Card> dups = aa.mergeCollections(bb);
        if (bb.getSize() != 0)
            System.out.println("mergeCollections 2 failed");
        if (aa.getSize() != 3 || !aa.myCollection.get(0).toString().matches(".*B.*") || !aa.myCollection.get(0).toString().matches(".*C.*") || !aa.myCollection.get(0).toString().matches(".*R.*"))
            System.out.println("mergeCollections 3 failed");
        if (dups.size() != 1 || !dups.get(0).toString().matches(".*C.*"))
            System.out.println("mergeCollections 1 failed");
    }

    private String NEWVARIABLE;
}
