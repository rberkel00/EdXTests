public class Playlist {
  String[] nextFive;
  public Playlist() {
    nextFive = new String[5];
  }
  public boolean makeRequest(String s) {
    if (searchList(s) == -1) {
      for (int i = 0; i < 5; i++) {
        if (nextFive[i] == null) {
          addSong(s);
          return true;
        }
      }
    }
    return false;
  }
  private int searchList(String s) {
    for (int i = 0; i < 5; i++) {
      if (nextFive[i] != null && nextFive[i].equals(s)) {
        return i;
      }
    }
    return -1;
  }
  private void addSong(String s) {
    for (int i = 0; i < 5; i++) {
      if (nextFive[i] == null) {
        nextFive[i] = s;
        return;
      }
    }
  }
  public String getNextSong() {
    String result =  nextFive[0];
    for (int i = 1; i < 5; i++) {
      nextFive[i-1] = nextFive[i];
      nextFive[i] = null;
    }
    return result;
  }

  public static void main(String[] args) {
    Playlist p = new Playlist();
    System.out.println("1" + p.makeRequest("Song1"));
    System.out.println("2" + p.makeRequest("Song1"));
    System.out.println("3" + p.makeRequest("Song2"));
    System.out.println("4" + p.makeRequest("Song3"));
    System.out.println("5" + p.makeRequest("Song4"));
    System.out.println("6" + p.makeRequest("Song5"));
    System.out.println("7" + p.makeRequest("Song6"));
    System.out.println("8" + p.getNextSong());
  }

}
