public class WordList {
  String[] words;
  int count;

  public WordList() {
    /*** TODO: Create new String array with size 2, and store in words
               Set count equal to 0 ***/
							 words = new String[2];
							 count = 0;
  }

  public int addWord(String w) {
    /*** TODO: Check which case the word, w, presents to your current list
               (1) w is in the list - do not add
               (2) words is not full and w is not in the list - add w, increment count
               (3) words is full - create new String array with two times the space,
                                   copy data from words to new array, set words equal
                                   to new array and then add w, increment count
               For all cases, return the current value of count ***/
							 if (findWord(w) != -1) return count;
							 if (count < words.length) {
								 words[count] = w;
								 count++;
							 }
							 if (count >= words.length) {
								 String[] temp = new String[count * 2];
								 for (int i = 0; i < count; i++) {
									 temp[i] = words[i];
								 }
								 words = temp;
								 addWord(w);
							 }
							 return count;
  }

  public void removeWord(String w) {
    /*** TODO: Find w in words. If found, move all elements to right of w one space to
               the left and decrement count. Otherwise, do nothing ***/
							 if (findWord(w) == -1) return;
							 for (int i = findWord(w); i < count - 1; i++) {
								 words[i] = words[i + 1];
							 }
							 count--;
  }

  public int findWord(String w) {
    /*** TODO: Loop over all words until w is found. Return index of w, or -1 if not
               found ***/
							 for (int i = 0; i < count; i++) {
								 if (w.equals(words[i])) return i;
							 }
							 return -1;
  }

  public boolean equals(WordList other) {
    if (words.length != other.count) {
      return false;
    } else {
      for (int i = 0; i < words.length; i++) {
        if (words[i] != other.words[i]) {
          return false;
        }
      }
    }
    return true;
  }

  public String toString() {
    String s = "There are " + count + " word" + ((words.length == 1)?"":"s") + " in the word list:\n";
    for (String w : words) {
      s = s + w + "\n";
    }
    return s;
  }

  public static void main(String[] args) {
    WordList wl = new WordList();
    wl.addWord("Dog");
    System.out.print(wl);
    wl.addWord("Dog");
    System.out.print(wl);
    wl.removeWord("Dog");
    wl.addWord("Cat");
    wl.addWord("Fish");
    wl.addWord("Horse");
    System.out.print(wl);
    if (wl.findWord("Cat") >= 0)
      System.out.println("Cat is in the word list");
    if (wl.findWord("Dog") >= 0)
      System.out.println("Dog is in the word list");
    WordList myList = new WordList();
    myList.addWord("Cat");
    myList.addWord("Horse");
    myList.addWord("Fish");
    if (wl.equals(myList))
      System.out.println("The two lists are the same");
  }
}
